package com.btlm.kotlingitproject.mvp.presenter.app.up


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.user.UpDetail
import com.btlm.kotlingitproject.mvp.contract.app.up.UpDetailContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:44
 * * 描述:up详情Presenter
 */

class UpDetailPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<UpDetailContract.View>(), UpDetailContract.Presenter<UpDetailContract.View> {

    override fun getUpDetailData() {
        val subscriber = mRetrofitHelper.getUpDetail()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<UpDetail>(mView) {
                    override fun onSuccess(t: UpDetail) {
                        mView?.showUpDetail(t)
                    }
                })
        addSubscribe(subscriber)

    }
}
