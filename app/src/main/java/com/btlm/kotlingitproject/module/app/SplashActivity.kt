package com.btlm.kotlingitproject.module.app

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.app.Splash
import com.btlm.kotlingitproject.mvp.contract.app.SplashContract
import com.btlm.kotlingitproject.mvp.presenter.app.SplashPresenter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
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
    }
    override fun showError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun complete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSplash(splash: Splash) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCountDown(count: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}