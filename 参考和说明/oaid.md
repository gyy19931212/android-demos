
## demo-oaid：Android OAID 接入

参考地址：
https://mtj.baidu.com/static/userguide/book/android/oaid.html
https://blog.csdn.net/haovip123/article/details/101679971

集成步骤：
1，就按照百度统计上的步骤（https://mtj.baidu.com/static/userguide/book/android/oaid.html），结合本demo

2，重要点：
 //先执行
        DevicesUtil.init();

        //后执行
                DevicesUtil.getOaid();