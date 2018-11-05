package cn.netwins.allmedianews.second_navigate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.netwins.allmedianews.R;
import cn.netwins.allmedianews.base.BaseFragment;
import cn.netwins.allmedianews.thread_pool.ICallback;
import cn.netwins.allmedianews.thread_pool.ThreadPoolManager;
import cn.netwins.allmedianews.thread_pool.ThreadPoolManager2;

/**
 * Created by desaco on 2018/4/18.
 * RxJava事件驱动代替接口回调
 * https://blog.csdn.net/ShareUs/article/details/52434527
 */
public class SecondSubFragment1 extends BaseFragment implements View.OnClickListener {


    private TextView callbackTv;

    public static SecondSubFragment1 newInstance(String s) {
        SecondSubFragment1 listFragment = new SecondSubFragment1();
        Bundle bundle = new Bundle();
        bundle.putString("videoType", s);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.common_fragment, null);

        initView(v);
        initData();
        return v;

    }

    private void initView(View v) {
        TextView showTv = (TextView) v.findViewById(R.id.show_text_tv);
        showTv.setText("SecondSubFragment1111,,,,,,,,,tab2");
//        Log.e("desaco", "SecondSubFragment1 onCreateView()");
        callbackTv = (TextView) v.findViewById(R.id.callback_data_tv);
        Button subThreadBt = (Button) v.findViewById(R.id.sub_thread_bt);
        subThreadBt.setVisibility(View.VISIBLE);
        subThreadBt.setOnClickListener(this);
    }

    private ThreadPoolManager2 poolManager2;

    private void initData() {
        poolManager2 = ThreadPoolManager2.getThreadPoolInstance();
        poolManager2.setInterfaceObject(new ICallback() {
            @Override
            public void sendMsg(String type, Object msg) {
                if (msg != null) {
                    callbackTv.setText("sub1111,,,"+ msg);
                    Log.e("desaco", "SecondSubFragment1111,  msg=" + msg);
                }
            }
        });
    }

//    @Override
//    public void sendMsg(String type, Object msg) {
//        if (msg != null) {
//            callbackTv.setText("sub1111,,," + msg);
//            Log.e("desaco", "SecondSubFragment1111,  msg=" + msg);
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sub_thread_bt:
                poolManager2.executeRunnableCallback(run1);
                break;
            default:
                break;

        }
    }

    private Runnable run1 = new Runnable() {
        @Override
        public void run() {
            String a = "sub 1,";
            String b = "sub 11111111";
            String c = a + b;
//            Log.e("desaco", "sub1,c=" + c);
        }
    };

}
