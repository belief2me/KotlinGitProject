package com.btlm.kotlingitproject.mvp.contract.app.video

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.app.video.VideoPlayer
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser

/**
 * Created by Administrator on 2017/12/22.
 */
interface VideoPlayerContract {
    interface View : BaseContract.BaseView{
        fun showVideoPlayer(videoPlayer : VideoPlayer)

        fun showAnimLoading()

        fun showDanmaku(baseDanmakuParser: BaseDanmakuParser)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getVideoPlayerData()
    }
}