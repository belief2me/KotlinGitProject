package com.btlm.kotlingitproject.event

import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment

/**
 * Created by Administrator on 2017/12/20.
 */
object Event {
    class StartNavigationEvent{
        var start : Boolean = false
    }
    class VideoDetailEvent{
        var videoDetail : VideoDetail.DataBean? = null
    }
    class VideoDetailCommentEvent {
        var videoDetailComment: VideoDetailComment.DataBean? = null
    }
}