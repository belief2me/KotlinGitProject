package com.btlm.kotlingitproject.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.widget.ProgressWheel
import org.jetbrains.anko.toast

/**
 * Created by Administrator on 2017/12/20.
 */
abstract class BaseRefreshActivity<T : BaseContract.BasePresenter<*>,K> : BaseInjectActivity<T>(),SwipeRefreshLayout.OnRefreshListener{

    protected var mRecycler : RecyclerView? = null
    protected var mRefresh : SwipeRefreshLayout? = null
    protected var mIsRefreshing = false
    protected var mList : MutableList<K>? = ArrayList()
    private var mLoading : ProgressWheel? = null

    override fun initWidget() {
        mRefresh = findViewById(R.id.refresh)
        mRecycler = findViewById(R.id.recycler)
        mLoading = findViewById(R.id.pw_loading)
        initRefreshLayout()
        initRecyclerView()
    }

    protected fun initRefreshLayout(){
        mRefresh?.let {
            it.setColorSchemeResources(R.color.colorPrimary)
            mRecycler?.post {
                it.isRefreshing = true
                loadData()
            }
            it.setOnRefreshListener(this)
        }
    }

    override fun onRefresh() {
        clearData()
        loadData()
    }
    protected open fun clearData(){
        mIsRefreshing = true
    }

    override fun complete() {
        super.complete()
        AppUtils.runOnUIDelayed({
            mRefresh?.isRefreshing = false
        },650)
        if(mIsRefreshing){
            mList?.clear()
            clear()
            toast("刷新成功")
        }
        mIsRefreshing = false
        mLoading?.visibility = View.GONE
    }
    protected open fun clear(){}

    override fun initDatas() {
        mRefresh?.let {
            mLoading?.let {
                it.visibility = View.VISIBLE
                AppUtils.runOnUIDelayed({
                    loadData()
                },650)
            }
        }?:super.initDatas()
    }


}