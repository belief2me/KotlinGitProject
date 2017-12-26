package com.btlm.kotlingitproject.mvp.contract.search

import com.btlm.kotlingitproject.bean.search.Up


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface UpContract {

    interface View : BaseSearchContract.View {

        fun showSearchUp(up: Up)

    }

    interface Presenter<in T> : BaseSearchContract.Presenter<T> {


        fun getSearchUpData()

    }
}
