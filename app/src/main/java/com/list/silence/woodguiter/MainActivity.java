package com.list.silence.woodguiter;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    protected boolean checkBox(int a[], int b[]) {//这个函数用来判断输入密码是否和预设相同，相同就返回true
        for (int i = 0; i < 10; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    public MediaPlayer mSun;//装you are my shine
    private int Secret[] = new int[10];//用来装密码的10位数组
    private int Secret_r[] = {2, 3, 5, 6, 6, 6, 5, 6, 3, 3};//这个是预设的10位密码
    private int p = 0;//数组中要装入数字的位置
    private Button Bt_box[] = new Button[9];//用来装按钮的数组，一共9个
    private SoundPool mPool;//声明一个处理声音的SoundPool
    private int soundID[] = new int[6];//用来装音频文件的数组

    protected void hideBottomUIMenu() {//隐藏虚拟按键，并且设置全屏的方法

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//引用布局文件
        this.hideBottomUIMenu();//调用上面隐藏虚拟按键，并且设置全屏的方法
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏


        //在布局文件中找到对应按钮，并依次装入数组
        Bt_box[0] = findViewById(R.id.x_1);//从1到6都是琴弦
        Bt_box[1] = findViewById(R.id.x_2);
        Bt_box[2] = findViewById(R.id.x_3);
        Bt_box[3] = findViewById(R.id.x_4);
        Bt_box[4] = findViewById(R.id.x_5);
        Bt_box[5] = findViewById(R.id.x_6);
        Bt_box[6] = findViewById(R.id.back_01);//主界面的返回按钮
        Bt_box[7] = findViewById(R.id.more_01);//主界面的更多按钮
        Bt_box[8] = findViewById(R.id.clean_01);//主界面隐藏的清除按钮

        mSun = MediaPlayer.create(this, R.raw.sunshine01);
        mSun.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);

        //将app\src\main\res\raw文件夹下的音频文件装入声音数组
        soundID[0] = mPool.load(this, R.raw.g01, 1);
        soundID[1] = mPool.load(this, R.raw.a01, 1);
        soundID[2] = mPool.load(this, R.raw.a02, 1);
        soundID[3] = mPool.load(this, R.raw.g04, 1);
        soundID[4] = mPool.load(this, R.raw.a03, 1);
        soundID[5] = mPool.load(this, R.raw.a04, 1);

        final Intent intent = new Intent(MainActivity.this, SecretActivity.class);//写一个跳转语句

        Bt_box[0].setOnClickListener(new View.OnClickListener() {//按钮1的监听，下方按钮同理
            @Override
            public void onClick(View v) {
                mPool.play(soundID[0], 1, 1, 0, 0, 1);//播放音频数组的第一个声音，下方同理
                Secret[p] = 1;//在装密码的数组的p位置装入1，下方同理
                p++;//将p移向下一个位置
                if (p == 10) {//如果p=10，将密码数组归零，将p归零，因为密码只有10位
                    for (int i = 0; i < 10; i++) {
                        Secret[i] = 0;
                    }
                    p = 0;
                }
            }
        });
        Bt_box[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPool.play(soundID[1], 1, 1, 0, 0, 1);
                Secret[p] = 2;
                p++;
                if (p == 10) {
                    for (int i = 0; i < 10; i++) {
                        Secret[i] = 0;
                    }
                    p = 0;
                }
            }
        });
        Bt_box[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPool.play(soundID[2], 1, 1, 0, 0, 1);
                Secret[p] = 3;
                if (checkBox(Secret, Secret_r)) {//这条语句有所不同，因为密码的最后一位数字是3，所以只在最后一位进行判断
                    for (int i = 0; i < 10; i++) {//如果密码正确，将密码数组归零
                        Secret[i] = 0;
                    }
                    p = 0;
                    startActivity(intent);       //并启动上面的跳转语句，从MainActivity跳转到SecretActivity
                    mSun.start();

                }
                p++;
                if (p == 10) {
                    for (int i = 0; i < 10; i++) {
                        Secret[i] = 0;
                    }
                    p = 0;
                }
            }
        });
        Bt_box[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPool.play(soundID[3], 1, 1, 0, 0, 1);
                Secret[p] = 4;
                p++;
                if (p == 10) {
                    for (int i = 0; i < 10; i++) {
                        Secret[i] = 0;
                    }
                    p = 0;
                }
            }
        });
        Bt_box[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPool.play(soundID[4], 1, 1, 0, 0, 1);
                Secret[p] = 5;
                p++;
                if (p == 10) {
                    for (int i = 0; i < 10; i++) {
                        Secret[i] = 0;
                    }
                    p = 0;
                }
            }
        });
        Bt_box[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPool.play(soundID[5], 1, 1, 0, 0, 1);
                Secret[p] = 6;
                p++;
                if (p == 10) {
                    for (int i = 0; i < 10; i++) {
                        Secret[i] = 0;
                    }
                    p = 0;
                }

            }
        });
        Bt_box[6].setOnClickListener(new View.OnClickListener() {//顶部返回按钮监听
            @Override
            public void onClick(View v) {
                mSun.release();
                finish();//结束当前Activity
            }
        });
        Bt_box[7].setOnClickListener(new View.OnClickListener() {//顶部更多按钮监听
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10; i++) {
                    Secret[i] = 0;
                }
                p = 0;
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);//设置一个跳转
                startActivity(intent);//启动跳转
            }
        });
        Bt_box[8].setOnLongClickListener(new View.OnLongClickListener() {//隐藏按钮监听
            @Override
            public boolean onLongClick(View v) {
                for (int i = 0; i < 10; i++) {
                    Secret[i] = 0;
                }//密码归零
                p = 0;
                mSun.stop();
                try {
                    mSun.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(MainActivity.this, "2356665633", Toast.LENGTH_SHORT).show();//弹出密码信息
                return true;
            }
        });
    }
}
