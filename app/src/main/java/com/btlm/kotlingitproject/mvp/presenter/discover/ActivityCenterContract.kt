package com.btlm.kotlingitproject.mvp.contract.discover

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.discover.ActivityCenter


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:话题中心Contract
 */

interface ActivityCenterContract {

    interface View : BaseContract.BaseView {

        fun showActivityCenter(listBeanList: List<ActivityCenter.ListBean>, total: Int)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getActivityCenterData(page: Int, pageSize: Int)
    }
}
