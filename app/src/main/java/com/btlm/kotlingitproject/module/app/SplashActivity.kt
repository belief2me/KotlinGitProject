package com.btlm.kotlingitproject.module.app

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.btlm.kotlingitproject.KotlinGitApplication
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.app.Splash
import com.btlm.kotlingitproject.di.component.DaggerActivityComponent
import com.btlm.kotlingitproject.mvp.contract.app.SplashContract
import com.btlm.kotlingitproject.mvp.presenter.app.SplashPresenter
import com.btlm.kotlingitproject.utils.PrefsUtils
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.di.module.ActivityModule
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/15.
 */
class SplashActivity : RxAppCompatActivity() , SplashContract.View{

    @Inject
    lateinit var mPresenter : SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //影藏状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        //设置透明
        StatusBarUtil.setTranslucent(this)
        //防止重复实例化app
        if(!isTaskRoot && intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intent.action != null && intent.action == Intent.ACTION_MAIN){
            finish()
            return@onCreate
        }
        initInject()
        initWidget()
        loadData()

    }

    /**
     * 请求数据
     */
    fun loadData(){
        mPresenter.getSplashData()
        mPresenter.setCountDown()
    }

    /**
     * 初始化组件
     */
    fun initWidget(){
        RxView.clicks(llCountDown)
                .throttleFirst(3, TimeUnit.SECONDS)//3秒内响应第一次发射数据
                .compose(bindToLifecycle())
                .subscribe { _ -> redirect() }
    }

    /**
     * 跳转
     */
    fun redirect(){
        var flag = PrefsUtils.getInstance().getBoolean(Constants.IS_LOGINED_FLAG,false)
        flag = false
        if(flag){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
    fun initInject(){
        DaggerActivityComponent.builder()
                .appComponent(KotlinGitApplication.instance.appComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        mPresenter.attachView(this)//依赖 保持p和v生命周期一致
    }

    override fun showError(msg: String) {
        ivSplash.setImageResource(R.mipmap.ic_default_bg)
    }

    override fun complete() {
    }

    override fun showSplash(splash: Splash) {
        val urls = splash.data
        urls.let {
            if(it.isNotEmpty()){
                val pos = Random().nextInt(urls.size)
                Glide.with(this)
                        .load(it[pos].thumb)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(ivSplash)
            }else{
                ivSplash.setImageResource(R.mipmap.ic_default_bg)
            }
        }?:ivSplash.setImageResource(R.mipmap.ic_default_bg)
    }

    override fun showCountDown(count: Int) {
        tvCountDown.text = count.toString()
        if(count == 0){
            redirect()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}