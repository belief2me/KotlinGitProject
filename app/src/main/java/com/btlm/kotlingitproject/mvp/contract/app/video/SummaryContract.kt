package com.btlm.kotlingitproject.mvp.contract.app.video

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.app.video.MulSummary

/**
 * Created by Administrator on 2017/12/21.
 */
interface SummaryContract {
    interface View : BaseContract.BaseView{
        fun showSummary(mulSummaries : List<MulSummary>)
    }
    interface Presenter<in T> : BaseContract.BasePresenter<T>{
        fun getSummaryData()
    }
}