package cn.netwins.allmedianews.interface_transfer_value;

/**
 * Created by desaco on 2018/11/30.
 * 接口传值
 */

public class ObjA {
    private IPresenter ip;
    public ObjA(IPresenter ip) {
        this.ip = ip;
        set();
    }
    public void set() {
        ip.codeMsg("A class");
    }
}
