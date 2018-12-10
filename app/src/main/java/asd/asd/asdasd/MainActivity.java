package asd.asd.asdasd;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.gson.Gson;

import asd.asd.asdasd.api.PersistentCookieStore;
import asd.asd.asdasd.api.PublicApi;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private BottomWebView web;
    private static final String TAG = "MainActivity";
    private List<TaskBean.ResultBean.RequestipBean> requestip=new ArrayList<>();
    public  List<TaskBean.ResultBean.TaskitemBean> taskitem=new ArrayList<>();
    private int ipnum=0;
    private int tasknum=0;
    private boolean isSucces=true;

    private String strurl[]={
            "http://www.e160xm.cn/",

    };



    String [] str_ua={
           " Mozilla/5.0 (Linux; Android 8.1; PAR-AL00 Build/HUAWEIPAR-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/WIFI Language/zh_CN Process/tools ",
            "Mozilla/5.0 (Linux; Android 8.1; EML-AL00 Build/HUAWEIEML-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.143 Crosswalk/24.53.595.0 XWEB/358 MMWEBSDK/23 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.0; KNT-AL20 Build/HUAWEIKNT-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044203 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060739) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; DUK-AL20 Build/HUAWEIDUK-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044353 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/WIFI Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 8.0; MHA-AL00 Build/HUAWEIMHA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/NON_NETWORK Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 8.0; MHA-AL00 Build/HUAWEIMHA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/4G Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 5.1.1; vivo X6S A Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044207 Mobile Safari/537.36 MicroMessenger/6.7.3.1340(0x26070332) NetType/4G Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 7.0; Redmi Note 4X Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/WIFI Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 7.1.1; vivo X20A Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.1; PAR-AL00 Build/HUAWEIPAR-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/4G Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 8.0; FIG-AL10 Build/HUAWEIFIG-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.3.1360(0x26070333) NetType/4G Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 6.0.1; KIW-AL10 Build/HONORKIW-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060739) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.1; BLA-AL00 Build/HUAWEIBLA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.0; KNT-AL20 Build/HUAWEIKNT-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060739) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; BKL-AL20 Build/HUAWEIBKL-AL20; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 wxwork/2.5.8 MicroMessenger/6.3.22 NetType/4G Language/zh",


            "Mozilla/5.0 (Linux; Android 7.1.1; OD105 Build/NMF26F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044208 Mobile Safari/537.36 wxwork/2.4.9 MicroMessenger/6.3.22 NetType/4G Language/zh",
            "Mozilla/5.0 (Linux; Android 8.0; VKY-AL00 Build/HUAWEIVKY-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 wxwork/2.5.8 MicroMessenger/6.3.22 NetType/WIFI Language/zh",
            "Mozilla/5.0 (Linux; Android 8.0; FIG-AL10 Build/HUAWEIFIG-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; BLN-AL40 Build/HONORBLN-AL40; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044207 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 5.1.1; vivo X6S A Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044207 Mobile Safari/537.36 MicroMessenger/6.7.3.1340(0x26070331) NetType/4G Language/zh_CN Process/tools",
            "Mozilla/5.0 (Linux; Android 8.0; VTR-AL00 Build/HUAWEIVTR-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060739) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.1.1; OPPO R11st Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.1.1; vivo X20A Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.1; ALP-AL00 Build/HUAWEIALP-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x26070264) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.0; Redmi Note 4X Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.0; FRD-AL10 Build/HUAWEIFRD-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; HWI-AL00 Build/HUAWEIHWI-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; HWI-AL00 Build/HUAWEIHWI-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.6.6.1300(0x26060638) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; MHA-AL00 Build/HUAWEIMHA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x26070233) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; FIG-AL10 Build/HUAWEIFIG-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044208 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 7.1.1; MI MAX 2 Build/NMF26F; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044304 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.0; SM-G9500 Build/R16NW; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044208 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/WIFI Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.1; EML-AL00 Build/HUAWEIEML-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.143 Crosswalk/24.53.595.0 XWEB/257 MMWEBSDK/23 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",
            "Mozilla/5.0 (Linux; Android 8.1; BLA-AL00 Build/HUAWEIBLA-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044208 Mobile Safari/537.36 MicroMessenger/6.7.2.1340(0x2607023A) NetType/4G Language/zh_CN",

    };







    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            switch (msg.what) {
                case 1:
                    Log.e(TAG, "handleMessage: 1" );
                    if (ipnum<requestip.size()){
//                        做单个任务
                        loadWeb(requestip.get(ipnum),taskitem.get(tasknum).getResources().get(getRand(2)));
                    }else {
                        //一轮做完
                        tasknum++;
                        if(tasknum<taskitem.size()){
                            ipnum=0;
                            mHandler.sendEmptyMessage(1);
                        }else {
//                            任务做完\
                            mHandler.sendEmptyMessage(2);
                        }
                    }




                    break;
                case 2:

                    Log.e(TAG, "handleMessage: 2" );
                    PublicApi.getRefreshApi(new PublicApi.ResponseListener() {
                        @Override
                        public void success(Object o) {

                            TaskBean taskBean = (TaskBean) o;

                            ipnum=0;
                            tasknum=0;
                            requestip = taskBean.getResult().getRequestip();
                            taskitem = taskBean.getResult().getTaskitem();

                            mHandler.sendEmptyMessage(1);

                        }

                        @Override
                        public void error(String s) {

                        }
                    });
                    break;
                case 3:
                    String str="{\n" +
                            "  \"code\": 0,\n" +
                            "  \"msg\": \"Success\",\n" +
                            "  \"result\": {\n" +
                            "    \"taskid\": 1,\n" +
                            "    \"requestip\": [\n" +
                            "      {\n" +
                            "        \"ip\": \"123.123.123.121\",\n" +
                            "        \"port\": \"1234\"\n" +
                            "      },\n" +
                            "      {\n" +
                            "        \"ip\": \"123.123.123.122\",\n" +
                            "        \"port\": \"4567\"\n" +
                            "      },\n" +
                            "      {\n" +
                            "        \"ip\": \"123.123.123.123\",\n" +
                            "        \"port\": \"8912\"\n" +
                            "      }\n" +
                            "    ],\n" +
                            "    \"taskitem\": [\n" +
                            "      {\n" +
                            "        \"id\": 1,\n" +
                            "        \"forwardplatformid\": 2,\n" +
                            "        \"resources\": [\n" +
                            "          {\n" +
                            "            \"url\": \"http://wwww.bbb.com\",\n" +
                            "            \"useragent\": \"xxxsssscccc\",\n" +
                            "            \"device\": \"Android\",\n" +
                            "            \"system\": \"8\",\n" +
                            "            \"platform\": \"wx\"\n" +
                            "          },\n" +
                            "          {\n" +
                            "            \"url\": \"http://wwww.bbb.com\",\n" +
                            "            \"useragent\": \"xxxsssscccc\",\n" +
                            "            \"device\": \"Android\",\n" +
                            "            \"system\": \"8\",\n" +
                            "            \"platform\": \"wx\"\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      },\n" +
                            "      {\n" +
                            "        \"id\": 2,\n" +
                            "        \"forwardplatformid\": 3,\n" +
                            "        \"resources\": [\n" +
                            "          {\n" +
                            "            \"url\": \"http://wwww.bbb.com\",\n" +
                            "            \"useragent\": \"xxxsssscccc\",\n" +
                            "            \"device\": \"Android\",\n" +
                            "            \"system\": \"8\",\n" +
                            "            \"platform\": \"wx\"\n" +
                            "          },\n" +
                            "          {\n" +
                            "            \"url\": \"http://wwww.bbb.com\",\n" +
                            "            \"useragent\": \"xxxsssscccc\",\n" +
                            "            \"device\": \"Android\",\n" +
                            "            \"system\": \"8\",\n" +
                            "            \"platform\": \"wx\"\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      }\n" +
                            "    ]\n" +
                            "  }\n" +
                            "}";

                    TaskBean taskBean = new Gson().fromJson(str,TaskBean.class);

                    ipnum=0;
                    tasknum=0;
                    requestip = taskBean.getResult().getRequestip();
                    taskitem = taskBean.getResult().getTaskitem();
                    mHandler.sendEmptyMessage(1);

                    break;
                case 4:

//                 mHandler.sendEmptyMessage(3);
                    PublicApi.getRefreshApiIP(new PublicApi.ResponseListener() {
                        @Override
                        public void success(Object o) {

                            IpBean ipBean = (IpBean) o;
                            List<IpBean.MsgBean> msg = ipBean.getMsg();
//                            ProxySettings.setProxy(web,msg.get(0).getIp(),new Integer(msg.get(0).getPort()),null);
                            web.getSettings().setUserAgentString(str_ua[num]);
                            String url = strurl[0];
                            String userAgentString = web.getSettings().getUserAgentString();
                            Log.d(TAG, url+"    loadWeb: ua   :" +userAgentString);
                            Map<String, String> extraHeaders; extraHeaders = new HashMap<String, String>();
                            extraHeaders.put("X-Forwarded-For", msg.get(0).getIp());
                            extraHeaders.put("X-Forwarded-For-Pound",msg.get(0).getIp());
                            extraHeaders.put("X-Requested-With", "");


//                            CookieManager cookieManager = CookieManager.getInstance();
//                            String cookieStr  = cookieManager.getCookie(url);

                            removeCookie(MainActivity.this);
//                            synCookies(MainActivity.this,url,"uid="+System.currentTimeMillis());
//                            syncCookie(MainActivity.this,url);
                            web.loadUrl(url,extraHeaders);
//                            viewById.setText(num+" ");

                            CookieManager cookieManager = CookieManager.getInstance();
                            String cookieStr  = cookieManager.getCookie(url);

                            viewById.setText(cookieStr+"");
                            num++;
                        }

                        @Override
                        public void error(String s) {

                        }
                    });
                    break;
            }
        }
    };

