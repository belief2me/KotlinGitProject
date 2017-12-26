package com.btlm.kotlingitproject.mvp.contract.discover

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.discover.TopicCenter


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:话题中心Contract
 */

interface TopicCenterContract {

    interface View : BaseContract.BaseView {

        fun showTopicCenter(topicCenterList: List<TopicCenter.ListBean>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getTopicCenterData()
    }
}
