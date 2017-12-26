package com.btlm.kotlingitproject.mvp.contract.home

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionTagType

/**
 * Created by Administrator on 2017/12/25.
 */
interface RegionContract {
    interface View : BaseContract.BaseView{
        fun showRegion(regions : List<Region>)

        fun showRegionType(regionTypes : List<RegionTagType>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getRegionData()
    }
}