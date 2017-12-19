package com.btlm.kotlingitproject.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.btlm.kotlingitproject.R
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * Created by Administrator on 2017/12/19.
 */
abstract class BaseFragment : RxFragment(){

    protected var mActivity : Activity? = null
    protected var mInflater : LayoutInflater? = null
    protected var mContext : Context? = null
    protected var mRootView : View? = null

    //标志位 标志已经初始化完成
    protected var mIsPrepared : Boolean = false
    // 标志位 fragment是否可见
    protected var mIsVisible : Boolean = false
    protected var mError : ConstraintLayout? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity?
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(mRootView != null){
            val parent = mRootView?.parent as ViewGroup
            parent.removeView(mRootView)
        }else{
            mRootView = inflater?.inflate(getLayoutId(),container,false)
            mActivity = getSupportActivity()
            mContext = mActivity
            this.mInflater = inflater
        }
        return mRootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInject()
        initPresenter()
        initVariables()
        mError = mRootView?.findViewById<ConstraintLayout>(R.id.cl_error)
        initWidget()
        initSetListener()
        finishCreateView(savedInstanceState)
        initDatas()
    }
    protected open fun initDatas(){
        loadData()
    }
    protected open fun loadData(){}


    open fun finishCreateView(state : Bundle?){
        mIsPrepared = true
        lazyLoad()
    }
    protected open fun lazyLoad(){
        if(!mIsPrepared || !mIsVisible)
            return@lazyLoad
        lazyLoadData()
        mIsPrepared = false
    }
    protected open fun lazyLoadData(){

    }

    protected open fun initSetListener(){

    }

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    open fun initWidget(){

    }

    /**
     * 初始化变量
     */
    open fun initVariables(){}

    /**
     * 初始化Presenter
     */
    protected open fun initPresenter(){

    }

    /**
     * 注入dagger2依赖
     */
    protected open fun initInject(){

    }

    /**
     * 布局
     */
    abstract fun getLayoutId() : Int

    /**
     * 获取Activity
     */
    fun getSupportActivity() : FragmentActivity = super.getActivity()

    override fun onDetach() {
        super.onDetach()
        this.mActivity = null
    }

    /**
     * 初始化RV
     */
    protected open fun initRecyclerView(){

    }

    /**
     * 初始化刷新
     */
    protected open fun initRefreshLayout(){

    }

    /**
     * 清除数据
     */
    protected open fun clearData(){

    }
    protected open fun finishTask(){

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(userVisibleHint){
            mIsVisible = true
            onVisible()
        }else{
            mIsVisible = false
            onInvisible()
        }
    }
    protected open fun onVisible(){
        lazyLoad()
    }
    protected open fun onInvisible(){

    }
    /**
     * 获取ApplicationContext 信息
     * @return Context
     */
    fun getApplicationContext(): Context? =
            if (this.mContext == null) {
                if (activity == null) {
                    null
                } else {
                    activity.application
                }
            } else {
                this.mContext?.applicationContext
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