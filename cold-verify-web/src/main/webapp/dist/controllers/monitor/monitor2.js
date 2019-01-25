/*
 *Created width wellsea
 *温湿度controller
 * 2017-9-13 16:29:42
 * dataType:-->1:1oid1key     2:多oid1key  3:1oid多key 4:多oid多key
 * viewtype:1 实时 
 * viewtype 0:echar 1:heacr 2:自定义 3:组态
 */

	  var UILISTVIEW={
		        18:{
		        	'Temp':{
		        		'1':function(mode){//表盘
		        			return {
		        				   title: {
		        				        text: mode.title,
		        				        textStyle:{color:'#F8F8FF'},
		        				        fontWeight:'100'
		        				    },
		        				    min:-100,
		        		            max:100,
		        				    backgroundColor: 'rgba(0, 0, 0, .2)',
		        				    tooltip : { formatter: "{a} <br/>{b} : {c}℃"},
		        				    toolbox: {  feature: { restore: {},saveAsImage: {} }},
		        				    series: [
		        				        {
		        				            name: mode.title,
		        				            type: 'gauge',
		        				            detail: {formatter:'{value}℃',padding:[90,0,0,0]},
		        				            min:-30,
		        				            max:80,
//		        				            splitNumber:11,
		        				            radius: '95%',
		        				            axisLine: {            // 坐标轴线
		        				                lineStyle: {       // 属性lineStyle控制线条样式
		        				                    color: [[0.09, 'lime'],[0.3, '#1e90ff'],[1, '#ff4500']],
		        				                    width: 1,
		        				                    shadowColor : '#fff', //默认透明
		        				                    shadowBlur: 10
		        				                }
		        				            },
		        				            data:mode.data 
		        				        }
		        				    ]
		        				};
		        		 },
					  '2':function(mode){
							return {
								    title: { text: mode.title,   textStyle:{color:'#F8F8FF'},fontWeight:'100'},
								    backgroundColor: 'rgba(0, 0, 0, .2)',
								    tooltip : { formatter: "{a} <br/>{b} : {c}%"},
								    toolbox: {  feature: { restore: {},saveAsImage: {} }},
								    series: [
								        {
								            name: mode.title,
								            type: 'gauge',
								            detail: {formatter:'{value}%'},
								            data:mode.data 
								        }
								    ]
								};
						 }
		        	}
		        	
		        } 	
   }


