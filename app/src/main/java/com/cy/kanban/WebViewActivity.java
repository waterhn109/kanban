package com.cy.kanban;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class WebViewActivity  extends Activity {

  public  String mac ;
  public  WebView  webview;
  public List<String> urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         webview = new WebView(this);

        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //修改系统策略，放开所有的权限
        //代码添加到onCreate回调方法中即可

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //创建属于主线程的handler
       // handler =new Handler();

        //加载需要显示的网页
        //webview.loadUrl("file:///android_asset/index.html");//显示本地网页
        //显示远程网页
        Intent intent=getIntent();
        mac = intent.getStringExtra("mac");
       // new ReloadWebView(this, 1, webview,mac);
       // bgrun();
        urls = getUlrs.getulrs(mac);
        if (!urls.isEmpty()) {


            webview.loadUrl(urls.get(0));
            //设置Web视图
            setContentView(webview);
            webview.reload();


            Runnable runnable = new Runnable() {
                public void run() {
                    while (true) {
                        webview.reload();
                        try {
                            Thread.sleep(1800000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
        }

       /* Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                webview.reload();
            }
        }, 2000,20000);// 设定指定的时间time,此处为60min*/
       // webview.loadUrl(url);
        //设置Web视图
      //  setContentView(webview);

      //  setContentView(R.layout.web_main);
        // 初始化各控件
     //   WebView webView= (WebView) findViewById(R.id.webView);

     //   webView.loadUrl("http://www.baidu.com");


    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("back", true);
        startActivity(intent);
        finish();
    }
}
