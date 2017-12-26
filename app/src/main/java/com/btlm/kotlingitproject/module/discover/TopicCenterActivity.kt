package com.btlm.kotlingitproject.module.discover

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.discover.TopicCenterAdapter
import com.btlm.kotlingitproject.base.BaseRefreshActivity
import com.btlm.kotlingitproject.bean.discover.TopicCenter
import com.btlm.kotlingitproject.mvp.contract.discover.TopicCenterContract
import com.btlm.kotlingitproject.mvp.presenter.discover.TopicCenterPresenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 22:28
 * * 描述:话题中心
 */
class TopicCenterActivity : BaseRefreshActivity<TopicCenterPresenter, TopicCenter.ListBean>(), TopicCenterContract.View {

    private var mAdapter: TopicCenterAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_topic_center
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "话题中心"
    }

    override fun loadData() {
        mPresenter.getTopicCenterData()
    }


    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initRecyclerView() {
        mAdapter = TopicCenterAdapter(mList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }

    override fun showTopicCenter(topicCenterList: List<TopicCenter.ListBean>) {
        mList?.addAll(topicCenterList)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }
}
