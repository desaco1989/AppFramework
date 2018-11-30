package cn.netwins.allmedianews.interface_transfer_value;

/**
 * Created by desaco on 2018/11/30.
 * 接口传值
 */

public class ObjB implements IPresenter{
    public static void main(String[] args) {
        new ObjA(new ObjB());
       //
    }
    @Override
    public void codeMsg(String str) {
        System.out.println(str);
    }
}
