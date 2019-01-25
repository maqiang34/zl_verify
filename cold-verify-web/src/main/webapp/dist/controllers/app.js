/*
* store 360 system
* date:2017-09-13
* */
//
var app = angular.module('storeWeb', ['ui.bootstrap', 'ui.router', 'oc.lazyLoad','ngFileUpload' , 'ngCookies']),
    sys = {version: "1.2.3"};
//对模块的注册
app.config(["$provide", "$compileProvider", "$controllerProvider", "$filterProvider", "$stateProvider", function ($provide, $compileProvider, $controllerProvider, $filterProvider, $stateProvider) {
    app.controller = $controllerProvider.register;
    app.directive = $compileProvider.directive;
    app.filter = $filterProvider.register;
    app.factory = $provide.factory;
    app.service = $provide.service;
    app.constant = $provide.constant;
    app.stateProvider = $stateProvider;//接管后对权限预处理
}]);
//
app.run(function ($rootScope, $state, $http, $cookies, $templateCache) {

    $http.get('/i/sys/user/findUser').success(function (data) {
        if (data == null || data == "") {
            console.log("未找到该用户！");
            document.location.href = "login.html";
            return;
        }
        $rootScope.user = data;
    });//初始化user
//	  $http.get('/i/systemController/getVersion').success(function(data) {
//		  window.localStorage.clear();
//		  $templateCache.remove(current.templateUrl);
//	  });//初始化版本

    $rootScope.logout = function () {
        $http.get('/i/user/logout').success(function (data) {
        });
        $rootScope.user = undefined;
        window.sessionStorage.clear();
        $cookieStore.remove('token');
        console.log("退出！");
        window.location.href = "login.html";
    };

//      userService.setUser(user);
//	    userService.setStorage();
});


//app.factory('userService', ['$rootScope', '$state', '$http','$cookies',function ($rootScope, $state,$http,$cookies) {
////    return {
////        setUser: function (user) {
////
////        }
////    };
//}]);

app.config(function ($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    $urlRouterProvider.otherwise("/monitor");
    $stateProvider.state('monitor', {//监控盘展示
        url: '/monitor',
        controller: 'monitor',
        templateUrl: 'dist/view/monitor/monitor.html',
        resolve: {
            deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                return $ocLazyLoad.load("dist/controllers/monitor/monitor.js");
            }]
        }
    })
        .state('verifyUpdate', {//验证数据库文件上传
            url: '/verifyUpdate',
            controller: 'verifyUpdate',
            templateUrl: 'dist/view/verify/verifyUpdate.html',
            resolve: {
                deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                    return $ocLazyLoad.load("dist/controllers/verify/verifyUpdate.js");
                }]
            }
        })
        .state('fullData', {//验证数据库文件解析下载
            url: '/fullData',
            controller: 'fullData',
            templateUrl: 'dist/view/verify/fullData.html',
            resolve: {
                deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                    return $ocLazyLoad.load("dist/controllers/verify/fullData.js");
                }]
            }
        })  .state('fullData1', {//验证数据库文件解析下载
        url: '/fullData1',
        controller: 'fullData1',
        templateUrl: 'dist/view/verify/fullData1.html',
        resolve: {
            deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                return $ocLazyLoad.load("dist/controllers/verify/fullData1.js");
            }]
        }
    })
        .state('reportUpdate', {//模板文件
            url: '/reportUpdate',
            controller: 'reportUpdate',
            templateUrl: 'dist/view/report/reportUpdate.html',
            resolve: {
                deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                    return $ocLazyLoad.load("dist/controllers/report/reportUpdate.js");
                }]
            }
        })
        .state('getVerData', {//验证数据库文件解析下载
            url: '/getVerData',
            controller: 'getVerData',
            templateUrl: 'dist/view/monitor/verifyUpdate.html',
            resolve: {
                deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                    return $ocLazyLoad.load("dist/controllers/monitor/verifyUpdate.js");
                }]
            }
        })
        .state('setParameter', {// 参数配置
            url: '/setParameter',
            controller: 'setParameter',
            templateUrl: 'dist/view/para/setParameter.html',
            resolve: {
                deps: ["$ocLazyLoad", function ($ocLazyLoad) {
                    return $ocLazyLoad.load("dist/controllers/para/setParameter.js");
                }]
            }
        })

});

$('.sidebar-menu li').click(function (open) {
    $(".sidebar-menu li").removeClass("active");
    $(open.target).addClass('active');
});
