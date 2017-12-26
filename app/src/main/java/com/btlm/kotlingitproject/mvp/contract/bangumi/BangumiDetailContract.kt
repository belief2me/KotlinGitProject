package com.btlm.kotlingitproject.mvp.contract.bangumi

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.bangumi.MulBangumiDetail

/**
 * Created by Administrator on 2017/12/22.
 */
interface BangumiDetailContract {
    interface View : BaseContract.BaseView{
        fun showMulBangumiDetail(mulBangumiDetails: List<MulBangumiDetail>, title : String)

    }
    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getBangumiDetailData()
    }

}