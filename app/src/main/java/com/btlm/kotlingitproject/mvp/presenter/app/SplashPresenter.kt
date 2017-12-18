package com.btlm.kotlingitproject.mvp.presenter.app

import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.app.Splash
import com.btlm.kotlingitproject.mvp.contract.app.SplashContract
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/16.
 */
class SplashPresenter @Inject constructor(private val retrofitHelper : RetrofitHelper) : RxPresenter<SplashContract.View>()
        ,SplashContract.Presenter<SplashContract.View>{
    override fun getSplashData() {
        addSubscribe(retrofitHelper.getSplash()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<Splash>(mView){
                    override fun onSuccess(t: Splash) {
                        if(t.code == 0){
                            mView?.showSplash(t)
                        }
                    }
                    override fun onFailure(code: Int, message: String) {
                        mView?.showError(message)
                    }
                }))
    }

    override fun setCountDown() {
        val count = 5L
        addSubscribe(Flowable.interval(0,1,TimeUnit.SECONDS)
                .map {
                    count - it
                }
                .take(count + 1)
                .compose(rxSchedulerHelper())
                .subscribe { mView?.showCountDown(it.toInt()) })
    }
}