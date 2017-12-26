package com.btlm.kotlingitproject.mvp.contract.region


import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.region.RegionRecommend

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface RegionTypeRecommendContract {

    interface View : BaseContract.BaseView {

        fun showRegionRecommend(regionRecommend: RegionRecommend)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getRegionRecommendData(tid: Int)


    }
}
