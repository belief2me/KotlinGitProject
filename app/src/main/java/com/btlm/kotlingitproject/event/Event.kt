package com.btlm.kotlingitproject.event

import com.btlm.kotlingitproject.bean.app.video.VideoDetail
import com.btlm.kotlingitproject.bean.app.video.VideoDetailComment
import com.btlm.kotlingitproject.bean.search.Search


/**
 * Created by Administrator on 2017/12/20.
 */
object Event {
    class RegionEntrancePositionEvent {
        var position: Int = -1
    }
    class StartNavigationEvent{
        var start : Boolean = false
    }
    class VideoDetailEvent{
        var videoDetail : VideoDetail.DataBean? = null
    }
    class VideoDetailCommentEvent {
        var videoDetailComment: VideoDetailComment.DataBean? = null
    }
    class SearchArchiveEvent {
        var itemBean: Search.DataBean.ItemsBean? = null
        var seasonCount: Int = 0
        var movieCount: Int = 0
    }

}