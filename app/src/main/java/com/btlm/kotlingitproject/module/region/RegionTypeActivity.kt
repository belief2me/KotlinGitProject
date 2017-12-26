package com.btlm.kotlingitproject.module.region

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.constant.Constants
import com.btlm.kotlingitproject.mvp.contract.region.AllRegionRankPositionContract
import com.btlm.kotlingitproject.mvp.presenter.region.AllRegionRankPositionPresenter
import com.yoyiyi.soleil.bean.region.RegionTagType
import javax.annotation.Nullable

class RegionTypeActivity : BaseRegionActivity<AllRegionRankPositionPresenter,Nullable>(), AllRegionRankPositionContract.View {

    lateinit var mRegionType : RegionTagType
    lateinit  var mTitle : String
    companion object {
        fun startActivity(context: Context,type : RegionTagType){
            val bundle = Bundle()
            val intent = Intent(context,RegionTypeActivity::class.java)
            bundle.putParcelable(Constants.EXTRA_PARCELABLE,type)
            intent.putExtra(Constants.EXTRA_BUNDLE,bundle)
            context.startActivity(intent)
        }
    }
    override fun showEventPosition(postion: Int) {
        //设置位置 viewpager
        mViewPager?.currentItem = postion + 1
    }

    override fun getLayoutId(): Int {
       return R.layout.activity_region_type
    }

    override fun initVariables() {
        intent?.let {
            val bundle = it.getBundleExtra(Constants.EXTRA_BUNDLE)
            mRegionType = bundle.getParcelable<Parcelable>(Constants.EXTRA_PARCELABLE) as RegionTagType
        }
        mTitle = mRegionType.name
        mTitles.add("推荐")
        // 推荐
        mFragment.add(RegionTypeRecommendFragment.newInstance(mRegionType.tid))
        mRegionType.children.forEach { (tid, _, name) ->
            mTitles.add(name)
            //其他标签页面
            mFragment.add(RegionTypeFragment.newInstance(tid))
        }
    }

    override fun initWidget() {
        super.initWidget()
        mViewPager?.offscreenPageLimit = mRegionType.children.size + 1
        mViewPager?.adapter = BaseRegionTypeAdapter(supportFragmentManager)
        mSlidingTabLayout?.setViewPager(mViewPager)
        mViewPager?.currentItem = 0
    }

    override fun initToolbar() {
        super.initToolbar()
        setTitle(mTitle)
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        //设置推荐Fragment entrance监听
        mPresenter.getEventPosition()
    }
}
