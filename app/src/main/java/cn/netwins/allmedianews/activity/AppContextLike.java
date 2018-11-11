//package cn.netwins.news.allnews;
//
//import android.annotation.TargetApi;
//import android.app.Application;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Build;
//import android.support.multidex.MultiDex;
//
//import com.tencent.tinker.anno.DefaultLifeCycle;
//import com.tencent.tinker.lib.tinker.Tinker;
//import com.tencent.tinker.lib.tinker.TinkerInstaller;
//import com.tencent.tinker.loader.app.ApplicationLike;
//import com.tencent.tinker.loader.shareutil.ShareConstants;
//
///**
// * Created by desaco on 2017/6/23.
// */
//@DefaultLifeCycle(
//        application = ".AppContext", flags = ShareConstants.TINKER_ENABLE_ALL
//)
//public class AppContextLike extends ApplicationLike {
//
//
//    public AppContextLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
//        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        TinkerInstaller.install(this);
//    }
//    /**
//     * install multiDex before install tinker
//     * so we don't need to put the tinker lib classes in the main dex
//     *
//     * @param base
//     */
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    @Override
//    public void onBaseContextAttached(Context base) {
//        super.onBaseContextAttached(base);
//        //you must install multiDex whatever tinker is installed!
//        MultiDex.install(base);
//
////        AppContextLike.application = getApplication();
////        AppContextLike.context = getApplication();
////        TinkerManager.setTinkerApplicationLike(this);
////
////        TinkerManager.initFastCrashProtect();
////        //should set before tinker is installed
////        TinkerManager.setUpgradeRetryEnable(true);
////
////        //optional set logIml, or you can use default debug log
////        TinkerInstaller.setLogIml(new MyLogImp());
////
////        //installTinker after load multiDex
////        //or you can put com.tencent.tinker.** to main dex
////        TinkerManager.installTinker(this);
//        Tinker tinker = Tinker.with(getApplication());
//    }
//
//    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
//    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
//        getApplication().registerActivityLifecycleCallbacks(callback);
//    }
//}