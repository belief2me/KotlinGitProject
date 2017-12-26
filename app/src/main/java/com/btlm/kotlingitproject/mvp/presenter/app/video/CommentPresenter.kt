package com.btlm.kotlingitproject.mvp.presenter.app.video

import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.app.video.MulComment
import com.btlm.kotlingitproject.event.Event
import com.btlm.kotlingitproject.mvp.contract.app.video.CommentContract
import com.btlm.kotlingitproject.rx.RxBus
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/22.
 */
class CommentPresenter @Inject constructor() : RxPresenter<CommentContract.View>() ,
        CommentContract.Presenter<CommentContract.View>
{
    override fun getCommentData() {
        addSubscribe(RxBus.toFlowable(Event.VideoDetailCommentEvent::class.java)
                .map {
                    val videoDetailComment = it.videoDetailComment
                    val mulComments = mutableListOf<MulComment>()
                    videoDetailComment?.hots?.forEach {
                        mulComments.add(MulComment(itemTypez = MulComment.TYPE_COMMENT_HOT_ITEM, hotsBean = it))
                    }
                    mulComments.add(MulComment(itemTypez = MulComment.TYPE_COMMENT_MORE))

                    videoDetailComment?.replies?.forEach {
                        mulComments.add(MulComment(
                                itemTypez = MulComment.TYPE_COMMENT_NOMAL_ITEM,
                                repliesBean = it))
                    }
                    mulComments
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulComment>>(mView) {
                    override fun onSuccess(t: List<MulComment>) {
                        mView!!.showComment(t)
                    }
                }))

    }
}