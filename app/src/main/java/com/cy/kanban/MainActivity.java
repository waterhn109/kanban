package com.cy.kanban;

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
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private ImageView iv_qrcode;
    private ImageView picture_logo, picture_black;//logo，代替黑色色块的图片

    private String barcode;//二维码内容
    private int width, height;//宽度，高度
    private String error_correction_level, margin;//容错率，空白边距
    private int color_black, color_white;//黑色色块，白色色块

    public static final int TAKE_PHOTO = 1;//拍照
    public static final int CHOOSE_PHOTO = 2;//从相册选择图片
    private Uri imageUri;
    private Bitmap logoBitmap;//logo图片
    private Bitmap blackBitmap;//代替黑色色块的图片
    private int remark;//标记返回的是logo还是代替黑色色块图片

    private android.graphics.Bitmap qrcode_bitmap;//生成的二维码
    private String mac ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_qrcode = findViewById(R.id.iv_qrcode);
        mac = getMac.getMac(this);
        generateQrcodeAndDisplay(mac);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
     //   setSupportActionBar(toolbar);

     //   FloatingActionButton fab = findViewById(R.id.fab);

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
    }

    /**
     * 生成二维码并显示
     */
    private void generateQrcodeAndDisplay(String mac) {
        barcode = mac;
        qrcode_bitmap = ZXingUtils.createQRImage(mac, 400,  400);
    //    qrcode_bitmap = QRCodeUtil.createQRCodeBitmap(barcode, 650, 650, "UTF-8",
    //            "H", margin, color_black, color_white, logoBitmap, 0.2F, blackBitmap);
        iv_qrcode.setImageBitmap(qrcode_bitmap);
    }

}
