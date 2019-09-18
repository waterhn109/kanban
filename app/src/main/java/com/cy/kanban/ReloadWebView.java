package com.cy.kanban;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ReloadWebView extends TimerTask {
    Activity context;
    Timer timer;
    WebView wv;
    String mac;
    public List<String> urls = new ArrayList<>();
  //  private Handler handler = new Handler(Looper.getMainLooper());
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    wv.loadUrl(msg.obj.toString());
                    context.setContentView(wv);
                    wv.reload();
            }
        }
    };

    public ReloadWebView(Activity context, int seconds, WebView wv,String mac) {
        this.context = context;
        this.wv = wv;
        this.mac = mac;

        timer = new Timer();
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        /* execute the first task after seconds */
        timer.schedule(this,
                seconds * 2000,  // initial delay
                seconds * 1800000); // subsequent rate

        /* if you want to execute the first task immediatly */
        /*
        timer.schedule(this,
                0,               // initial delay null
                seconds * 1000); // subsequent rate
        */
    }

    @Override
    public void run() {
        if(context == null || context.isFinishing()) {
            // Activity killed
            this.cancel();
            return;
        }

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                // wv.loadUrl(urls.get(i));
                //设置Web视图

                //获取mac地址的urls
                urls = getUlrs.getulrs(mac);
                if (!urls.isEmpty()) {
                    while (true) {
                    for (int i=0;i<urls.size();i++) {


                        Message message = new Message();
                        //区分标志
                        message.what=1;
                        //传参到ui线程
                        message.obj=urls.get(i);
                        handler.handleMessage(message);

                     //   wv.loadUrl("https://www.baidu.com/");
                       // wv.loadUrl(urls.get(i));
                        //设置Web视图
                      //  context.setContentView(wv);
                      //  wv.reload();
                try {
                    Thread.sleep(1800000);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                        }
                    }
                }
                else
                {
                    return;
                }
            }

        });
    }

}
