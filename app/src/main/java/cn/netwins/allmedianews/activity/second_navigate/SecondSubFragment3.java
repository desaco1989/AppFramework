package cn.netwins.allmedianews.activity.second_navigate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Callable;

import cn.netwins.allmedianews.R;
import cn.netwins.allmedianews.base.BaseFragment;
import cn.netwins.allmedianews.thread_pool.ICallback;
import cn.netwins.allmedianews.thread_pool.ThreadPoolManager2;

/**
 * Created by desaco on 2018/4/18.
 * RxJava事件驱动代替接口回调
 * https://blog.csdn.net/ShareUs/article/details/52434527
 */
public class SecondSubFragment3 extends BaseFragment implements View.OnClickListener {

    private TextView callbackTv;

    public static SecondSubFragment3 newInstance(String s) {
        SecondSubFragment3 listFragment = new SecondSubFragment3();
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
        showTv.setText("SecondSubFragment3333,,,,,,,,,tab2");
//        Log.e("desaco", "SecondSubFragment3 onCreateView()");
        callbackTv = (TextView) v.findViewById(R.id.callback_data_tv);
        Button subThreadBt = (Button) v.findViewById(R.id.sub_thread_bt);
        subThreadBt.setVisibility(View.VISIBLE);
        subThreadBt.setOnClickListener(this);
        //
        Button subThreadStopBt = (Button) v.findViewById(R.id.sub_thread_stop_bt);
        subThreadStopBt.setVisibility(View.VISIBLE);
        subThreadStopBt.setOnClickListener(this);
    }

    private ThreadPoolManager2 poolManager2;

    private void initData() {
        poolManager2 = ThreadPoolManager2.getThreadPoolInstance();
        poolManager2.setInterfaceObject(new ICallback() {
            @Override
            public void sendMsg(String type, Object msg) {
                if (msg != null) {
                    callbackTv.setText("sub3333,,," + msg);
                    Log.e("desaco", "SecondSubFragment3333,  msg=" + msg);
                }
            }
        });
    }

//    @Override
//    public void sendMsg(String type, Object msg) {
//        if (msg != null) {
//            callbackTv.setText("sub3333,,," + msg);
//            Log.e("desaco", "SecondSubFragment3333,  msg=" + msg);
//        }
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sub_thread_bt:
//                poolManager2.executeRunnableCallback(run3);
//                for(int i=0;i<10;i++){
//                    poolManager2.submitCallback(new CallableDemo());
//                }
                poolManager2.submitCallback(new CallableDemo());
                break;
            case R.id.sub_thread_stop_bt:
                poolManager2.cancel();
                break;
            default:
                break;
        }
    }

//    private Runnable run3 = new Runnable() {
//        @Override
//        public void run() {
//            String a = "sub 3,";
//            String b = "sub 33333333";
//            String c = a + b;
//        }
//    };

    class CallableDemo implements Callable<Object> {

        private int sum;
        @Override
        public Object call() throws Exception {
//            System.out.println("Callable子线程开始计算啦！");
//            Thread.sleep(2000);
//
//            for(int i=0 ;i<5000;i++){
//                sum=sum+i;
//            }
//            System.out.println("Callable子线程计算结束！");
//            return sum;
            String a = "sub 3,";
            String b = "Object 33333333 Object";
            Object o = a + b;
            return o;
        }
    }

}
