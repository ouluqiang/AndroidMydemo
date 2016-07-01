package imbmob.test.testmodelimpl;


import imbmob.test.RxBackCall;
import imbmob.test.api.ServerApi;
import imbmob.test.http.HttpLoader;
import imbmob.test.http.backcall.RBackCall;
import imbmob.test.rongyun.BaseEntity;
import imbmob.test.rongyun.UserRong;
import imbmob.test.testmodel.TestModel;
import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class TestModelImpl implements TestModel {

    @Override
    public void getEmailLoginToken(String email, String password, RBackCall<BaseEntity<UserRong>> backCall) {
        Call<BaseEntity<UserRong>> call= HttpLoader.getInstace().getCreate(ServerApi.class).getEmailLoginToken(email,password);
        call.enqueue(backCall);
    }

    @Override
    public void getEmailLoginToken2(String email, String password, RxBackCall<BaseEntity<UserRong>> backCall) {
        Observable<BaseEntity<UserRong>> observable=HttpLoader.getInstace().getRetrofit1().create(ServerApi.class).getEmailLoginToken1(email,password);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(backCall);

    }

    @Override
    public void getEmailLoginToken1(String email, String password, Subscriber<BaseEntity<UserRong>> backCall) {
        Observable<BaseEntity<UserRong>> observable= HttpLoader.getInstace().getRetrofit1().create(ServerApi.class).getEmailLoginToken1(email,password);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(backCall);
    }

    @Override
    public void getReg(String email, String mobile, String username, String password, RBackCall backCall) {
        Call<BaseEntity> call=HttpLoader.getInstace().getCreate(ServerApi.class).getReg(email,mobile,username,password);
        call.enqueue(backCall);
    }
}
