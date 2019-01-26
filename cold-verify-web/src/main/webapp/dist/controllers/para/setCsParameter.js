app.controller('setCsParameter', function ($scope, $http, $rootScope, $state, $stateParams, Upload) {
    $scope.index = 0;
    var n = 10000;

    $scope.model = {};

    $scope.save = function() {
        var para = $scope.model;
        // var jsonpara = angular.toJson(para);
        // var proid= $stateParams.powerid;
        console.log("请求参数为：" + para);
        var data ={
            "mapping" : para,
            "pmid" : 1
        };
        // data.mapping = para;
        // data.pmid = 1;
        console.log("提交的参数：" + data);
        $http.post('/i/para/setPara', data).success(function (data) {
            if (data.code == 200) {
                console.log("修改成功了");
            }else{
                alert(data.msg);
            }
        });
    }


    $scope.download = function () {
        var expfrom = $("<form>").attr('style', 'display:none').attr('method', 'post').attr('action', window.location.origin + '/i/verifyData/checkData').attr('id', "expdataform");
        expfrom.attr("Content-Type", "application/json;charset=UTF-8");
        expfrom.append($("<input>").attr("name", "fileName").attr("value", "data.csv"));
        expfrom.appendTo('body').submit().remove();
    }


});