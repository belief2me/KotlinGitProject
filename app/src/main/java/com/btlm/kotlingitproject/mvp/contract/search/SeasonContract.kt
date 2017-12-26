package com.btlm.kotlingitproject.mvp.contract.search

import com.btlm.kotlingitproject.bean.search.Season


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface SeasonContract {

    interface View : BaseSearchContract.View {

        fun showSearchSeason(season: Season)

    }

    interface Presenter<in T> : BaseSearchContract.Presenter<T> {

        // void getSearchArchiveData(String keyword, int page, int pagesize);

        fun getSearchSeasonData()

    }
}
