package com.btlm.kotlingitproject.mvp.contract.region

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.region.RegionType

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface RegionTypeContract {

    interface View : BaseContract.BaseView {

        fun showRegionType(regionType: RegionType)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getRegionTypeData(rid: Int)
    }
}
