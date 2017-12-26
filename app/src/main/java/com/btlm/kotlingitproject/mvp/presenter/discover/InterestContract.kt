package com.btlm.kotlingitproject.mvp.contract.discover

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.discover.Community
import com.btlm.kotlingitproject.bean.discover.InterestAd
import com.btlm.kotlingitproject.bean.discover.InterestCategrory


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:话题中心Contract
 */

interface InterestContract {

    interface View : BaseContract.BaseView {

        fun showInterestAd(interestAdList: InterestAd)

        fun showCommunity(community: Community)

        fun showInterestCategrory(interestCategroryList: List<InterestCategrory.ResultBean>)

        fun onComplete()
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getInterestData()
    }
}
