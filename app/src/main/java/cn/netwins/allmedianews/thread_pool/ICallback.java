package cn.netwins.allmedianews.thread_pool;

/**
 * Created by desaco on 2018/4/16.
 */

public interface ICallback {//ICallback<T>

//    void onSuccess(Class<T> clazz);//成功回调
//    void onFail(Class<T> clazz);//失败后回调

    void sendMsg(String type,Object msg);

}
