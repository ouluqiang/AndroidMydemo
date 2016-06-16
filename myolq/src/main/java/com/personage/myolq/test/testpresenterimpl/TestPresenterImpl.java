package com.personage.myolq.test.testpresenterimpl;

import android.content.Context;
import android.widget.Toast;

import com.example.javabean.entity.BaseEntity;
import com.example.javabean.entity.rongyun.User;
import com.example.my.http.backcall.RBackCall;
import com.example.utils.L;
import com.personage.myolq.R;
import com.personage.myolq.api.OnResponse;
import com.personage.myolq.mvp.iuiview.IUiLoginView;
import com.personage.myolq.mvp.iuiview.IUiRegisterView;
import com.personage.myolq.test.iuiview.IUiTest2View;
import com.personage.myolq.test.iuiview.IUiTestView;
import com.personage.myolq.test.testmodel.TestModel;
import com.personage.myolq.test.testmodelimpl.TestModelImpl;
import com.personage.myolq.test.testpresenter.TestPresenter;

import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class TestPresenterImpl implements TestPresenter {

    private TestModel mloginModel;
    private IUiTestView mIUiLoginView;
    private IUiTest2View mIUiTest2View;

    public TestPresenterImpl(IUiTestView iUiTestView) {
        if(mloginModel==null){
            mloginModel=new TestModelImpl();
        }
        mIUiLoginView=iUiTestView;
    }

    public TestPresenterImpl(IUiTest2View iUiTest2View) {
        if(mloginModel==null){
            mloginModel=new TestModelImpl();
        }
        mIUiTest2View=iUiTest2View;
    }

    @Override
    public void getEmailLoginToken2(final Context context, String email, String password) {
        mloginModel.getEmailLoginToken1(email, password, new Subscriber<BaseEntity<User>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(context,
                        "Completed",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context,
                        e.getMessage(),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNext(BaseEntity<User> userBaseEntity) {
                L.log("数据："+userBaseEntity.getCode());
                BaseEntity<User> baseEntity=userBaseEntity;
                if(OnResponse.is_OK(baseEntity.getCode())){
                    mIUiLoginView.onToast(baseEntity.getCode()+baseEntity.getResult());
                }else{
                    mIUiLoginView.onToast(baseEntity.getMessage());
                }
            }
        });
    }

    @Override
    public void getEmailLoginToken1(final Context context, String email, String password) {
        mloginModel.getEmailLoginToken1(email, password, new Subscriber<BaseEntity<User>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(context,
                        "Completed",
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(context,
                        e.getMessage(),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNext(BaseEntity<User> userBaseEntity) {
                L.log("数据："+userBaseEntity.getCode());
                BaseEntity<User> baseEntity=userBaseEntity;
                if(OnResponse.is_OK(baseEntity.getCode())){
                    mIUiLoginView.onToast(baseEntity.getCode());
                }else{
                    mIUiLoginView.onToast(baseEntity.getMessage());
                }
            }
        });
    }

    @Override
    public void getEmailLoginToken(final Context context, String email, String password) {
        mloginModel.getEmailLoginToken(email, password, new RBackCall<BaseEntity<User>>() {
            @Override
            public void onResponse(Call call, Response response) {
                L.log("数据："+response.body().toString());
                BaseEntity<User> baseEntity= (BaseEntity) response.body();
                if(OnResponse.is_OK(baseEntity.getCode())){
                    mIUiLoginView.onSucceed(baseEntity.getResult());
                }else{
                    mIUiLoginView.onToast(baseEntity.getMessage());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    @Override
    public void getReg(final Context context, String email, String mobile, String username, String password) {
        mloginModel.getReg(email, mobile, username, password, new RBackCall() {
            @Override
            public void onResponse(Call call, Response response) {
                L.log("数据："+response.body().toString());
                BaseEntity baseEntity= (BaseEntity) response.body();
                if(OnResponse.is_OK(baseEntity.getCode())){
                    mIUiTest2View.onSucceed(baseEntity.getResult());
                    mIUiTest2View.onToast(context.getString(R.string.register_succeed));
                }else{
                    mIUiTest2View.onToast(baseEntity.getCode());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
