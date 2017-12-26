package com.btlm.kotlingitproject.mvp.contract.app.video

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.app.video.VideoDetail
import com.btlm.kotlingitproject.bean.app.video.VideoDetailComment

/**
 * Created by Administrator on 2017/12/21.
 */
interface VideoDetailContract {
    interface View : BaseContract.BaseView{
        fun showVideoDetail(videoDetail : VideoDetail.DataBean)
        fun showVideoDetailComment(videoDetailComment: VideoDetailComment.DataBean)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getVideoDetailData()
    }
}