var View={
   //创建面板
   addpanel:function(pid,oid,style,title,width,height){
	  var a=['<div>'];
	   
	   $(pid).append(); 
   },		
};
var UIUTIL={
		getopen:function(mode){
			return {
			title : {
				text : mode.title,
				textStyle : {
					color : '#6ac5fe',
					fontSize : 14,
				}
			},
			backgroundColor : ' rgba(0, 0, 0, .2)',
			color : [ '#eb644b', '#313443', '#fff' ],
			toolbox : {
				feature : {
					restore : {},
					saveAsImage : {},
				},
			}
		};
	   },
		getPanle:function(mode){
			return "<div id='"+mode.emid+"'  style='"+VIEWSIZE.divStyle+";float: left;text-align: center;background: rgba(0, 0, 0, .2) !important; border: 1px solid transparent;    margin: 0px 10px 10px 0px;'></div>";
		},
		getcav:function(mode){
			return'<div id="'+emid+'" style="width:'+mode.info.w+'px;height:'+mode.info.h+'px;border:1px solid #ccc;position:absolute;left:'+(mode.info.x)+'px;top:'+(mode.info.y)+'px;"><div id="pre_'+emid+'"style="width:100%;height:100%;font-size:'+deviced[i].fontSize+'px;background-image:url('+deviced[i].openImgurl+');background-repeat:no-repeat;background-size:contain;"></div></div>';
		}
		
}
//组态模型
var UIABSVIEW={
		        18:{
		        	'Temp':{
		        		'0':function(mode,emid){
		        			if(emid){
		        				
		        			}else{
			        			var preHtml = UIUTIL.getcav(mode);
			        			return [mode.emid,preHtml];
		        			}
		        			
		        			
		        			
		        			
		        		}
		        	}
		        }
		
};
//列表模型
var UILISTVIEW={
        18:{
        	'Temp':{
        		'0':function(mode,objmode){
        			
        			if(objmode){
        				var char=objmode[mode.emid];
        				var open=char.getOption();
        				 open .series[0].data[0].name =mode.data[0].value+'℃' ;
        				 open.series[0].data[0].value=mode.data[0].value+30;
        				 char.setOption(open);
        			}else{
           			    var html= UIUTIL.getPanle(mode);
           			    $("#dash_main_div").append(html);
           		        var option=	 UIUTIL.getopen(mode);
           		         option.series= [{ name:mode.title,type:'pie', radius: [80,84],avoidLabelOverlap: false,label: {normal: { show: true,position: 'center',color:'#1E90FF', fontSize:28}},labelLine: {normal: { show: false } }, data: [{value: 65, name: '-35.22℃' }, { value: 35, name: ''} ]}];
           			     var myChart = echarts.init(document.getElementById(mode.emid));
            	  	     myChart.setOption(option);
            			 return [mode.emid,myChart]
        			   }
        			 
        		},
        		'1':function(mode,objmode){//表盘
        			if(objmode){
        				  var char=objmode[mode.emid];
        				  var open=char.getOption();
        				  open .series[0].data[0].value =mode.data[0].value;
        				  char.setOption(open, true);
        			}else{
		    			 var html=UIUTIL.getPanle(mode);
		    			 $("#dash_main_div").append(html);
		    			 var option= {
		    				    min:-100,max:100,
		    				    series: [
		    				        {
		    				            name: mode.title,
		    				            type: 'gauge',
		    				            detail: {formatter:'{value}℃',padding:[90,0,0,0]},
		    				            min:-30,max:80,
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
		    			 $.extend(option,  UIUTIL.getopen(mode));//合并
		    			 UIUTIL.getopen(mode);
		    			 
		    			var myChart = echarts.init(document.getElementById(mode.emid));
		    	  	    myChart.setOption(option);
		    			return [mode.emid,myChart]
        		   }
        		 },
			   '2':function(mode,objmode){
	       			if(objmode){
	       				  var char=objmode[mode.emid];
	       				  var open=char.getOption();
	       				  open .series[0].data.splice(0,1);
	       				  open .series[0].data.push({ value: [new Date(), mode.data[0].value] });
	       				  char.setOption(open, true);
	       			}else{
	       			 var html=UIUTIL.getPanle(mode);
	    			 $("#dash_main_div").append(html);
					  	var option = {
							    tooltip: {trigger: 'axis', axisPointer: { animation: false} },
							    xAxis: {
							        type: 'time',
							        splitLine: { show: false},
					                axisLine: { lineStyle: {  color: '#6ac5fe', } },
							    },
							    yAxis: {
							        type: 'value',
							        axisLine: { lineStyle: {  color: '#6ac5fe', } },
							    },
							    series: [{ name: '22', type: 'line', showSymbol: false, hoverAnimation: false,data: mode.data }]
							};
					  	    $.extend(option,  UIUTIL.getopen(mode));//合并
					     	var myChart = echarts.init(document.getElementById(mode.emid));
		    	  	        myChart.setOption(option);
		    			    return [mode.emid,myChart]
	       			}
				 }
        	}
        }
};
var VIEW3DMode={
		
		
		
		
}