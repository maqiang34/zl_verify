app.controller('verifyUpdate', function ($scope, $http, $rootScope, $state, Upload) {
    $scope.index = 0;
    var n = 10000;
    $scope.update = function () {
        if ($scope.local) {
            return;
        }
        var file = $("#inputfile")[0].files[0];
        if (file == null) {
            layer.msg("请选择文件！");
            return;
        }
        Upload.upload({
            url: "/i/verify/fileUpload",
            headers: {'Content-Transfer-Encoding': 'utf-8'},
            data: {
                file: file
            }
        }).then(function (rep) {
            var data = rep.data;
            if (data.success) {
                layer.msg("上传完成，开始解析");
                $scope.getData();
            } else {
                layer.msg(data.message);
            }
        });
    }
    $scope.getData = function () {
        $scope.index = 1;
        //$scope.init_chartsData();
    }


    $scope.download = function () {
        var expfrom = $("<form>").attr('style', 'display:none').attr('method', 'post').attr('action', window.location.origin + '/i/verify/fileDownload').attr('id', "expdataform");
        expfrom.attr("Content-Type", "application/json;charset=UTF-8");
        expfrom.append($("<input>").attr("name", "fileName").attr("value", "data.csv"));
        expfrom.appendTo('body').submit().remove();
    }

    $scope.fileModify = function () {
        var expfrom = $("<form>").attr('style', 'display:none').attr('method', 'post').attr('action', window.location.origin + '/i/verify/fileModify').attr('id', "expdataform");
        expfrom.attr("Content-Type", "application/json;charset=UTF-8");
        expfrom.append($("<input>").attr("name", "fileName").attr("value", "data.csv"));
        expfrom.appendTo('body').submit().remove();
    }


    $scope.init_chartsData = function () {
        var index = layer.load(0, {shade: false});
        $http({
            method: 'GET',
            url: '/i/verify/getVerData',
        }).then(function successCallback(rep) {
            layer.close(index);
            console.log(data);
            var data = rep.data;
            if (data.success) {
                $scope.init_dwrCharts(data.entity);
            } else {
                layer.msg(data.message);
            }
        }, function errorCallback(response) {
            // 请求失败执行代码
        });
    }
    $scope.init_dwrCharts = function (dataList) {
        var series = [];// [{type: 'area',name: '美元兑欧元',  data: data  }]
        angular.forEach(dataList, function (obj, i) {
            series.push({type: 'line', name: '设备' + i, data: obj})
        });
        chart = Highcharts.chart('div_char', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: '设备数据采集'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                    '鼠标拖动可以进行缩放' : '手势操作进行缩放'
            },
            xAxis: {
                type: 'datetime',
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            tooltip: {
                dateTimeLabelFormats: {
                    millisecond: '%H:%M:%S.%L',
                    second: '%H:%M:%S',
                    minute: '%H:%M',
                    hour: '%H:%M',
                    day: '%Y-%m-%d',
                    week: '%m-%d',
                    month: '%Y-%m',
                    year: '%Y'
                }
            },
            yAxis: {
                title: {
                    text: ''
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: series
        });
    }

});