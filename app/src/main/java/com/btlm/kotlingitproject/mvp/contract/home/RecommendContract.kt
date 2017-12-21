package com.btlm.kotlingitproject.mvp.contract.home

import com.btlm.kotlingitproject.base.BaseContract
import com.yoyiyi.soleil.bean.recommend.Recommend

/**
 * Created by Administrator on 2017/12/21.
 */
interface RecommendContract {
    interface View : BaseContract.BaseView{
        fun showRecommend(recommend : List<Recommend>)
    }
    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getRecommendData()
    }
}