package wincity.litao.com.base;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.BuildConfig;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;

import wincity.litao.com.App;
import wincity.litao.com.R;
import wincity.litao.com.util.LogUtil;
import wincity.litao.com.util.ToastUtil;

//import com.squareup.leakcanary.RefWatcher;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 * Android development framework
 */
public abstract class BaseActivity extends RxAppCompatActivity {


    protected String TAG;

    private SparseArray<View> mViews = new SparseArray<>();
    /**
     * @return root 设置root视图ID =xml layout id
     */
//    public abstract int getRootLayoutId();

    /**
     * @param id
     * @param <T>
     * @return 通过id，获取到控件对象
     */
    public <T extends View> T bind(int id) {
        return (T) bindView(id);
    }

    /**
     * @param id
     * @param <T>
     * @return 绑定视图对象并返回
     */
    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) this.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedInstanceState.putParcelable("android:support:fragments", null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(getResources().getColor(R.color.them_status));
        }
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setTheme(R.style.pageTheme);
        TAG = getClass().getSimpleName();
        LogUtil.i(TAG,"onCreate");


        setTopBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG,"onStart");
        registerEventBus();

    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG,"onStop");
        unregisterEventBus();
    }
    protected void registerEventBus(){

    }
    protected void unregisterEventBus(){

    }

    @Override
    protected void onPause() {
//        clearReferences();
        super.onPause();
        LogUtil.i(TAG,"onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(TAG,"onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG,"onResume");
        App.getInstance().setCurrentActivity(this);
    }


    @Override
    protected void onDestroy() {
        LogUtil.i(TAG,"onDestroy");
        clearReferences();
        super.onDestroy();
        closeDialog();
    }

    private void clearReferences(){
        Activity currActivity = App.getInstance().getCurrentActivity();
        if (this.equals(currActivity))
            App.getInstance().setCurrentActivity(null);
    }

    /**
     * 显示一个提示信息
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtil.showToast(this, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个提示信息
     *
     * @param strId 显示信息在XML中ID
     */
    public void showToast(int strId) {
        ToastUtil.showToast(this, strId, Toast.LENGTH_SHORT);
    }


    /**
     * 启动Activity 不带参数
     *
     * @param className
     */
    public void lunchActivity(Class<?> className) {
        Intent intent=new Intent(this, className);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
//        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    /**
     * 启动Activity 带参数
     * @param className
     */
    public void lunchActivity(Class<?> className, Bundle bundle) {
        Intent intent = new Intent(this, className);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
//        overridePendingTransition( R.anim.left_in,R.anim.left_out);
        finish();
    }

    /**
     * 启动一个Activity 并等待返回
     *
     * @param className
     * @param requestCode
     * @param bundle
     */
    protected void lunchActivityForResult(Class<?> className, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, className);
        if (null != bundle) {
            intent.putExtras(bundle);
//            overridePendingTransition(bundle.getInt("animIn", R.anim.left_in), bundle.getInt("animOut", R.anim.left_out));
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 设置状态栏
     */
    protected void setTopBar() {
    }

    public void goneView(@Nullable View v) {
        v.setVisibility(View.GONE);
    }
    public void visibleView(@Nullable View v) {
        v.setVisibility(View.VISIBLE);
    }
    public void invisibleView(@Nullable View v) {
        v.setVisibility(View.INVISIBLE);
    }

    public static Fragment getFragmentByTag(BaseActivity activity, Bundle bundle , Class<?> className){
        BaseFragment mFragment;
        FragmentManager fm =activity.getSupportFragmentManager();
        mFragment = (BaseFragment) fm.findFragmentByTag(className.getName());
        if (mFragment == null) {
            mFragment = (BaseFragment) Fragment.instantiate(activity, className.getName(), bundle);

        }
        return mFragment;
    }


    /**
     * 修复输入法引起的内存泄漏
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String [] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0;i < arr.length;i ++) {
            String param = arr[i];
            try{
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
//                        if (QLog.isColorLevel()) {
//                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
//                        }
                        break;
                    }
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
    private ProgressDialog mProgressDialog;
    public  void showDialog(String title,boolean isCancel){
        closeDialog();
        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setCancelable(isCancel);
        mProgressDialog.setMessage(title);

        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        mProgressDialog.show();

    }
    public  void showDialog(String title){
        showDialog(title, false);
    }
    public void closeDialog(){

        if(mProgressDialog!=null&&mProgressDialog.isShowing()){

            mProgressDialog.dismiss();

            mProgressDialog=null;

        }
    }

    public void replaceFg(@NonNull Fragment fragment, @IdRes int container, @NonNull String tag){
        getSupportFragmentManager().beginTransaction()
               // .setCustomAnimations(R.anim.left_in, R.anim.left_out,R.anim.left_in, R.anim.left_out)
                .replace(container, fragment,tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();
    }
}