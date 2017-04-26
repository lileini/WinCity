package wincity.litao.com.login.view;

import wincity.litao.com.base.mvp.MvpView;

/**
 * created by litao
 **/
public interface LoginView extends MvpView{
    void showDialog();
    void dissMissDialog();
    void showError(String error);
    void success();

}
