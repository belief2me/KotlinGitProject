package com.btlm.kotlingitproject.mvp.presenter.app.video


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.app.video.MulSummary
import com.btlm.kotlingitproject.event.Event
import com.btlm.kotlingitproject.mvp.contract.app.video.SummaryContract
import com.btlm.kotlingitproject.rx.RxBus
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:44
 * * 描述:启动界面Presenter
 */

class SummaryPresenter @Inject
constructor() : RxPresenter<SummaryContract.View>(), SummaryContract.Presenter<SummaryContract.View> {

    override fun getSummaryData() {
        addSubscribe(RxBus.toFlowable(Event.VideoDetailEvent::class.java)
                .map({ videoDetailEvent ->
                    val videoDetail = videoDetailEvent.videoDetail

                    val mulSummaries = mutableListOf<MulSummary>()
                    mulSummaries += MulSummary(
                            itemTypez = MulSummary.TYPE_DES,
                            title = videoDetail?.title,
                            desc = videoDetail?.desc,
                            state = videoDetail?.stat)
                    mulSummaries += MulSummary(itemTypez = MulSummary.TYPE_OWNER,
                            owner = videoDetail?.owner,
                            ctime = videoDetail?.ctime?.toLong() ?: 0,
                            tags = videoDetail?.tag)

                    mulSummaries += MulSummary(itemTypez = MulSummary.TYPE_RELATE_HEAD)

                    videoDetail?.relates?.forEach {
                        mulSummaries += MulSummary(itemTypez = MulSummary.TYPE_RELATE, relates = it)
                    }
                    mulSummaries
                })
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulSummary>>(mView) {
                    override fun onSuccess(t: List<MulSummary>) {
                        mView?.showSummary(t)
                    }
                }))
    }
}
