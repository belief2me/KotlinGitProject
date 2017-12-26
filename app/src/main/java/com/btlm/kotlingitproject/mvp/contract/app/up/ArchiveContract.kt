package com.btlm.kotlingitproject.mvp.contract.app.up

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.user.MulUpDetail


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:45
 * * 描述:欢迎界面Contract
 */

interface ArchiveContract {
    interface View : BaseContract.BaseView {

        fun showArchive(mulUpDetailList: List<MulUpDetail>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getArchiveData()

    }
}
