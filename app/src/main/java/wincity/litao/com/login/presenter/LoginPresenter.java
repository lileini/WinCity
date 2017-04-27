package wincity.litao.com.login.presenter;

import android.support.annotation.NonNull;

import wincity.litao.com.base.mvp.MvpPresenter;
import wincity.litao.com.login.view.LoginView;

/**
 * created by litao
 **/
public interface LoginPresenter extends MvpPresenter<LoginView>  {
    void requestLogin(String phone,String pwd);

    void forgetPWD();

    void checkPhoneNumber(@NonNull String number);

    void register();

}
