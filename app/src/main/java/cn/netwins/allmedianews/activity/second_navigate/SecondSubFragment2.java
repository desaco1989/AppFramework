package cn.netwins.allmedianews.activity.second_navigate;

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
import cn.netwins.allmedianews.thread_pool.ThreadPoolManager2;

/**
 * Created by desaco on 2018/4/18.
 * RxJava事件驱动代替接口回调
 * https://blog.csdn.net/ShareUs/article/details/52434527
 */
public class SecondSubFragment2 extends BaseFragment implements View.OnClickListener, ICallback {


    private TextView callbackTv;

    public static SecondSubFragment2 newInstance(String s) {
        SecondSubFragment2 listFragment = new SecondSubFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("videoType", s);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.common_fragment, null);
        poolManager2 = ThreadPoolManager2.getThreadPoolInstance();

        initView(v);
        initData();
        return v;

    }

    private void initView(View v) {
        TextView showTv = (TextView) v.findViewById(R.id.show_text_tv);
//
        Bundle bundle =this.getArguments();//得到从Activity传来的数据
        String mess = null;
        if(bundle!=null){
            mess = bundle.getString("videoType");
        }
        showTv.setText(mess);

        callbackTv = (TextView) v.findViewById(R.id.callback_data_tv);

        Button subThreadBt = (Button) v.findViewById(R.id.sub_thread_bt);
        subThreadBt.setVisibility(View.VISIBLE);
        subThreadBt.setOnClickListener(this);
    }

    private ThreadPoolManager2 poolManager2;

    private void initData() {

        poolManager2.setInterfaceObject(new ICallback() {
            @Override
            public void sendMsg(String type, Object msg) {
                if (msg != null) {
                    callbackTv.setText("sub2222,,," + msg);
                    Log.e("desaco", "SecondSubFragment2222,  msg=" + msg);
                }
            }
        });
    }

    @Override
    public void sendMsg(String type, Object msg) {
        if (msg != null) {
            callbackTv.setText("sub2222,,," + msg);
            Log.e("desaco", "SecondSubFragment2222,  msg=" + msg);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sub_thread_bt:
                poolManager2.executeRunnableCallback(run2);
                break;
            default:
                break;

        }
    }

    private Runnable run2 = new Runnable() {
        @Override
        public void run() {
            String a = "sub 2,";
            String b = "sub 222222222";
            String c = a + b;
        }
    };

}