//    /**
//     * 设置Cookie
//     *
//     * @param context
//     * @param url
//     * @param cookie  格式：uid=21233 如需设置多个，需要多次调用
//     */
//    public void  synCookies(Context context, String url, String cookie) {
//        CookieSyncManager.createInstance(context);
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setAcceptCookie(true);
//        cookieManager.setCookie(url, cookie+";Domain=hotspot.******;Path=/");//cookies格式自定义
//        CookieSyncManager.getInstance().sync();
//    }

    /**
     * 给WebView同步Cookie
     *
     * @param context 上下文
     * @param url     可以使用[domain][host]
     */
    private void syncCookie(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();// 移除旧的[可以省略]
//        List<HttpCookie> cookies = new PersistentCookieStore(context).getCookies();// 获取Cookie[可以是其他的方式获取]
//        for (int i = 0; i < cookies.size(); i++) {
//            HttpCookie cookie = cookies.get(i);
//        }
            String value = "qwe" + "=" + "123";
            cookieManager.setCookie(url, value);
        CookieSyncManager.getInstance().sync();// To get instant sync instead of waiting for the timer to trigger, the host can call this.
    }


    /**
     * 清除Cookie
     *
     * @param context
     */
    public static void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

     TextView viewById;
    private int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        web = (BottomWebView) findViewById(R.id.web);
        initWebView();


      viewById = (TextView) findViewById(R.id.tv);

                viewById.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 mHandler.sendEmptyMessage(4);


             }
         });


    }

    private int getRand(int num){
        return getRand(0,num);
    }
    private int getRand(int base,int num){
        return (int) (base+Math.random()*num);
    }

    private void loadWeb(TaskBean.ResultBean.RequestipBean requestipBean, TaskBean.ResultBean.TaskitemBean.ResourcesBean resourcesBean) {

        Log.d(TAG, "loadWeb ip:"+requestipBean.getIp()+"  port:"+requestipBean.getPort()+"  url:"+resourcesBean.getUrl());
        isSucces=true;
        ProxySettings.setProxy(web,requestipBean.getIp(),new Integer(requestipBean.getPort()),null);
        String userAgentString = web.getSettings().getUserAgentString();
        Log.d(TAG, "loadWeb: ua   :" +userAgentString);
        web.getSettings().setUserAgentString(userAgentString+"@@@@@@@@@@@@@@@@@@@@@@@@@");
        web.loadUrl("");
    }



    private int erro=0;
    private void initWebView() {


        final WebSettings mWebSettings = web.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        web.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);



        web.clearCache(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setAppCacheEnabled(false);
//        mWebSettings.getco

        mWebSettings.setPluginState(WebSettings.PluginState.ON);
        mWebSettings.setSupportZoom(true); // 支持缩放
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWebSettings.setAllowFileAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }



        web.setWebViewClient(new WebViewClient() {


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
//
//                WebResourceResponse webResourceResponse = super.shouldInterceptRequest(view, request);
//                InputStream data = webResourceResponse.getData();
//
//                byte[] bs = new byte[1024];
//                //定义每次读取的长度
//                int len = -1;
//
//                String str="";
//                try {
//                    while ((len = data.read(bs)) != -1) {
//                        str=str+new String(bs,1,len);
//                    }
//                    Log.e(TAG, " "+str );
//                }catch (Exception e1) {
//                    e1.printStackTrace();
//                }
                Log.e(TAG, "@@@"+request.getUrl() );

                return super.shouldInterceptRequest(view, request);
            }


            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

                if (url.endsWith(".js")) {
//                Log.e(TAG, "shouldInterceptRequest: "+url );
//                    try {
//                        return new WebResourceResponse("text/javascript", "utf-8", getAssets().open("2.js"));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                }
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                mHandler.removeMessages(1);
                Log.e(TAG, "onReceivedError: ");
                isSucces=false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                Log.e(TAG, "onReceivedSslError: ");

                super.onReceivedSslError(view, handler, error);
                handler.proceed();

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        web.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
                //当进度走到100的时候做自己的操作，我这边是弹出dialog
                Log.e(TAG, "onProgressChanged: "+progress +" isSuccess:  "+isSucces);
                if(progress == 100&&isSucces){

//                    CookieManager cookieManager = CookieManager.getInstance();
//                    String cookieStr  = cookieManager.getCookie(strurl[0]);
//                    viewById.setText(cookieStr+"");

//                    mHandler.removeMessages(1);
////                    mHandler.sendEmptyMessageDelayed(1,getRand(10,4)*1000);
////                    web.scrollToBottom();
////                    ipnum++;
//                    mHandler.sendEmptyMessage(4);

                }
//                else if (progress == 100&&!isSucces){
//
//                    erro+=1;
//                    if (erro==5){
//                        erro=0;
//                        mHandler.sendEmptyMessage(2);
//                    }else {
//                        mHandler.sendEmptyMessage(1);
//                    }
//                }

            }
        });
    }
}
