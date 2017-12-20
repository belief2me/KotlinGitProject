package com.btlm.kotlingitproject.module.home

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.home.live.LiveAdapter
import com.btlm.kotlingitproject.base.BaseRefreshFragment
import com.btlm.kotlingitproject.bean.live.MulLive
import com.btlm.kotlingitproject.mvp.contract.home.LiveContract
import com.btlm.kotlingitproject.mvp.presenter.home.LivePresenter

/**
 * Created by Administrator on 2017/12/20.
 */
class LiveFragment : BaseRefreshFragment<LivePresenter,MulLive>(),LiveContract.View {

    private var mAdapter : LiveAdapter? = null

    companion object {
        fun newInstance() : LiveFragment{
            return LiveFragment()
        }
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getLiveData()
    }

    override fun initRecyclerView() {
        mAdapter = LiveAdapter(mList)
        mRecycler?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
    }
    override fun showMulLive(mulLives: List<MulLive>) {
        mList.addAll(mulLives)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_live
    }
}