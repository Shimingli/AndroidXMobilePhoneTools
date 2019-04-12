package com.lsm.mobilephonetools;

import android.content.Context;
import android.util.Log;

import com.lsm.base.BaseApplication;
import com.lsm.base.ui.QMUISwipeBackActivityManager;
import com.lsm.ironmanlog.FakeCrashLibrary;
import com.lsm.ironmanlog.IronManLog;
import com.lsm.ironmanlog.IronManLogConfig;
import com.miui.zeus.mimo.sdk.MimoSdk;
import com.miui.zeus.mimo.sdk.api.IMimoSdkListener;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import timber.log.Timber;


/**
 * <p>
 *
 * </p>
 *
 * @author shiming
 * @version v1.0
 * @since 2018/11/28 11:31
 */

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                @Override
                protected void log(int priority, String tag, @NotNull String message, Throwable t) {
                    super.log(priority, tag, message, t);
                }
            });
        } else {
            //上线的话  就关闭有些不必要的日记输出
            Timber.plant(new CrashReportingTree());
        }
        Timber.v("---MyApplication  start ---");
        Timber.d("Timber MyApplication start ");
        QMUISwipeBackActivityManager.init(this);
        //记得给与存储的权限
        IronManLogConfig config = new IronManLogConfig.Builder()
                .setContext(getApplicationContext())
                .setEncryptKey16("shiminglog123456".getBytes()) //128位ase加密Key
                .setEncryptIV16("shiminglog123456".getBytes()) //128位aes加密IV
                .setCacheFolder("alllib_log")//这是Logan正在缓存在本地文件夹的名称
                .build();
        //由于文件是加密的，如果需要自己解密然后上传到后台的话，那么这里就需要一个文件夹的名称
        IronManLogConfig.mUploadFolderName ="led";
        //由于文件是加密的，如果需要自己解密然后上传到后台的话，那么这里就需要一个文件的名称
        IronManLogConfig.mUploadFileName ="led.log";
        IronManLog.init(config,BuildConfig.DEBUG,true);


        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "5cb0397e61f564b7f400022b", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "5cb0397e61f564b7f400022b");
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        /* Bugly SDK初始化
         * 参数1：上下文对象
         * 参数2：APPID，平台注册时得到,注意替换成你的appId
         * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
         */
        CrashReport.initCrashReport(getApplicationContext(), "0312c64750", false);



//        // 但是这也意味着您必须得重新发版才能使用最新版本的sdk, 建议开启自升级
//        MimoSdk.setEnableUpdate(false);
////        // 正式上线时候务必关闭stage和debug
////        MimoSdk.setDebugOn();
////        MimoSdk.setStageOn();
//
//
//        // 如需要在本地预置插件,请在assets目录下添加mimo_asset.apk;
//        MimoSdk.init(this,Constant.APP_ID, APP_KEY, APP_TOKEN, new IMimoSdkListener() {
//            @Override
//            public void onSdkInitSuccess() {
//                Log.d("MyApplication", "onSdkInitSuccess");
//                // Toast.makeText(MyApplication.this, R.string.sdk_is_ready, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onSdkInitFailed() {
//                Log.d("MyApplication", "onSdkInitFailed");
//                // Toast.makeText(MyApplication.this, R.string.sdk_is_failed, Toast.LENGTH_LONG).show();
//            }
//        });

    }





    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    /**
     * 记录重要信息以进行故障报告的树。
     */
    private static final class CrashReportingTree extends Timber.Tree {

        @Override
        protected boolean isLoggable(@Nullable String tag, int priority) {
            return priority >=Log.INFO;
    }

        @Override
        protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            FakeCrashLibrary.log(priority, tag, message);
            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
