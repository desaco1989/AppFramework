package cn.netwins.allmedianews.common;

/**
 * Created by desaco on 2018/11/11.
 */

public class CommonReponse<T> {
    private T data;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
