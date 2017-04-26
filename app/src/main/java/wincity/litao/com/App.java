package wincity.litao.com;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import wincity.litao.com.util.GlideImageLoader;

/**
 * created by litao
 **/
public class App extends Application {
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init();
        BaseBean<XXbean> xXbeanBaseBean = new BaseBean<>();
        new Gson().fromJson("s",BaseBean.class);
    }
    private void init(){
//        BusUtil.register(this);
        Stetho.initializeWithDefaults(this);
        //Bugly init
//        CrashReport.initCrashReport(getApplicationContext(), "900053490"/*App ID*/, false);

        if (BuildConfig.DEBUG){
            LeakCanary.install(this);
        }

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                .build());

        //设置realm配置
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded() //solve exception "io.realm.exceptions.RealmMigrationNeededException: RealmMigration must be provided"
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        byte[] bytes = new byte[64];// 加密 key
        RealmInspectorModulesProvider.builder(this)
                .withFolder(getCacheDir())
                .withEncryptionKey("xxx.realm", bytes)//自己填写加密key
                .withMetaTables()
                .withDescendingOrder()
                .withLimit(1000)
                .databaseNamePattern(Pattern.compile(".+\\.realm"))
                .build();
        initConfigForGallerFinal();
    }

    /**
     * 图片选择器
     */
    private void initConfigForGallerFinal() {
        //配置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.color_ffcb04))
                .setTitleBarTextColor(getResources().getColor(R.color.Color_ffffff))
                .setTitleBarIconColor(getResources().getColor(R.color.Color_ffffff))
                .setFabNornalColor(getResources().getColor(R.color.color_ffcb04))
                .setFabPressedColor(getResources().getColor(R.color.color_f0b012))
                .setCheckNornalColor(Color.WHITE)
                .setCheckSelectedColor(Color.BLACK)
//                .setIconBack(R.mipmap.back)
//                .setIconRotate(R.mipmap.ic_action_repeat)
//                .setIconCrop(R.mipmap.ic_action_crop)
//                .setIconCamera(R.mipmap.ic_action_camera)
                .build();

        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setMutiSelectMaxSize(20)
                //                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        ImageLoader imageloader = new GlideImageLoader();
        //设置核心配置信息
        CoreConfig coreConfig = new CoreConfig.Builder(this, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);

    }
    public static App getInstance(){
        return instance;
    }

    private Activity mCurrentActivity = null;

    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }
    public void setCurrentActivity(Activity mCurrentActivity){
        this.mCurrentActivity = mCurrentActivity;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
//        BusUtil.unregister(this);
    }
}
