package imbmob.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import imbmob.test.base.BaseActivity;
import imbmob.test.iuiview.IUiTestView;
import imbmob.test.rongyun.UserRong;
import imbmob.test.testpresenter.TestPresenter;
import imbmob.test.testpresenterimpl.TestPresenterImpl;
import imbmob.test.utils.StringUtils;
import imbmob.test.utils.ToastUtil;

/**
 * A login screen that offers login via email/password.
 */
public class TestActivity extends BaseActivity implements IUiTestView {


    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.til_username)
    TextInputLayout tilUsername;
    @Bind(R.id.et_password)
    EditText etPassword;
    @Bind(R.id.til_password)
    TextInputLayout tilPassword;
    private TestPresenter loginPresenter;

//    @Override
//    protected void setLayout() {
//        setContentView(R.layout.activity_test);
//        setTitle(R.string.title_activity_login);
//
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void getonCreate() {
        loginPresenter = new TestPresenterImpl(this);
//        etUsername.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.length()>0){
//                    tilUsername.setErrorEnabled(false);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }


    @OnClick(R.id.tv_login)
    public void onlogin() {
        if (StringUtils.isNull(getUserName())) {
//            ToastUtil.show(getApplicationContext(), getString(R.string.username_isnull));
//            tilUsername.setError(getString(R.string.username_isnull));
//            return;
        }
        if (StringUtils.isNull(getUserPass())) {
//            ToastUtil.show(getApplicationContext(), getString(R.string.userpass_isnull));
//            tilPassword.setError(getString(R.string.userpass_isnull));
        }else{
            tilUsername.setErrorEnabled(false);
            tilPassword.setErrorEnabled(false);
            loginPresenter.getEmailLoginToken2(this, getUserName(),getUserPass());
        }


    }

//    @OnClick(R.id.tv_register)
//    public void onregister() {
//        Intent intent = new Intent(this, Test2Activity.class);
//        startActivity(intent);
//    }

    @Override
    public void onSucceed(Object object) {
        UserRong user= (UserRong) object;
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onToast(String text) {
        ToastUtil.show(this,text);
    }


    @Override
    public String getUserName() {
        return tilUsername.getEditText().getText().toString().trim();
    }

    @Override
    public String getUserPass() {
        return tilPassword.getEditText().getText().toString().trim();
    }

    @Override
    public void onLogin() {
//        Intent intent = new Intent(this, HomePageActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onRegister() {

    }




}

