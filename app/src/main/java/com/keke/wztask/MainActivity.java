package com.keke.wztask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.keke.wztask.api.PublicApi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomWebView web;
    private static final String TAG = "MainActivity";
    private List<TaskBean.ResultBean.RequestipBean> requestip=new ArrayList<>();
    public  List<TaskBean.ResultBean.TaskitemBean> taskitem=new ArrayList<>();
    private int ipnum=0;
    private int tasknum=0;
    private boolean isSucces=true;


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
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        web = (BottomWebView) findViewById(R.id.web);
        initWebView();








         findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                 mHandler.sendEmptyMessage(3);

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
        web.loadUrl(resourcesBean.getUrl());
    }



    private int erro=0;
    private void initWebView() {


        final WebSettings mWebSettings = web.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        web.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebSettings.setAppCacheEnabled(true);

        mWebSettings.setPluginState(WebSettings.PluginState.ON);
        mWebSettings.setSupportZoom(true); // 支持缩放
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWebSettings.setAllowFileAccessFromFileURLs(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }



        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return super.shouldOverrideUrlLoading(view, url);
            }


            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) {

                    return super.shouldInterceptRequest(view, url);
                } else {
                    if (url.indexOf(".qq.com") > -1) {
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(in);
                        return null;
                    } else {
                        return super.shouldInterceptRequest(view, url);
                    }
                }
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
                    mHandler.removeMessages(1);
                    mHandler.sendEmptyMessageDelayed(1,getRand(10,4)*1000);
                    web.scrollToBottom();
                    ipnum++;

                }else if (progress == 100&&!isSucces){

                    erro+=1;
                    if (erro==5){
                        erro=0;
                        mHandler.sendEmptyMessage(2);
                    }else {
                        mHandler.sendEmptyMessage(1);
                    }
                }
            }
        });
    }
}
