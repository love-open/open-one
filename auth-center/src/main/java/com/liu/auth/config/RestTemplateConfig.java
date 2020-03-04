package com.liu.auth.config;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @ClassName RestTemplateConfig
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/8/13
 * @Version V1.0
 **/
@Configuration
public class RestTemplateConfig {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateConfig.class);
    @Configuration
    public class RestClientConfig {
        @Bean
        public RestTemplate restTemplate() {
            // unable to find valid certification path to requested target
        /*
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        */

            // 401 Unauthorized
        /*
        String host = "localhttps";
        int port = 23333;
        String user = "john";
        String password = "123456";
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(host, port),
                new UsernamePasswordCredentials(user, password));
        */


            CloseableHttpClient httpClient
                    = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
//                .setSSLSocketFactory(sslConnectionSocketFactory)
//                .setDefaultCredentialsProvider(credsProvider)
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory
                    = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            restTemplate.setRequestFactory(clientHttpRequestFactory());
            restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

            return restTemplate;
        }
        @Bean
        public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
            try {
                HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
                SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                    @Override
                    public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                        return true;
                    }
                }).build();
                httpClientBuilder.setSSLContext(sslContext);
                HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
                SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.getSocketFactory())
                        .register("https", sslConnectionSocketFactory).build();// 注册http和https请求
                // 开始设置连接池
                PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                poolingHttpClientConnectionManager.setMaxTotal(500); // 最大连接数500
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100); // 同路由并发数100
                httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
                httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true)); // 重试次数
                HttpClient httpClient = httpClientBuilder.build();
                HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient); // httpClient连接配置
                clientHttpRequestFactory.setConnectTimeout(20000);              // 连接超时
                clientHttpRequestFactory.setReadTimeout(30000);                 // 数据读取超时时间
                clientHttpRequestFactory.setConnectionRequestTimeout(20000);    // 连接不够用的等待时间
                return clientHttpRequestFactory;
            } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
                logger.error("初始化HTTP连接池出错", e);
            }
            return null;
        }
    }
}
