package wincity.litao.com.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wincity.litao.com.R;
import wincity.litao.com.base.BaseActivity;
import wincity.litao.com.login.presenter.impl.LoginPresenterImpl;
import wincity.litao.com.login.view.LoginView;
import wincity.litao.com.ui.activity.MainActivity;
import wincity.litao.com.util.LogUtil;
import wincity.litao.com.util.ToastUtil;

/**
 * Created by admin on 2017/4/22.
 */

public class LoginActivity extends BaseActivity<LoginView, LoginPresenterImpl> implements LoginView {
    @BindView(R.id.et_account)
    TextInputEditText etAccount;
    @BindView(R.id.til_account)
    TextInputLayout tilAccount;
    @BindView(R.id.iv_del_account)
    ImageView ivDelAccount;
    @BindView(R.id.et_pwd)
    TextInputEditText etPwd;
    @BindView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @BindView(R.id.iv_del_pwd)
    ImageView ivDelPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    public void showDialog() {
        showDialog("test_title");
    }

    @Override
    public void dissMissDialog() {
        closeDialog();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void success() {
        ToastUtil.showToast(this, "sss", Toast.LENGTH_SHORT);
    }

    @OnClick(R.id.btn_login)
    public void login(View v){
        LogUtil.i(TAG,"login");
        presenter.requestLogin("ss","ss");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.lauch(LoginActivity.this,new Intent());
                finish();
            }
        },2000);
    }
}
