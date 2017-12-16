package com.btlm.kotlingitproject.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.Toolbar
import android.view.View
import com.btlm.kotlingitproject.KotlinGitApplication
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by Administrator on 2017/12/16.
 */
abstract class BaseActivity : RxAppCompatActivity(){
    protected var mToolbar : Toolbar? = null
    protected var mContext : Context? = null
    protected open val mBack = true
    protected var mError : ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mError = findViewById<ConstraintLayout>(R.id.cl_error)
        initStatusBar()
        initInject()
        initPresenter()
        initVariables()
        KotlinGitApplication.instance.addActivity(this)
        mToolbar?.let {
            /**
             * 初始化Toolbar
             */
            initToolbar()
            /**
             * 让组件支持Toolbar
             */
            setSupportActionBar(it)
            if(mBack){
                it.setNavigationOnClickListener { finish() }
            }
        }
        initWidget()
        initDatas()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
    }

    /**
     * 完成请求
     */
    open fun finishTask(){

    }

    open fun initRecyclerView(){}

    /**
     * 初始化数据
     */
    open fun initDatas() {
        loadData()
    }
    /**
     * 加载数据
     */
    open fun loadData() {}
    /**
     * 初始化控件
     */
    open fun initWidget() {}

    /**
     * 初始化Toolbar
     */
    open fun initToolbar() {
        if (mBack) mToolbar?.setNavigationIcon(R.drawable.ic_clip_back_white)
    }

    /**
     * 注入依赖
     */
    open fun initInject(){

    }

    /**
     * 初始化Presenter
     */
    open fun initPresenter(){

    }
    /**
     * 初始化变量
     */
    open fun initVariables() {}

    /**
     * 布局文件
     */
    abstract fun getLayoutId() : Int

    /**
     * 初始化StatusBar
     */
    open fun initStatusBar(){
        StatusBarUtil.setColorNoTranslucent(mContext as Activity,AppUtils.getColor(R.color.colorPrimary))
    }

    /**
     * 隐藏View
     * @param views 视图
     */
    fun gone(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.GONE
            }
        }
    }

    /**
     * 显示View
     * @param views 视图
     */
    fun visible(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.VISIBLE
            }
        }
    }
}