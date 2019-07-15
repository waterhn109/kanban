package com.cy.kanban;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

public class WebViewActivity  extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WebView  webview = new WebView(this);
        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页
        //webview.loadUrl("file:///android_asset/index.html");//显示本地网页
        //显示远程网页
        webview.loadUrl("http://192.168.100.133:18888/WebReport/decision/view/form?viewlet=ZMT/SFC/F-KB-SFC-3.frm&plant=KYN&jihuahao=RW01-19061903&riqi=2019/06/26&gongdanhao=NGMB-19060368&banzu=N01TP10DA");
        //设置Web视图
        setContentView(webview);

      //  setContentView(R.layout.web_main);
        // 初始化各控件
     //   WebView webView= (WebView) findViewById(R.id.webView);

     //   webView.loadUrl("http://www.baidu.com");


    }


    @Override
    public void onBackPressed(){
    //    Intent intent = new Intent(this, MainActivity.class);
    //    intent.putExtra("from_guaci", true);
    //    startActivity(intent);
        finish();
    }
}
