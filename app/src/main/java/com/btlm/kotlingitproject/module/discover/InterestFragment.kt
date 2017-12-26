package com.btlm.kotlingitproject.module.discover

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.discover.InterestAdapter
import com.btlm.kotlingitproject.base.BaseRefreshFragment
import com.btlm.kotlingitproject.bean.discover.Community
import com.btlm.kotlingitproject.bean.discover.InterestAd
import com.btlm.kotlingitproject.bean.discover.InterestCategrory
import com.btlm.kotlingitproject.bean.discover.MulInterest
import com.btlm.kotlingitproject.mvp.contract.discover.InterestContract
import com.btlm.kotlingitproject.mvp.presenter.discover.InterestPresenter
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.utils.ToastUtils



/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 22:25
 * * 描述:兴趣圈
 */
class InterestFragment : BaseRefreshFragment<InterestPresenter, MulInterest>(), InterestContract.View {
    private var mAdapter: InterestAdapter? = null
    private var mInterestAdList: InterestAd? = null
    private var mInterestCategroryList: List<InterestCategrory.ResultBean>? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_interest
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getInterestData()
    }

    override fun initRecyclerView() {
        mAdapter = InterestAdapter(mList)
        mRecycler?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }


    override fun showInterestAd(interestAdList: InterestAd) {
        mInterestAdList = interestAdList
    }

    override fun showCommunity(community: Community) {
        mList.add(MulInterest(MulInterest.TYPE_BANNER, interestAdList = mInterestAdList))
        mList.add(MulInterest(MulInterest.TYPE_CATEGRORY, mInterestCategroryList))
        mList.add(MulInterest(MulInterest.TYPR_HEADER))
        community.result.forEach { mList.add(MulInterest(MulInterest.TYPR_ITEM, community = it)) }
        finishTask()
    }

    override fun showInterestCategrory(interestCategroryList: List<InterestCategrory.ResultBean>) {
        mInterestCategroryList = interestCategroryList
    }

    override fun onComplete() {
        AppUtils.runOnUIDelayed({ mRefresh?.isRefreshing = false }, 650)
        if (mIsRefreshing) {
            mList.clear()
            clear()
            ToastUtils.showSingleLongToast("刷新成功")
        }
        mIsRefreshing = false
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun complete() {
        mError?.let {
            gone(it)
        }
    }
}
