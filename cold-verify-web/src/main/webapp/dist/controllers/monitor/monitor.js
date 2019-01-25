/*
 *Created width wellsea
 *温湿度controller
 * 2017-9-13 16:29:42
 * dataType:-->1:1oid1key     2:多oid1key  3:1oid多key 4:多oid多key 
 * viewtype :当前模型支持图表类型 
 * listtype:0 列表组件   1组态  2:3D 
 * 
 */

 var  h=$("#uiView"), VIEWSIZE={divStyle:'height:200px;width:24%', height: 200,width:330,vh:h.height(),vw:h.width() };
app.controller('monitor', function($scope,$http, $rootScope,$state) {
	    $scope.initAll=function () {
	    	$(".select2").select2();
	    	$scope.initmodelist();
	    	initmoView();
	    };
	    
	    //创建模型
	    $scope.createMode=function(){
	    	layer.open({
	    		  type: 1,
	    		  skin: 'layui-layer-rim', //加上边框
	    		  area: ['420px', '240px'], //宽高
	    		  content: 'html内容'
	    		});
	    };
	  
	    //1.1 初始化用户模型数据
		$scope.initmodelist=function(){
			 $scope.modelist = [ {id:1,name:"列表-全局监控"}, 
//				 {id:2,name:"组态-低温库监控"},{id:3,name:"平面监控"}, {id:6,name:"机组监控"},
				 {id:2,name:"机组监控"}, {id:3,name:"机组监控"}];
			 $scope.modedata={
						1:{id:3142,listtype:1,mode:[
							                        	{oid:340,title:'冷库1平均温度',type:18,key:'Temp',dataType:1,viewtype:0},
			                                            {oid:342,title:'冷库2平均温度',type:18,key:'Temp',dataType:1,viewtype:1},
			                                            {oid:343,oids:[343,344],title:'冷库3平均温度',type:18,key:'Temp',dataType:2,viewtype:2}
		                                            ] },
		                                            
//						 2:{id:3143,listtype:2,url: 'http://www.fscada.net/scada/editor.aspx', mode:[{oid:343,type:18,key:'Temp',dataType:1,viewtype:0,info:{url:''}}]},
//		                	
//						 3:{id:3145,listtype:2,url:'http://www.hightopo.com/demo/WSTEditor/index.html',},
//						    
//		                	
//		                	
//		                 4:{id:3143,listtype:2,url:'http://www.hightopo.com/demo/cloud-monitor/demo1.html', mode:[{oid:343,type:18,key:'Temp',dataType:1,viewtype:0,info:{url:''}}] },
		                 2:{id:3144,listtype:2,url:'http://www.hightopo.com/demo/cloud-monitor/demo2.html',},
		                 3:{id:3144,listtype:2,url:'http://www.hightopo.com/demo/cloud-monitor/demo1.html',},
		            
			   };
			 
			 $scope.selectedMode = $scope.modelist[0];  //设置第2个为初始值；
		};
		
	
	//1.2创建模型
    function initmoView(){
			    $scope.modeObj=[];
			    $scope.modeData=$scope.modedata[ $scope.selectedMode.id];
			    switch ($scope.modeData.listtype) {
					case 0:	break;
					case 1:cateUiListView();break;
					case 2:cateUiABBSView();break;
					case 3:cate3DView();break;
					default:	break;
	            }
	};
	//2.创建组件=========================================================================================================================================================================================================================
    //1.创建列表组件
	function  cateUiListView(){
		if($scope.modeData&&$scope.modeData.mode.length>0){
			window.removeEventListener('resize',resizefun); 
			angular.forEach($scope.modeData.mode,function(obj,i){ 
				obj.emid='dev_'+obj.type+'_'+obj.key+'_'+obj.oid;
	    		if(2==obj.viewtype){
	        		var data = [],now = new Date()-900000,value = -23;
	        		for (var int = 0; int < 30; int++) {
	        			now =now+30000, value1 = value + Math.random() * 10 ;
	        			data.push( { value: [now, value1] });
	        		}
	        		obj.data=data;
	        	}else{
	    			obj.data=[{value: -13.59, name: ''}];	
	    		}
	        	try {
	    			var emobj=UILISTVIEW[obj.type][obj.key][obj.viewtype](obj);
	    			if(emobj){ $scope.modeObj.push(emobj[0]);$scope.modeObj[emobj[0]]=emobj[1];}
	        	 } catch (e) { console.log("未找到模型！"); }
				
			});
			 window.addEventListener('resize',resizefun  );
	    }
    	
    }
    function initCharts(data){
        var chart = Highcharts.chart('container', {
            title: {
                text: '数据分析'
            },
            subtitle: {
                text: '数据来源链库网'
            },
            yAxis: {
                title: {
                    text: '温度'
                }
            },
            xAxis:{
                title:{
                    text:'时间'
                },
                categories:categories,
                labels:{
                    x:0,//调节x偏移
                    rotation: 90
                }
            },
            plotOptions: {
                series: {
                    label: {
                        connectorAllowed: false
                    },
                    pointStart: 2010
                }
            },
            series:data.data,
            responsive: {
                rules: [{
                    condition: {
                        maxWidth: 500
                    },
                    chartOptions: {
                        legend: {
                            layout: 'horizontal',
                            align: 'center',
                            verticalAlign: 'bottom'
                        }
                    }
                }]
            }
        });
	}
    var categories=null;
	$("#test").on("click",function (){
        var data={"fileName":fileData.get("file").name};
        console.log(data);
        $.ajax({
            url: "/i/file/selectAllDataFromDB",
            type: "POST",
            dataType:'JSON',
            data:data,
            success:function(data){
                console.log(data);
                console.log(JSON.stringify(data.data));
                initCharts(data);
            }
        })
    });
    var fileData=new FormData;
    $("#Filesumbit").on("click",function(){
        $.ajax({
            url: "/i/file/filesUpload",
            type: "POST",
            contentType: false,
            processData: false,
            // datType:'json',
            data:fileData,
            success:function(data){
                alert(data.message);
            }
        })
    })

    $("#file-file").on("change",function(e){
        fileData.append("file",e.target.files[0]);
    })
    //2.创建组态
	function  cateUiABBSView(){
//		angular.forEach($scope.modeData.mode,function(obj,i){ 
//    	    obj.emid='dev_'+obj.type+'_'+obj.key+'_'+obj.oid;
//    		obj.data=[{value: -13.59, name: ''}];	
//    		try {
//    			var emobj=UIABSVIEW[obj.type][obj.key][obj.viewtype](obj);
//    			if(emobj){ $scope.modeObj.push(emobj[0]);$scope.modeObj[emobj[0]]=emobj[1];}
//    			
//    			
//    			
//    			
//    			
//        	 } catch (e) { console.log("未找到模型！"); }
//		});
		$("#dash_main_div").append(' <iframe src="'+$scope.modeData.url+'" style="height:'+(VIEWSIZE.vh-80)+'px;width:'+VIEWSIZE.vw+'px;background: white;"></iframe>');
    }
	function  cate3DView(){
		
		
    }
	function test(){
	}

	
    //3.事件=========================================================================================================================================================================================================================
    $scope.changemode=function(){$("#dash_main_div").empty();initmoView();}
    function resizefun(){ angular.forEach($scope.modeObj,function(obj,i){$scope.modeObj[obj].resize(); }); }
    //   
   setInterval(function () {
	   angular.forEach($scope.modeData.mode,function(obj,i){ 
		    obj.data=[{value:  -(Math.random() * 30).toFixed(2)}];	
		    try {
		    	UILISTVIEW[obj.type][obj.key][obj.viewtype](obj,$scope.modeObj);
			} catch (e) {
				console.log("模型刷新失敗！");
            }
			
		});
	},2000);
    

   
   
   $scope.initAll();
});

