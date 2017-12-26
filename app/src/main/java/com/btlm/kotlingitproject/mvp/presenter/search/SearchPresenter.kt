package com.btlm.kotlingitproject.mvp.presenter.search

import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.search.Search
import com.btlm.kotlingitproject.mvp.contract.search.SearchContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/18 14:00
 * * 描述:
 */

class SearchPresenter @Inject
constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<SearchContract.View>(), SearchContract.Presenter<SearchContract.View> {

    override fun getSearchData() {
        val subscriber = retrofitHelper.getSearch()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<Search>(mView) {
                    override fun onSuccess(t: Search) {
                        mView?.showSearch(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
