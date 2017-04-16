package wincity.litao.com.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import wincity.litao.com.R;
import wincity.litao.com.base.BaseActivity;
import wincity.litao.com.bus.BaseEvent;
import wincity.litao.com.bus.BusUtil;
import wincity.litao.com.bus.Xevent;
import wincity.litao.com.http.ApiManager;
import wincity.litao.com.util.LogUtil;
import wincity.litao.com.util.ToastUtil;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    ApiManager.getInstance().getOtp()
                            .throttleFirst(1, TimeUnit.SECONDS)
                            .take(10)
                            .onBackpressureDrop()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ResponseBody>() {
                                @Override
                                public void accept(ResponseBody responseBody) throws Exception {
                                    String string = responseBody.string();
                                    BaseEvent.RequestOtpEvent success = BaseEvent.RequestOtpEvent.SUCCESS;
                                    //                                    BusUtil.getEventBust().post(success);
                                    BusUtil.getEventBust().post(new Xevent());
                                    LogUtil.i(TAG, string);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    LogUtil.i(TAG, throwable.toString());
                                    BaseEvent.RequestOtpEvent unsuccess = BaseEvent.RequestOtpEvent.UNSUCCESS;
                                    unsuccess.setMessage(throwable);
                                    BusUtil.getEventBust().post(BaseEvent.RequestOtpEvent.UNSUCCESS);
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    LogUtil.i(TAG, "completed");
                                }
                            });
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //    No subscribers registered for event class wincity.litao.com.bus.BaseEvent$RequestOtpEvent
    @Subscribe
    public void registerResult(Xevent x) {
        /*LogUtil.i(TAG,"registerResult");
        if (commonEvnet == BaseEvent.RequestOtpEvent.SUCCESS){
            ToastUtil.showToast(this,"请求otp成功", Toast.LENGTH_SHORT);
        }else {
            ToastUtil.showToast(this,"请求otp失败", Toast.LENGTH_SHORT);
        }*/
        ToastUtil.showToast(this, "请求otp成功", Toast.LENGTH_SHORT);
    }

    @Override
    protected void registerEventBus() {
        super.registerEventBus();
        //        BusUtil.register(this);
        if (!!EventBus.getDefault().hasSubscriberForEvent(Xevent.class))
            EventBus.getDefault().register(this);
        LogUtil.i(TAG, "registerEventBus");
    }

    @Override
    protected void unregisterEventBus() {
        super.unregisterEventBus();
        //        BusUtil.unregister(this);
    }
}
