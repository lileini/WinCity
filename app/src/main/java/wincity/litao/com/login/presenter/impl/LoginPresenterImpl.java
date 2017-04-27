package wincity.litao.com.login.presenter.impl;


import android.support.annotation.NonNull;

import wincity.litao.com.base.BaseActivity;
import wincity.litao.com.base.mvp.MvpBasePresenter;
import wincity.litao.com.login.presenter.LoginPresenter;
import wincity.litao.com.login.view.LoginView;

public class LoginPresenterImpl extends MvpBasePresenter<LoginView> implements LoginPresenter {


    @Override
    public void requestLogin(String phone,String pwd) {
        getView().showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    if (!isViewAttached()){
                        return;
                    }
                    ((BaseActivity)getView()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getView().success();
                            getView().dissmissLoading();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void forgetPWD() {

    }

    @Override
    public void checkPhoneNumber(@NonNull String number) {

    }

    @Override
    public void register() {

    }
}
