app.controller('setCsParameter', function ($scope, $http, $rootScope, $state, $stateParams, Upload) {
    $scope.index = 0;
    $scope.layoutid = null;
    $scope.model = {};
    $scope.cslayout = {};
    $scope.lisLay = {};
    $scope.getdata = function() {
        var para = {
            "pmid" : $stateParams.powerid,
        }
        $http.post('/i/para/getPara', para).success(function (data) {
            if (data.code == 200) {
                if (data.paramod != null && data.paramod != undefined) {
                    $scope.model = angular.fromJson(data.paramod);
                    $scope.cslayout = angular.fromJson(data.layouts);
                    $scope.lisLay = data.lisLay;
                }
            }else{
                alert(data.msg);
            }
        });
    }

    $scope.getdata();

    $scope.save = function() {
        var para = $scope.model;
        var layoud = $scope.cslayout;
        var jsonpara = angular.toJson(para);
        var jsonlayout = angular.toJson(layoud);
        var proid= $stateParams.powerid;
        console.log("proid：" + proid);
        var data ={
            "mapping" : jsonpara,
            "pmid" : proid,
            "layMapping" : jsonlayout
        };
        $http.post('/i/para/setPara', data).success(function (data) {
            if (data.code == 200) {
                console.log("操作成功");
            }else{
                alert(data.msg);
            }
        });
    }


    $scope.changelayout = function (x) {
        var layoutId = x.value;
        console.log("layouid：" + layoutId);
        var paradata = {
            "layId" : layoutId
        }
        $http.post('/i/para/getlayout', paradata).success(function (data) {
            if (data.code == 200) {
                $scope.cslayout = data.laymod;
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