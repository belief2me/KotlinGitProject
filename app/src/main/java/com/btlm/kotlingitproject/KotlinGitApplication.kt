package com.btlm.kotlingitproject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.utils.CrashHandler
import com.btlm.kotlingitproject.utils.LogUtils
import com.btlm.kotlingitproject.utils.PrefsUtils
import com.btlm.kotlingitproject.utils.net.NetworkUtils
import com.facebook.stetho.Stetho

/**
 * Created by Administrator on 2017/12/15.
 */
class KotlinGitApplication : Application() {

    private var allActivities: HashSet<Activity>? = null





    companion object {
        /**
         * lateinit : properties declared as having a non-null must be initalized in the constructor;
         */
        lateinit var instance: KotlinGitApplication
    }

    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
        instance = this
        initNetwork()
        initStetho()
        initCrashHandler()
        initLog()
        initPrefs()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    /**
     * 增加Activity
     */
    fun addActivity(act : Activity){
        if(allActivities == null){
            allActivities = HashSet()
        }else{
            allActivities?.add(act)
        }
    }

    /**
     * 移除Activity
     * @param act
     */
    fun removeActivity(act :Activity){
        allActivities?.remove(act)
    }

    @Synchronized fun exitApp(){
        allActivities?.let {
            for (act in it){
                act.finish()
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    /**
     * 开启网络监听
     */
    private fun initNetwork() {
        NetworkUtils.startNetService(this)
    }

    /**
     * 初始化调试
     */
    private fun initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build()
        )
    }

    /**
     * 初始化崩溃日志
     */
    private fun initCrashHandler(){
        CrashHandler.getInstance().init(this)
    }

    /**
     * 初始化log
     */
    private fun initLog(){
        LogUtils.init(this)
    }

    /**
     * 初始化sp
     */
    private fun initPrefs(){
        PrefsUtils.init(this,packageName + "_preference", Context.MODE_MULTI_PROCESS)
    }


}