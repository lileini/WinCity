package wincity.litao.com.presenter;

import wincity.litao.com.base.mvp.MvpPresenter;

/**
 * created by litao
 **/
public interface LoginPresenter<LoginView> extends MvpPresenter {
    void requestLogin();
    void forgetPWD();

}