app.controller('monitor', function($scope,$http, $rootScope,$state) {
	  $scope.modedata={
				1:{id:3142,mode:[{oid:342,title:'冷库1平均温度',type:18,key:'Temp',dataType:1,viewtype:1},{oid:342,title:'冷库1平均温度',type:18,key:'Temp',dataType:2,viewtype:1}] },
				2:{id:3143,mode:[{oid:343,type:18,key:'Temp',view:1}] }
	   };
	
	    //1.1 初始化用户模型数据
		$scope.initmodelist=function(){
			 $scope.modelist = [ {id:1,name:"全局监控"}, {id:2,name:"低温库监控"},{id:3,name:"变温监控"}, {id:4,name:"机组监控"}];
			 $scope.selectedMode = $scope.modelist[0];  //设置第2个为初始值；
		};
		//1.2创建模型
		$scope.initmodedata=function(){
			 $scope.modechar=[];
			var modeData=$scope.modedata[ $scope.selectedMode.id];
			if(modeData&&modeData.mode.length>0){
				angular.forEach(modeData.mode,function(obj,i){ 
					var emid='div_'+obj.type+'_'+obj.key+'_'+obj.oid;//div_18_Temp_16
					obj.data=[{value: -13.59, name: ''}];
					try {
					  var open=UILISTVIEW[obj.type][obj.key][obj.viewtype](obj);
					   cateoanle(emid,open);
					 } catch (e) {
						 console.log("未找到模型！");
		             }
				});
		    }
		
//		//.....
		$scope.carateMode1("div_18_1_TEMP");
//    	$scope.carateMode2("div_18_2_TEMP");
//    	$scope.carateMode3("div_18_3_TEMP");
//    	$scope.carateMode4("div_18_4_TEMP");
		
	};
	
    $scope.initAll=function () {
    	$(".select2").select2();
    	$scope.initmodelist();
    	$scope.initmodedata();
    };
    
    
    function cateoanle(emid,open,w,h){
    	var style='height:200px;width:32%';
    	if(w&h){style='height:'+h+'px;width:'+w; }
        var html="<div id='"+emid+"'  style='"+style+";float: left;text-align: center;background: rgba(0, 0, 0, .2) !important; border: 1px solid transparent;    margin: 0px 10px 10px 0px;'></div>";
    	$("#dash_main_div").append(html);
    	var myChart = echarts.init(document.getElementById(emid));
  	    myChart.setOption(open);
  	    myChart.viewtype=0;
  	    $scope.modechar.push(emid);
  		$scope.modechar[emid]=myChart;
    }
    
    $scope.carateMode1=function(emid){
    	var option = {
    			backgroundColor: 'rgba(0, 0, 0, .2)',
    		    title : {text: '电量累计',  x:'left' },
    		    tooltip : { trigger: 'item', formatter: "{a} <br/>{b} : {c}KW.h ({d}%)" },
    		    toolbox: {
    		        feature : {
    		            mark : {show: true},
    		            dataView : {show: true, readOnly: false},
    		            magicType : {
    		                show: true,
    		                type: ['pie', 'funnel']
    		            },
    		            restore : {show: true},
    		            saveAsImage : {show: true}
    		        }
    		    },
    		    series : [
    		        {
    		            name:'',
    		            type:'pie',
    		            radius : [30, 50],
    		            center : ['50%', '50%'],
    		            roseType : 'area',
    		            data:[
    		                {value:45.32, name:'电表1-电量：15.32KW.h'},
    		                {value:5, name:'电表2-电量：5KW.h'},
    		                {value:15, name:'电表3-电量：15KW.h'},
    		                {value:25, name:'电表4-电量：25KW.h'},
    		                {value:20, name:'电表5-电量：20KW.h'},
    		            ]
    		        }
    		    ]
    		};
    	 cateoanle(emid,option);
    }; 	
    
    
	$scope.carateMode2=function(emid){
        cateoanle(emid);
		option = {
			  backgroundColor: 'rgba(0, 0, 0, .2)',
		    title: { text: '一周耗电详细'},
		    tooltip: { trigger: 'axis'},
		    grid: {left: '3%', right: '4%',bottom: '3%', containLabel: true},
		    toolbox: { feature: { saveAsImage: {} }},
		    xAxis: { type: 'category', boundaryGap: false, data: ['周一','周二','周三','周四','周五','周六','周日'] ,  textStyle: {
		            color: '#6ac5fe',
		        }, },
		    yAxis: {type: 'value' },
		    series: [ { name:'总耗电量', type:'line', stack: '总量',   data:[120, 132, 101, 134, 90, 230, 210] },
		    	      { name:'谷耗电量',type:'line', stack: '总量', data:[220, 182, 191, 234, 290, 330, 310] },
		              { name:'平耗电量', type:'line',stack: '总量',data:[150, 232, 201, 154, 190, 330, 410] },
		              {name:'峰耗电量',type:'line',stack: '总量', data:[320, 332, 301, 334, 390, 330, 320] }
		    ]
		};
		var myChart = echarts.init(document.getElementById(emid));
		myChart.setOption(option);
	}
    
    
   


    
    $scope.carateMode3=function(emid){
    	cateoanle(emid);
    	option = {
    		    backgroundColor: 'rgba(0, 0, 0, .2)',
    		    legend: {
    		        top: 20,
    		        textStyle: {
    		            color: '#6ac5fe',
    		        },
    		        data: ['男', '女', '111']
    		    },
    		    grid: {
    		        left: '3%',
    		        right: '4%',
    		        bottom: '10%',
    		        containLabel: true
    		    },

    		    tooltip: {
    		        show: "true",
    		        trigger: 'item',
    		        axisPointer: { // 坐标轴指示器，坐标轴触发有效
    		            // type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
    		        },
    		        formatter: function(params) {
    		            if (params.seriesIndex == "3" || params.seriesIndex == "4" || params.seriesIndex == "5") {
    		                return params.name + ': ' + params.value;
    		            }
    		        }
    		    },
    		    yAxis: {
    		        type: 'value',
    		        axisTick: {
    		            show: false
    		        },
    		        axisLine: {
    		            show: true,
    		            lineStyle: {
    		                color: '#6ac5fe',
    		            }
    		        },
    		        splitLine: {
    		            show: true,
    		            lineStyle: {
    		                color: '#6ac5fe',
    		            }
    		        },
    		        axisLabel: {
    		            textStyle: {
    		                color: 'white',
    		                fontWeight: 'normal',
    		                fontSize: '12',
    		            },
    		        },
    		    },
    		    xAxis: [{
    		            type: 'category',
    		            axisTick: {
    		                show: false
    		            },
    		            axisLine: {
    		                show: true,
    		                lineStyle: {
    		                    color: '#6ac5fe',
    		                }
    		            },
    		            axisLabel: {
    		                inside: false,
    		                textStyle: {
    		                    color: '#6ac5fe',
    		                    fontWeight: 'normal',
    		                    fontSize: '12',
    		                },
    		                // formatter:function(val){
    		                //     return val.split("").join("\n")
    		                // },
    		            },
    		            data: ['会', '不会', '看情况']
    		        }, {
    		            type: 'category',
    		            axisLine: {
    		                show: false
    		            },
    		            axisTick: {
    		                show: false
    		            },
    		            axisLabel: {
    		                show: false
    		            },
    		            splitArea: {
    		                show: false
    		            },
    		            splitLine: {
    		                show: false
    		            },
    		            data: ['会', '不会', '看情况']
    		        },

    		    ],
    		    series: [{
    		            type: 'bar',
    		            xAxisIndex: 1,
    		            zlevel: 1,
    		            itemStyle: {
    		                normal: {
    		                    color: '#6ac5fe',
    		                    borderWidth: 0,
    		                    shadowBlur: {
    		                        shadowColor: 'rgba(255,255,255,0.31)',
    		                        shadowBlur: 10,
    		                        shadowOffsetX: 0,
    		                        shadowOffsetY: 2,
    		                    },
    		                }
    		            },
    		            barWidth: '10%',
    		            data: [30, 30, 30]
    		        }, {
    		            type: 'bar',
    		            xAxisIndex: 1,
    		            barGap: '100%',
    		            data: [30, 30, 30],
    		            zlevel: 1,
    		            barWidth: '10%',
    		            itemStyle: {
    		                normal: {
    		                    color: '#121847',
    		                    borderWidth: 0,
    		                    shadowBlur: {
    		                        shadowColor: 'rgba(255,255,255,0.31)',
    		                        shadowBlur: 10,
    		                        shadowOffsetX: 0,
    		                        shadowOffsetY: 2,
    		                    },
    		                }
    		            },
    		        }, {
    		            type: 'bar',
    		            xAxisIndex: 1,
    		            barGap: '100%',
    		            data: [30, 30, 30],
    		            zlevel: 1,
    		            barWidth: '10%',
    		            itemStyle: {
    		                normal: {
    		                    color: '#121847',
    		                    borderWidth: 0,
    		                    shadowBlur: {
    		                        shadowColor: 'rgba(255,255,255,0.31)',
    		                        shadowBlur: 10,
    		                        shadowOffsetX: 0,
    		                        shadowOffsetY: 2,
    		                    },
    		                }
    		            },
    		        }, {
    		            name: '男',
    		            type: 'bar',
    		            itemStyle: {
    		                normal: {
    		                    show: true,
    		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    		                        offset: 0,
    		                        color: '#6ac5fe'
    		                    }, {
    		                        offset: 1,
    		                        color: '#e12945'
    		                    }]),
    		                    barBorderRadius: 50,
    		                    borderWidth: 0,
    		                }
    		            },
    		            zlevel: 2,
    		            barWidth: '10%',
    		            data: [8, 15, 10]
    		        }, {
    		            name: '女',
    		            type: 'bar',
    		            barWidth: '10%',
    		            itemStyle: {
    		                normal: {
    		                    show: true,
    		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    		                        offset: 0,
    		                        color: '#96d668'
    		                    }, {
    		                        offset: 1,
    		                        color: '#01babc'
    		                    }]),
    		                    barBorderRadius: 50,
    		                    borderWidth: 0,
    		                }
    		            },
    		            zlevel: 2,
    		            barGap: '100%',
    		            data: [8, 17, 26]
    		        }, {
    		            name: '111',
    		            type: 'bar',
    		            barWidth: '10%',
    		            itemStyle: {
    		                normal: {
    		                    show: true,
    		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
    		                        offset: 0,
    		                        color: '#1a98f8'
    		                    }, {
    		                        offset: 1,
    		                        color: '#7049f0'
    		                    }]),
    		                    barBorderRadius: 50,
    		                    borderWidth: 0,
    		                }
    		            },
    		            zlevel: 2,
    		            barGap: '100%',
    		            data: [8, 17, 26]
    		        }

    		    ]
    		};
    	  var myChart = echarts.init(document.getElementById(emid));
    	   myChart.setOption(option);
    	
    	
    }
    $scope.carateMode4=function(emid){cateoanle(emid,300,'98.5%');}
    
    
   
    
  
    
  
    
    
    //3.事件=========================================================================================================================================================================================================================
    $scope.changemode=function(){$("#dash_main_div").empty();$scope.initmodedata();}
    
   $scope.initAll();
   setInterval(function () {
	  var char= $scope.modechar['div_18_Temp_342'];
	  if(char){
		  var open=char.getOption();
		  open .series[0].data[0].value =- (Math.random() * 30).toFixed(2) ;
		  char.setOption(open, true);
	  }
	},2000);
    
   window.addEventListener('resize', function () {
//      if($scope.modechar){
    		 angular.forEach($scope.modechar,function(obj,i){
    			var char= $scope.modechar[obj];
    			 if(char.viewtype==0){
    				 char.resize();
    			 }
    		 });
//      }
	   
	   
	   
	   
   });


    
});

