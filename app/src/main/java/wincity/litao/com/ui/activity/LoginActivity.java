package wincity.litao.com.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import wincity.litao.com.R;
import wincity.litao.com.base.BaseActivity;
import wincity.litao.com.base.mvp.MvpPresenter;
import wincity.litao.com.ui.view.LoginView;

/**
 * Created by admin on 2017/4/22.
 */

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @NonNull
    @Override
    public MvpPresenter<LoginView> createPresenter() {
        return null;
    }
}
