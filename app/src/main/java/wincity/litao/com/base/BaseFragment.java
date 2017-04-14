package wincity.litao.com.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxFragment;

import wincity.litao.com.util.ToastUtil;

/**
 * Created by shang guangneng on 2016/6/8 0008.
 */
public abstract class BaseFragment extends RxFragment implements IDelegate{

    protected String TAG;
    protected SparseArray<View> mViews = new SparseArray<>();
    protected View rootView = null;
    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        create(inflater,container,savedInstanceState);
        initView(savedInstanceState);
        Log.i(TAG,"onCreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG,"onViewCreated");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG=this.getClass().getSimpleName();
        mContext = (Activity) context;
        Log.i(TAG,"onAttach");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext = null;
        Log.i(TAG,"onDestroy");

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

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == rootView) {
            rootView=inflater.inflate(getRootLayoutId(), container, false);
            Log.i(TAG, " create fragment rootView SUCCESS");
        }
    }

    @Override
    public void initListener(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            bind(id).setOnClickListener(listener);
        }
    }
    @Override
    public void initListener(View.OnClickListener listener,View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view!=null)view.setOnClickListener(listener);
        }
    }
    @Override
    public void initOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener,CompoundButton... compoundButtons) {
        if (compoundButtons == null) {
            return;
        }
        for (CompoundButton btn : compoundButtons) {
            btn.setOnCheckedChangeListener(listener);
        }

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