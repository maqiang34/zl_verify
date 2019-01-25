app.controller('reportUpdate', function ($scope, $http, $rootScope, $state, Upload) {
    var confIndex=8;
    var fileName="";
    var confIndexs=[];
    var reportUrl="";
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
        url: "/i/report/fileUpload",
        headers: {'Content-Transfer-Encoding': 'utf-8'},
        data: {
            file: file
        }
    }).then(function (rep) {
        var data = rep.data;
        if (data.success) {
            layer.msg("上传完成");
        } else {
            layer.msg(data.message);
        }
    });
}
    $scope.preview=function(){
            $scope.newReport(1);
            $scope.getPerView();
    }
    $scope.newReport=function(type){
        var dataList=[];
        var fileList=[];
        for(var i=2;i<=7;i++){
            var data={};
            var addName="test"+i;
            var ele="#test"+i;
            if(!$(ele)[0].files){
                if($(ele)[0].value){
                    data.confData=$(ele)[0].value;
                }else{
                    layer.msg("请完善"+addName+"得内容")
                    return;
                }
                data.name=addName;
                data.type=0;
                dataList.push(data);
            }else{
                if($(ele)[0].files[0]){
                    fileList.push($(ele)[0].files[0]);
                }else{
                    layer.msg("请上传"+addName+"得图片")
                    return;
                }
                data.name=addName;
                data.type=1;
                data.fileIndex=fileList.length-1;
                dataList.push(data);
            }
        }
        var listIndex=2;
        if(confIndex>7){
            for(var i=8;i<confIndex;i++){
                var data={};
                var str="input[name=test"+i+"]";
                if($(str)[0].value && $(str)[0].value!=null){
                    var addName=$(str)[0].value;
                }else{
                    layer.msy("请完善资料");
                    return;
                }
                var ele="#test"+i;
                if(!$(ele)[0].files){
                    if($(ele)[0].value && $(ele)[0].value!=null){
                        data.confData=$(ele)[0].value;
                    }else{
                        layer.msg("请完善"+addName+"得内容");
                        return;
                    }
                    data.name=addName;
                    data.type=0;
                    dataList.push(data);
                }else{
                    if($(ele)[0].files[0] && $(ele)[0].files[0]!=null){
                        fileList.push($(ele)[0].files[0]);
                    }else{
                        layer.msg("请上传"+addName+"得图片");
                        return;
                    }
                    data.name=addName;
                    data.type=1;
                    data.fileIndex=fileList.length-1;
                    dataList.push(data);
                }
            }
        }
        var file = $("#inputfile")[0].files[0].name;
        Upload.upload({
            url: "/i/report/uploadConf",
            headers: {'Content-Transfer-Encoding': 'utf-8'},
            data: {
                confData:JSON.stringify(dataList),fileName:file
            },
            file:fileList
        }).then(function (rep) {
            var data = rep.data;
            if (data.success) {
                reportUrl=data.message;
                if(type==1){
                    $scope.getPerView();
                }else{
                    var expfrom = $("<form>").attr('style', 'display:none').attr('method', 'post').attr('action', window.location.origin + '/i/report/downloadReport').attr('id', "expdataform");
                    expfrom.attr("Content-Type", "application/json;charset=UTF-8");
                    expfrom.append($("<input>").attr("name", "fileName").attr("value", "data.csv"));
                    expfrom.appendTo('body').submit().remove();
                }
            } else {
                layer.msg("报告生成失败");
            }
        });
    }
    $scope.getPerView=function(){
        window.open("/i/report/preview?filename="+reportUrl);
    }
    $scope.addConf=function(){
        if(!$scope.selectType){
            layer.msg("请先选择参数类型");
            return;
        }
        if($scope.selectType=="文本框"){
            var eleStr='<div class="input-group" style="margin-top: 10px;font-size:16px">' +
                '<input type="text" class="form-control" style="border-top-left-radius: 5px;border-bottom-left-radius: 5px; width: 50%" placeholder="请输入配置名称" name='+"test"+confIndex+'>' +
                '<input type="text" id='+"test"+confIndex+' class="form-control" style="border-top-right-radius: 5px;border-bottom-right-radius: 5px; width: 50%" ng-model='+"test"+confIndex+' placeholder="请输入参数">' +
                '</div>';
            confIndexs.push(confIndex);
            confIndex++;
        }else{
            var eleStr='<div class="input-group" style="margin-top: 10px;font-size:16px">' +
                '<input type="text" class="form-control" style="border-radius: 5px; width: 50%" placeholder="请输入配置名称" name='+"test"+confIndex+'>' +
                '<input type="file" accept="image/*" style="display: inline" id='+"test"+confIndex+' ng-model='+"test"+confIndex+'>' +
                '</div>';
            confIndexs.push(confIndex);
            confIndex++;
        }
        $("#newConf").append(eleStr);
    }
    $scope.getModule=function(){
        var file = $("#inputfile")[0].files[0];
        var type=file.name.split(".");
        $http({
            method: "post",
            url: "/i/report/getModule",
            params:{"filename":file.name,"type":type[1]}
        }).
        success(function(data, status) {

        }).
        error(function(data, status) {
        });
    }
    $scope.download = function () {
        if(reportUrl==""){
           $scope.newReport(2);
        }else{
            var expfrom = $("<form>").attr('style', 'display:none').attr('method', 'post').attr('action', window.location.origin + '/i/report/downloadReport').attr('id', "expdataform");
            expfrom.attr("Content-Type", "application/json;charset=UTF-8");
            expfrom.append($("<input>").attr("name", "fileName").attr("value", "data.csv"));
            expfrom.appendTo('body').submit().remove();
        }

    }


});