package com.cy.kanban;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private ImageView iv_qrcode;
    private android.graphics.Bitmap qrcode_bitmap;//生成的二维码
    private String  mac  ;
    //获取到的ulr组
    private List<String> urls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_qrcode = findViewById(R.id.iv_qrcode);
        mac = getMac.getMac(this);
        generateQrcodeAndDisplay(mac);
        Toast.makeText(this, "正在连接看板服务器...", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable(){
            @Override
            public void run() {
                urls = getUlrs.getulrs(mac);
                if(!urls.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                    intent.putExtra("mac", mac);
                    startActivity(intent);
                    finish();
                }

            }
        }).start();


      //  bgrun();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){

        finish();
        closeContextMenu();
    }

    /**
     * 生成二维码并显示
     */
    private void generateQrcodeAndDisplay(String mac) {

        qrcode_bitmap = ZXingUtils.createQRImage(mac, 400,  400);
        iv_qrcode.setImageBitmap(qrcode_bitmap);
    }




}
