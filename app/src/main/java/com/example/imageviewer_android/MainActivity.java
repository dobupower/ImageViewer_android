package com.example.imageviewer_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev,btnNext;
    myPictureView myPicture;
    int curNum=1;
    File[] imageFiles;
    String imageFname;
    TextView tvNum, tvSize;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지뷰어");
        ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        myPicture=(myPictureView)findViewById(R.id.myPictureView1);
        tvNum = (TextView) findViewById(R.id.tvNum);
        tvSize= (TextView) findViewById(R.id.tvSize);
        imageFiles= new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFname=imageFiles[0].toString();
        myPicture.imagePath=imageFname;
        tvSize.setText(Integer.toString(imageFiles.length));
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum<=0) {
                    curNum = imageFiles.length - 1;
                }else {
                    curNum--;
                }
                int buff = curNum+1;
                tvNum.setText(Integer.toString(buff));
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curNum >= imageFiles.length -1){
                    curNum = 0;
                }else {
                    curNum++;
                }
                int buff = curNum+1;
                tvNum.setText(Integer.toString(buff));
                imageFname = imageFiles[curNum].toString();
                myPicture.imagePath = imageFname;
                myPicture.invalidate();
            }
        });
    }
}