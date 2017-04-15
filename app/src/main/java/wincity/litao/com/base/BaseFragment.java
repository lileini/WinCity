package wincity.litao.com.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle2.components.support.RxFragment;

import wincity.litao.com.util.BusUtil;
import wincity.litao.com.util.LogUtil;
import wincity.litao.com.util.ToastUtil;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 */
public abstract class BaseFragment extends RxFragment {

    protected String TAG;
    protected SparseArray<View> mViews = new SparseArray<>();
    protected View rootView = null;
    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG,"onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG,"onStart");
        BusUtil.register(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG,"onCreateView");
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i(TAG,"onViewCreated");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG=this.getClass().getSimpleName();
        mContext = (Activity) context;
        LogUtil.i(TAG,"onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG,"onPause");

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG,"onResume");

    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG,"onStop");
        BusUtil.unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
        LogUtil.i(TAG,"onDestroy");

    }


    /**
     * 启动Activity 不带参数
     *
     * @param className
     */
    protected void lunchActivity(Class<?> className) {
        startActivity(new Intent(getActivity(), className));
    }

    /**
     * 启动Activity 带参数
     *
     * @param className
     */
    protected void lunchActivity(Class<?> className, Bundle bundle) {
        Intent intent = new Intent(getActivity(), className);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 显示一个提示信息
     *
     * @param msg
     */
    protected void showToast(String msg) {
        ToastUtil.showToast(getActivity(), msg, Toast.LENGTH_SHORT);
    }

    /**
     * 显示一个提示信息
     *
     * @param strId 显示信息在XML中ID
     */
    protected void showToast(int strId) {
        if(getActivity()!=null)
         ToastUtil.showToast(getActivity(), strId, Toast.LENGTH_SHORT);
    }

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
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }



    /**
     * @return root 设置root视图ID =xml layout id
     */
    public abstract int getRootLayoutId();

    public void goneView(@Nullable View v) {
      v.setVisibility(View.GONE);
    }
    public void visibleView(@Nullable View v) {
        v.setVisibility(View.VISIBLE);
    }
    public void invisibleView(@Nullable View v) {
        v.setVisibility(View.INVISIBLE);
    }





}