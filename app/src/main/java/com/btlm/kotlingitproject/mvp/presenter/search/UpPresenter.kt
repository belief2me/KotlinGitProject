package com.btlm.kotlingitproject.mvp.presenter.search


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.search.Up
import com.btlm.kotlingitproject.mvp.contract.search.UpContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面主页presenter
 */

class UpPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<UpContract.View>(), UpContract.Presenter<UpContract.View> {
    override fun getSearchUpData() {
        val subscriber = retrofitHelper.getUp()
                .compose(rxSchedulerHelper())
                .doOnSubscribe { mView?.showLoading() }
                //  .delay(5, TimeUnit.SECONDS)
                .subscribeWith(object : BaseSubscriber<Up>(mView) {
                    override fun onSuccess(t: Up) {
                        mView?.showSearchUp(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
