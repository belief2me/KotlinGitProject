package com.btlm.kotlingitproject.mvp.contract.recommend

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.recommend.AllStationRank


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:全区排行Contract
 */

interface AllStationRankContract {

    interface View : BaseContract.BaseView {

        fun showAllStationRank(regionRank: List<AllStationRank.RankBean.ListBean>)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getAllStationRankData(type: String)
    }
}
