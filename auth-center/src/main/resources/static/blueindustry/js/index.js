//订单跟踪
var orderDetail = echarts.init(document.getElementById('ec-orderdeatail'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '11%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        x: 'right',
        data:['APP下单','销售下单'],
    },
    xAxis: {
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'APP下单',
            type:'line',
            symbol: 'circle',
            data:[50, 30, 60, 70, 230, 120, 140, 90, 60, 80, 180, 200]
        },
        {
            name:'销售下单',
            type:'line',
            symbol: 'circle',
            data:[80, 70, 60, 90, 280, 100, 120, 60, 100, 60, 240 ,80]
        },
    ]
};
orderDetail.setOption(option);

//定制化需求交付
var orderDemand = echarts.init(document.getElementById('ec-orderdemand'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '12%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        x: 'right',
        data:['非定制订单','定制订单']
    },
    xAxis: {
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'非定制订单',
            type:'bar',
            data:[50, 30, 60, 70, 230, 120, 140, 90, 60, 80, 180, 200]
        },
        {
            name:'定制订单',
            type:'bar',
            data:[80, 70, 60, 90, 280, 100, 120, 60, 100, 60, 240 ,80]
        },
    ]
};
orderDemand.setOption(option);

//物料请求
var materiel = echarts.init(document.getElementById('ec-materiel'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '12%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    legend: {
        x: 'right',
        data:['申请数量','产出数量']
    },
    xAxis: {
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'申请数量',
            type:'line',
            data:[50, 30, 60, 70, 230, 120, 140, 90, 60, 80, 180, 200]
        },
        {
            name:'产出数量',
            type:'bar',
            data:[80, 70, 60, 90, 280, 100, 120, 60, 100, 60, 240 ,80]
        },
    ]
};
materiel.setOption(option);

//完成进度
var complete = echarts.init(document.getElementById('ec-complete'),'halloween');
var option = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    series: [
        {
            name: '完成进度',
            type: 'gauge',
            data: [{value: 100, name: ''}],
            radius: '100%',
            axisLine: {
                lineStyle : {
                    color:[
                        [0.1, '#f2fc6b'],
                        [0.2, '#f2fc6b'],
                        [0.3, '#f2fc6b'],
                        [0.4, '#f2fc6b'],
                        [0.5, '#f2fc6b'],
                        [0.6, '#f2fc6b'],
                        [0.7, '#f2fc6b'],
                        [0.8, '#f2fc6b'],
                        [0.9, '#f2fc6b'],
                        [1, '#f2fc6b'],
                    ],
                    width: 1,
                    opacity:0
                },
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#ffffff',
                fontSize: 10,
                fontFamily: 'Microsoft YaHei'
                // show: false
            },
            pointer: {
                width:4
            },
            detail: {
                color: '#25a4f5',
                fontSize: 14,
                fontFamily: 'Microsoft YaHei',
                fontWeight: 'bold',
                offsetCenter: ['-100px' , '-90%'],
                formatter:'{value}%'
            }
        }
    ]
};
complete.setOption(option);

//直通率
var adopt = echarts.init(document.getElementById('ec-adopt'),'halloween');
var option = {
    tooltip : {
        formatter: "{a} <br/>{b} : {c}%"
    },
    series: [
        {
            name: '业务指标',
            type: 'gauge',
            data: [{value: 99, name: ''}],
            radius: '100%',
            axisLine: {
                lineStyle : {
                    color:[
                        [0.1, '#f2fc6b'],
                        [0.2, '#f2fc6b'],
                        [0.3, '#f2fc6b'],
                        [0.4, '#f2fc6b'],
                        [0.5, '#f2fc6b'],
                        [0.6, '#f2fc6b'],
                        [0.7, '#f2fc6b'],
                        [0.8, '#f2fc6b'],
                        [0.9, '#f2fc6b'],
                        [1, '#f2fc6b'],
                    ],
                    width: 1,
                    opacity:0
                },
                show: false
            },
            splitLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                color: '#ffffff',
                fontSize: 10,
                fontFamily: 'Microsoft YaHei'
                // show: false
            },
            pointer: {
                width:4
            },
            detail: {
                color: '#25a4f5',
                fontSize: 14,
                fontFamily: 'Microsoft YaHei',
                fontWeight: 'bold',
                offsetCenter: ['-110px' , '-90%'],
                formatter:'{value}%'
            }
        }
    ]
};
adopt.setOption(option);

