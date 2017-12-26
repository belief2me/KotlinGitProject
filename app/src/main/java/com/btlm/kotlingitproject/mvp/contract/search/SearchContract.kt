package com.btlm.kotlingitproject.mvp.contract.search

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.search.Search


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/18 13:57
 * * 描述:搜索
 */

interface SearchContract {
    interface View : BaseContract.BaseView {

        fun showSearch(search: Search)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getSearchData()
    }
}
