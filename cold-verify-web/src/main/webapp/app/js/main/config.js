/**
 * Created by gushaohua on 16/6/17.
 */
var PUBLIC_LANGUAGE_CONF = {};

// if(!window.localStorage.UYUN_LANGUAGE_CONSTANT){
    window.localStorage.setItem('UYUN_LANGUAGE_CONSTANT','zh_CN');
// }


switch (window.localStorage.UYUN_LANGUAGE_CONSTANT){
    case 'zh_CN': PUBLIC_LANGUAGE_CONF = {
        "productId": 1001,
        "items": [
            {
                "title": "总览",
                "icon": "iconfont icon-zonglan",
                "route": "/overview",
            },
            {
                "title": "仪表盘",
                "icon": "iconfont icon-yibiaopan",
                "route": "/dashboard"
            },
            {
                "title": "事件台",
                "icon": "iconfont icon-shijiantai",
                "route": "/events"
            },
            {
                "title": "资源库",
                "icon": "iconfont icon-ziyuanku",
                "route": "/sources"
            },
            {
                "title": "监测器",
                "icon": "iconfont icon-jianceqi",
                "route": "/monitor"
            }
            // ,
            // {
            //     "title": "报表",
            //     "icon": "iconfont icon-baobiao",
            //     "route": "/report"
            // },
            
        ],
        "sub_items": [
            {
                "title": "换肤",
                "icon": "iconfont icon-huanfu",
                "route": "/"
            },
            {
                "title": "部署",
                "icon": "iconfont icon-bushu",
                "route": "/deploy"
            },
            {
                "title": "帮助",
                "icon": "iconfont icon-bangzhu",
                "route": "/help"
            }
        ]
    };
    break;
    case 'en_US':PUBLIC_LANGUAGE_CONF = {
        "productId": 1001,
        "items": [
            {
                "title": "Overview",
                "icon": "iconfont icon-zonglan",
                "route": "/overview"
            },
            {
                "title": "Dashborads",
                "icon": "iconfont icon-yibiaopan",
                "route": "/dashboard"
            },
            {
                "title": "Events",
                "icon": "iconfont icon-shijiantai",
                "route": "/events"
            },
            {
                "title": "ResourceLab",
                "icon": "iconfont icon-ziyuanku",
                "route": "/sources"
            },
            {
                "title": "Monitors",
                "icon": "iconfont icon-jianceqi",
                "route": "/monitor"
            }
            // ,
            // {
            //     "title": "Report",
            //     "icon": "iconfont icon-baobiao",
            //     "route": "/report"
            // }
            
        ],
        "sub_items": [
            {
                "title": "Deploy",
                "icon": "iconfont icon-bushu",
                "route": "/deploy"
            },
            {
                "title": "Help",
                "icon": "iconfont icon-bangzhu",
                "route": "/help"
            }
        ]
    };break;
}


window.PUBLIC_MENU_CONF = PUBLIC_LANGUAGE_CONF;