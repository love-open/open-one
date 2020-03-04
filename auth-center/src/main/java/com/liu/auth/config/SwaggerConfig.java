package com.liu.auth.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Frank.liu
 * swagger相关配置类
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private Contact contact = new Contact("Frank.liu","localhost:8080/swagger-ui.html", "714240924@qq.com");

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("auth", "无论是否前后端分离都共用的登陆、注销、注册及刷新token"), getTags());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("平台接口 v1.0")
                .description("平台涉及的所有接口")
                .contact(contact)
                .version("1.0")
                .build();
    }

    private Tag[] getTags() {
        Tag[] tags = {
                //new Tag("auth", "无论是否前后端分离都共用的登陆、注销、注册及刷新token"),
                new Tag("login", "非前后端分离系统用到的登录相关接口"),
                new Tag("user", "用户相关的接口"),
                new Tag("role", "角色相关的接口"),
                new Tag("menu", "菜单相关的接口"),
                new Tag("dict", "字典相关的接口")
        };
        return tags;
    }

}