//计划外运营维护
var maintenance = echarts.init(document.getElementById('ec-maintenance'),'halloween');
var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },

    legend: {
        x: 'left',
        y: 'top',
        data: ['保养人工费', '材料全额'],
    },
    series: [
        {
            type:'pie',
            radius: ['38%', '48%'],
            label: {
                normal: {
                    textStyle: {
                        fontSize: 14,
                    },
                    formatter: "{b}({d}%)",
                    show: false
                },
                emphasis: {
                    textStyle: {
                        fontSize: '12',
                    }
                }
            },
            data:[
                {value:35, name:'保养人工费'},
                {value:310, name:'材料全额'},
            ]
        }
    ]
};
maintenance.setOption(option);

//TOP原因
var reason = echarts.init(document.getElementById('ec-reason'),'halloween');
var option = {
    tooltip: {
        trigger: 'item',
        formatter: "{b}: {c} ({d}%)"
    },
    legend: {
        x: 'left',
        data:['操作异常','制造异常','预期磨损或老化']
    },
    series: [
        {
            type:'pie',
            radius: ['38%', '48%'],
            label: {
                normal: {
                    textStyle: {
                        fontSize: 14,
                    },
                    formatter: "{b}({d}%)",
                    show: false
                },
                emphasis: {
                    textStyle: {
                        fontSize: '12',
                    }
                }
            },
            data:[
                {value:310, name:'预期磨损或老化'},
                {value:35, name:'操作异常'},
                {value:15, name:'制造异常'},

            ]
        }
    ]
};
reason.setOption(option);

//产品不良率
var unqualified = echarts.init(document.getElementById('ec-unqualified'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '12%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    legend: {
        data:['性能不良','外观不良','结构不良'],
        x: 'right'
    },
    xAxis : [
        {
            type : 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'性能不良',
            type:'bar',
            data:[20, 5, 15, 23, 16, 34, 26, 28, 34, 25, 32, 34]
        },
        {
            name:'外观不良',
            type:'bar',
            data:[5, 7, 3, 6, 4, 9, 3, 3, 5, 2, 5, 3]
        },
        {
            name:'结构不良',
            type:'bar',
            data:[5, 7, 8, 8, 2, 4, 6, 4, 6, 8, 4, 6]
        },
    ]
};
unqualified.setOption(option);

//物料异常占比
var fault = echarts.init(document.getElementById('ec-fault'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '11%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        },
        formatter: "{b} : {c}%"
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [1, 3, 2, 5, 3, 6, 7, 4, 3, 6, 4, 3],
        type: 'line',
        areaStyle: {}
    }]
};
fault.setOption(option);

//设备故障提示
var abnormal = echarts.init(document.getElementById('ec-abnormal'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '11%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        },
        formatter: "{b} : {c}%"
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        data: [1, 3, 2, 5, 3, 6, 7, 4, 3, 6, 4, 3],
        type: 'line',
        areaStyle: {},
        symbol: 'circle'
    }]
};
abnormal.setOption(option);

//OEE与产量
var yield = echarts.init(document.getElementById('ec-yield'),'halloween');
var option = {
    grid: {
        left: '1%',
        right: '1%',
        top: '11%',
        bottom: '5%',
        containLabel: true
    },
    tooltip : {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        },
        formatter: "{b} : {c}%"
    },
    legend: {
        data:['OEE','产量'],
        x: 'right'
    },
    xAxis: {
        type: 'category',
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'OEE',
            type:'line',
            data:[50, 30, 60, 70, 230, 120, 140, 90, 60, 80, 180, 200]
        },
        {
            name:'产量',
            type:'bar',
            data:[80, 70, 60, 90, 280, 100, 120, 60, 100, 60, 240 ,80]
        },
    ]
};
yield.setOption(option);