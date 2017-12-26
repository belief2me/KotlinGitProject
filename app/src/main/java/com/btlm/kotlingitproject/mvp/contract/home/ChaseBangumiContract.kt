package com.btlm.kotlingitproject.mvp.contract.home

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.chase.ChaseBangumi
import com.btlm.kotlingitproject.bean.chase.RecommendBangumi

/**
 * Created by Administrator on 2017/12/22.
 */
interface ChaseBangumiContract {
    interface View : BaseContract.BaseView{
        fun showChaseBangumi(chaseBangumi : ChaseBangumi)
        fun showRecommendBangumi(recommendBangumi : RecommendBangumi)
    }
    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getChaseBangumiData()
    }
}