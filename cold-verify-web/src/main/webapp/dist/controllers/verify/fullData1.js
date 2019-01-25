app.controller('fullData1', function ($scope, $http, $rootScope, $state, Upload) {
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
            url: "/i/verifyData1/fileUpload",
            headers: {'Content-Transfer-Encoding': 'utf-8'},
            data: {
                file: file
            }
        }).then(function (rep) {
            var data = rep.data;
            if (data.success) {
                layer.msg("上传完成，开始解析");
                $scope.download();
            } else {
                layer.msg(data.message);
            }
        });
    }

    $scope.download = function () {
        var expfrom = $("<form>").attr('style', 'display:none').attr('method', 'post').attr('action', window.location.origin + '/i/verifyData1/checkData').attr('id', "expdataform");
        expfrom.attr("Content-Type", "application/json;charset=UTF-8");
        expfrom.append($("<input>").attr("name", "fileName").attr("value", "data.csv"));
        expfrom.appendTo('body').submit().remove();
    }


});