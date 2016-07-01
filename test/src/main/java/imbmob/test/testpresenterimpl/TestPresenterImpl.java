package imbmob.test.testpresenterimpl;

import android.content.Context;
import android.widget.Toast;


import imbmob.test.api.OnResponse;
import imbmob.test.http.backcall.RBackCall;
import imbmob.test.iuiview.IUiTest2View;
import imbmob.test.iuiview.IUiTestView;
import imbmob.test.rongyun.BaseEntity;
import imbmob.test.rongyun.UserRong;
import imbmob.test.testmodel.TestModel;
import imbmob.test.testmodelimpl.TestModelImpl;
import imbmob.test.testpresenter.TestPresenter;
import retrofit2.Call;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class TestPresenterImpl
        implements TestPresenter {


    private TestModel mloginModel;
    private IUiTestView mIUiLoginView;
    private IUiTest2View mIUiTest2View;

    public TestPresenterImpl(IUiTestView iUiTestView) {
        if (mloginModel == null) {
            mloginModel = new TestModelImpl();
        }
        mIUiLoginView = iUiTestView;
    }

    public TestPresenterImpl(IUiTest2View iUiTest2View) {
        if (mloginModel == null) {
            mloginModel = new TestModelImpl();
        }
        mIUiTest2View = iUiTest2View;
    }

    @Override
    public void getEmailLoginToken2(final Context context, String email, String password) {
        mloginModel.getEmailLoginToken1(email, password, new Subscriber<BaseEntity<UserRong>>() {
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
            public void onNext(BaseEntity<UserRong> userBaseEntity) {
//                L.log("数据："+userBaseEntity.getCode());
                BaseEntity<UserRong> baseEntity = userBaseEntity;
                if (OnResponse.is_OK(baseEntity.getCode())) {
                    mIUiLoginView.onToast(baseEntity.getCode() + baseEntity.getResult());
                } else {
                    mIUiLoginView.onToast(baseEntity.getMessage());
                }
            }
        });
    }

    @Override
    public void getEmailLoginToken1(final Context context, String email, String password) {
        mloginModel.getEmailLoginToken1(email, password, new Subscriber<BaseEntity<UserRong>>() {
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
            public void onNext(BaseEntity<UserRong> userBaseEntity) {
//                L.log("数据："+userBaseEntity.getCode());
                BaseEntity<UserRong> baseEntity = userBaseEntity;
                if (OnResponse.is_OK(baseEntity.getCode())) {
                    mIUiLoginView.onToast(baseEntity.getCode());
                } else {
                    mIUiLoginView.onToast(baseEntity.getMessage());
                }
            }
        });
    }

    @Override
    public void getEmailLoginToken(final Context context, String email, String password) {
        mloginModel.getEmailLoginToken(email, password, new RBackCall<BaseEntity<UserRong>>() {
            @Override
            public void onResponse(Call call, Response response) {
//                L.log("数据："+response.body().toString());
                BaseEntity<UserRong> baseEntity = (BaseEntity) response.body();
                if (OnResponse.is_OK(baseEntity.getCode())) {
                    mIUiLoginView.onSucceed(baseEntity.getResult());
                } else {
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
//                L.log("数据："+response.body().toString());
                BaseEntity baseEntity = (BaseEntity) response.body();
                if (OnResponse.is_OK(baseEntity.getCode())) {
                    mIUiTest2View.onSucceed(baseEntity.getResult());
                    mIUiTest2View.onToast("成功");
                } else {
                    mIUiTest2View.onToast(baseEntity.getCode());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}