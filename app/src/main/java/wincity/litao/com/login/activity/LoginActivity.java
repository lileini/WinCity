package wincity.litao.com.login.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wincity.litao.com.R;
import wincity.litao.com.base.BaseActivity;
import wincity.litao.com.login.presenter.impl.LoginPresenterImpl;
import wincity.litao.com.login.view.LoginView;
import wincity.litao.com.util.ToastUtil;

/**
 * Created by admin on 2017/4/22.
 */

public class LoginActivity extends BaseActivity<LoginView, LoginPresenterImpl> implements LoginView {


    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_del_pwd)
    Button btnDelPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.btn_del_account)
    Button btnDelAccount;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_show_pwd)
    Button btnShowPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvForgetPwd.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvRegister.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnDelAccount.setVisibility(count != 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                LogUtil.i(TAG,"count: "+count);
                btnDelPwd.setVisibility(count != 0 ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @NonNull
    @Override
    public LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    public void showLoading() {
        showDialog("加载中...");
    }

    @Override
    public void dissmissLoading() {
        closeDialog();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void success() {
        ToastUtil.showToast(this, "sss", Toast.LENGTH_SHORT);
    }

    /*@OnClick(R.id.btn_login)
    public void login(View v) {
        LogUtil.i(TAG, "login");
        presenter.requestLogin("ss", "ss");
    }*/

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_forget_pwd, R.id.btn_del_account,
            R.id.btn_del_pwd, R.id.btn_show_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                presenter.requestLogin("ss", "ss");
                break;
            case R.id.tv_register:
                presenter.register();
                break;
            case R.id.tv_forget_pwd:
                presenter.forgetPWD();
                break;
            case R.id.btn_del_account:
                etAccount.setText("");
                etAccount.requestFocus();
                break;
            case R.id.btn_del_pwd:
                etPwd.setText("");
                etPwd.requestFocus();
                break;
            case R.id.btn_show_pwd:
                btnShowPwd.setSelected(!btnShowPwd.isSelected());
                etPwd.setTransformationMethod(btnShowPwd.isSelected() ? HideReturnsTransformationMethod.getInstance()
                        : PasswordTransformationMethod.getInstance());
                etPwd.setSelection(etPwd.getText().length());
                break;

        }
    }


}
