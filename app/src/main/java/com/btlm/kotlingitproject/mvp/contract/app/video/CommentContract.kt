package com.btlm.kotlingitproject.mvp.contract.app.video

import com.btlm.kotlingitproject.base.BaseContract
import com.yoyiyi.soleil.bean.app.video.MulComment

/**
 * Created by Administrator on 2017/12/21.
 */
class CommentContract {
    interface View : BaseContract.BaseView{
        fun showComment(mulComments : List<MulComment>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getCommentData()
    }
}