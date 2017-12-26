package com.btlm.kotlingitproject.mvp.presenter.search


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.search.SearchArchive
import com.btlm.kotlingitproject.mvp.contract.search.TotalSearchContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.handleResult
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:直播Presenter
 */

class TotalSearchPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<TotalSearchContract.View>(), TotalSearchContract.Presenter<TotalSearchContract.View> {

    override fun getSearchNavData(keyword: String, page: Int, pagesize: Int) {
        val subscriber = retrofitHelper.getSearchArchive(keyword, page, pagesize)
                .doOnSubscribe { mView?.showLoading() }
                .delay(1, TimeUnit.SECONDS)
                .compose(handleResult())
                .map(SearchArchive::nav)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<SearchArchive.NavBean>>(mView) {
                    override fun onSuccess(t: List<SearchArchive.NavBean>) {
                        mView?.showSearchNav(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
