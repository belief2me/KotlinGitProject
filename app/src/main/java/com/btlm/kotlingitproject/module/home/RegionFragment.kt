package com.btlm.kotlingitproject.module.home

import android.support.v7.widget.GridLayoutManager
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.adapter.home.section.region.RegionActivityCenterSection
import com.btlm.kotlingitproject.adapter.home.section.region.RegionEntranceSection
import com.btlm.kotlingitproject.adapter.home.section.region.RegionSection
import com.btlm.kotlingitproject.adapter.home.section.region.RegionTopicSection
import com.btlm.kotlingitproject.base.BaseRefreshFragment
import com.btlm.kotlingitproject.bean.region.Region
import com.btlm.kotlingitproject.mvp.contract.home.RegionContract
import com.btlm.kotlingitproject.mvp.presenter.home.RegionPresenter
import com.btlm.kotlingitproject.widget.section.SectionedRVAdapter
import com.yoyiyi.soleil.bean.region.RegionTagType

/**
 * Created by Administrator on 2017/12/25.
 */
class RegionFragment : BaseRefreshFragment<RegionPresenter, Region>(), RegionContract.View {
    override fun showRegion(regions: List<Region>) {
        mList.addAll(regions)
        finishTask()
    }

    private var mSectionedAdapter: SectionedRVAdapter? = null
    private val mRegionTypeList = arrayListOf<RegionTagType>()

    companion object {
        fun newInstance(): RegionFragment {
            return RegionFragment()
        }
    }


    override fun showRegionType(regionTypes: List<RegionTagType>) {
        mRegionTypeList.addAll(regionTypes)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home_region
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getRegionData()
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = GridLayoutManager(activity, 2)
        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mSectionedAdapter?.getSectionItemViewType(position)) {
                    SectionedRVAdapter.VIEW_TYPE_HEADER -> return 2//2格
                    SectionedRVAdapter.VIEW_TYPE_FOOTER -> return 2//2格
                    else -> return 1
                }
            }
        }
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun finishTask() {
        mSectionedAdapter?.addSection(RegionEntranceSection(mRegionTypeList))
        mList.forEach {
            when (it.type) {
                "topic" -> mSectionedAdapter?.addSection(RegionTopicSection(it.body[0]))
                "activity" -> mSectionedAdapter?.addSection(RegionActivityCenterSection(it.body))//活动中心
                else -> mSectionedAdapter?.addSection(RegionSection(it.title, it.body))//分区和番剧区
            }
        }
        mSectionedAdapter?.notifyDataSetChanged()
    }
}