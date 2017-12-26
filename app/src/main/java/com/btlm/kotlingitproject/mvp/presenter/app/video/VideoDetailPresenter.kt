package com.btlm.kotlingitproject.mvp.presenter.app.video

import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.app.video.VideoDetailComment
import com.btlm.kotlingitproject.mvp.contract.app.video.VideoDetailContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * Created by Administrator on 2017/12/21.
 */
class VideoDetailPresenter @Inject constructor(private val retrofitHelper : RetrofitHelper)
    : RxPresenter<VideoDetailContract.View>(),VideoDetailContract.Presenter<VideoDetailContract.View>{
    override fun getVideoDetailData() {
        /* BaseObjectSubscriber<VideoDetailComment> subscriber = mRetrofitHelper.getVideoDetail()
                  .flatMap(videoDetail -> {
                      mView.showVideoDetail(videoDetail);
                      return mRetrofitHelper.getVideoDetailComment();
                  })
                  .compose(RxUtils.rxSchedulerHelper())
                  .subscribeWith(new BaseObjectSubscriber<VideoDetailComment>(mView) {
                      @Override
                      public void onSuccess(VideoDetailComment videoDetailComment) {
                          mView.showVideoDetailComment(videoDetailComment);
                      }
                  });*/
        addSubscribe(retrofitHelper.getVideoDetail()
                .flatMap({
                    mView?.showVideoDetail(it.data)
                    retrofitHelper.getVideoDetailComment()
                }).compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<VideoDetailComment>(mView) {
                    override fun onSuccess(t: VideoDetailComment) {
                        mView?.showVideoDetailComment(t.data)
                    }
                }))
    }
}