package com.btlm.kotlingitproject.mvp.presenter.discover



import com.btlm.kotlingitproject.base.BaseObjectSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.discover.Community
import com.btlm.kotlingitproject.mvp.contract.discover.InterestContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.handleResult
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:活动中心resenter
 */

class InterestPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<InterestContract.View>(), InterestContract.Presenter<InterestContract.View> {
    override fun getInterestData() {
        addSubscribe(retrofitHelper.getInterestAd()
                .compose(handleResult())
                .flatMap { interestAd ->
                    mView?.showInterestAd(interestAd)
                    retrofitHelper.getInterestCategrory()
                }
                .compose(handleResult())
                .flatMap { interestCategrory ->
                    mView?.showInterestCategrory(interestCategrory.result)
                    retrofitHelper.getCommunity()
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<Community>(mView) {
                    override fun onSuccess(t: Community) {
                        mView?.onComplete()
                        mView?.showCommunity(t)
                    }
                }))

    }
}
