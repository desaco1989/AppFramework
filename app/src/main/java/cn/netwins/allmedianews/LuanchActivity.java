package cn.netwins.allmedianews;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import cn.netwins.allmedianews.MainActivity;
import cn.netwins.allmedianews.R;

public class LuanchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
//        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
//                 WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_luanch);
        delayJump();
    }


    private void delayJump() {//延时跳转
        new Handler(new Handler.Callback() {
            //处理接收到的消息的方法
            @Override
            public boolean handleMessage(Message arg0) {
                //实现页面跳转
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return false;
            }
        }).sendEmptyMessageDelayed(0, 1000); //表示延时三秒进行任务的执行
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
