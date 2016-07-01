package imbmob.test.iuiview;


import imbmob.test.base.IUiBaseView;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface IUiTestView extends IUiBaseView {

    public String getUserName();

    public String getUserPass();

    public void onLogin();

    public void onRegister();


}
