package com.cy.kanban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.ArrayList;

public class WebViewActivity  extends Activity {

  public  String mac ;
  public  WebView  webview;
  public ArrayList<String> urls = new ArrayList<>();

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
        urls = intent.getStringArrayListExtra("urls");
        if (!urls.isEmpty()) {
            webview.loadUrl(urls.get(0));
            //设置Web视图
            setContentView(webview);
            //    webview.reload();

        }
           /* Runnable runnable = new Runnable() {
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
            };*/
    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("back", true);
        startActivity(intent);
        finish();
    }
}
