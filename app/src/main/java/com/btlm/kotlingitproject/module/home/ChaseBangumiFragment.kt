package com.btlm.kotlingitproject.module.home

import android.support.v7.widget.LinearLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.home.section.chase.*
import com.btlm.kotlingitproject.base.BaseRefreshFragment
import com.btlm.kotlingitproject.bean.chase.ChaseBangumi
import com.btlm.kotlingitproject.bean.chase.RecommendBangumi
import com.btlm.kotlingitproject.mvp.contract.home.ChaseBangumiContract
import com.btlm.kotlingitproject.mvp.presenter.home.ChaseBangumiPresenter
import com.btlm.kotlingitproject.widget.section.SectionedRVAdapter

/**
 * Created by Administrator on 2017/12/22.
 */
class ChaseBangumiFragment :BaseRefreshFragment<ChaseBangumiPresenter, ChaseBangumi.Follows>(),
ChaseBangumiContract.View{

    private var mSectionedAdapter: SectionedRVAdapter? = null
    @Volatile private var mChaseBangumi: ChaseBangumi? = null
    private var mRecommendCnBean: RecommendBangumi.RecommendCn? = null
    private var mRecommendJpBean: RecommendBangumi.RecommendJp? = null
    private var mRecommendBangumi: RecommendBangumi? = null

    companion object {
        fun newInstance() : ChaseBangumiFragment{
            return ChaseBangumiFragment()
        }
    }
    override fun showChaseBangumi(chaseBangumi: ChaseBangumi) {
        mChaseBangumi = chaseBangumi
    }

    override fun showRecommendBangumi(recommendBangumi: RecommendBangumi) {
        mChaseBangumi?.follows?.let {
            mList.addAll(it)
        }
        mRecommendBangumi = recommendBangumi
        mRecommendCnBean = recommendBangumi.recommend_cn
        mRecommendJpBean = recommendBangumi.recommend_jp
        finishTask()
    }

    override fun clear() {
        mSectionedAdapter?.removeAllSections()
    }

    override fun finishTask() {
        mSectionedAdapter?.addSection(ChaseIndexSection())
        mSectionedAdapter?.addSection(ChaseFollowSection("${mChaseBangumi?.update_count}", mList))
        mRecommendBangumi?.ad?.let {
            if (it.isNotEmpty()) {
                mSectionedAdapter?.addSection(ChaseAdSection(it[0]))
            }
        }
        mSectionedAdapter?.addSection(mRecommendJpBean?.recommend?.let {
            mRecommendJpBean?.foot?.get(0)?.let { it1 -> ChaseRecommendJPSection(it, it1) }
        })
        mSectionedAdapter?.addSection(mRecommendCnBean?.recommend?.let { mRecommendCnBean?.foot?.get(0)?.let { it1 -> ChaseRecommendCNSection(it, it1) } })
        mSectionedAdapter?.notifyDataSetChanged()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_chase_bangumi
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getChaseBangumiData()
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        mRecycler?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }
}