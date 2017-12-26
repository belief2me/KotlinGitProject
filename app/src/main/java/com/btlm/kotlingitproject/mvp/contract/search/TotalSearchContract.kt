package com.btlm.kotlingitproject.mvp.contract.search

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.search.SearchArchive


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface TotalSearchContract {

    interface View : BaseContract.BaseView {

        fun showSearchNav(navBeans: List<SearchArchive.NavBean>)

        fun showLoading()
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getSearchNavData(keyword: String, page: Int, pagesize: Int)
    }
}
