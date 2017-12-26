package com.btlm.kotlingitproject.mvp.presenter.discover


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.discover.TopicCenter
import com.btlm.kotlingitproject.mvp.contract.discover.TopicCenterContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:直播Presenter
 */

class TopicCenterPresenter @Inject
constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<TopicCenterContract.View>(), TopicCenterContract.Presenter<TopicCenterContract.View> {

    override fun getTopicCenterData() {
        addSubscribe(retrofitHelper.getTopicCenter()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<TopicCenter>(mView) {
                    override fun onSuccess(t: TopicCenter) {
                        mView?.showTopicCenter(t.list)
                    }
                }))
    }

}
