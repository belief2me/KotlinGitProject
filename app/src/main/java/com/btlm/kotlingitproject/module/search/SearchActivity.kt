

package com.btlm.kotlingitproject.module.search
import com.btlm.kotlingitproject.R
import com.btlm.kotlingitproject.bean.search.Search
import com.btlm.kotlingitproject.event.Event
import com.btlm.kotlingitproject.module.region.BaseRegionActivity
import com.btlm.kotlingitproject.mvp.contract.search.SearchContract
import com.btlm.kotlingitproject.mvp.presenter.search.SearchPresenter

import com.btlm.kotlingitproject.rx.RxBus
import com.btlm.kotlingitproject.utils.AppUtils
import com.btlm.kotlingitproject.widget.StatusBarFontUtil
import com.btlm.kotlingitproject.widget.statusbar.StatusBarUtil
import javax.annotation.Nullable

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/18 13:55
 * * 描述:搜索
 */

class SearchActivity : BaseRegionActivity<SearchPresenter, Nullable>(), SearchContract.View {

    private lateinit var mSearch: Search

    override fun getLayoutId(): Int = R.layout.activity_search


    override fun showSearch(search: Search) {
        mSearch = search
        finishTask()
    }

    override fun initStatusBar() {
        //设置状态栏颜色
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.gray_light_30))
        //设置状态栏字体
        StatusBarFontUtil.from(this).setLightStatusBar(true).process()
    }

    override fun initTitle() {
        mTitles.add("综合")
        mTitles.add(mSearch.data.nav[0].name + if (mSearch.data.nav[0].total == 0) "" else "(" + (if (mSearch.data.nav[0].total > 99) "99+" else mSearch.data.nav[0].total) + ")")
        mTitles.add(mSearch.data.nav[1].name + if (mSearch.data.nav[1].total == 0) "" else "(" + (if (mSearch.data.nav[1].total > 99) "99+" else mSearch.data.nav[1].total) + ")")
        mTitles.add(mSearch.data.nav[2].name + if (mSearch.data.nav[2].total == 0) "" else "(" + (if (mSearch.data.nav[2].total > 99) "99+" else mSearch.data.nav[2].total) + ")")

    }

    override fun initFragment() {
        mFragment.add(ArchiveFragment.newsInstance())
        mFragment.add(ArchiveFragment.newsInstance())
        mFragment.add(ArchiveFragment.newsInstance())
        mFragment.add(ArchiveFragment.newsInstance())
//        mFragment.add(SeasonFragment.newsInstance())
//        mFragment.add(UpFragment.newsInstance())
//        mFragment.add(MovieFragment.newsInstance())

    }

    override fun initViewPager() {
        super.initViewPager()
        setCurrentItem(0)
    }

    override fun loadData() {
        mPresenter.getSearchData()
    }


    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initEvent() {
        //发射数据 给首页
        val searchArchiveEvent = Event.SearchArchiveEvent()
        searchArchiveEvent.itemBean = mSearch.data.items
        searchArchiveEvent.movieCount = mSearch.data.nav[2].total
        searchArchiveEvent.seasonCount = mSearch.data.nav[1].total
        RxBus.post(searchArchiveEvent)
    }

}
