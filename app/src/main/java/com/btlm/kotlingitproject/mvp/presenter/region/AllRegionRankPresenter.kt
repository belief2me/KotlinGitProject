package com.btlm.kotlingitproject.mvp.presenter.region


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.region.AllRegionRank
import com.btlm.kotlingitproject.mvp.contract.region.AllRegionRankContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:全区排行presenter
 */
class AllRegionRankPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<AllRegionRankContract.View>(), AllRegionRankContract.Presenter<AllRegionRankContract.View> {


    override fun getAllRegionRankData(type: String) {
        addSubscribe(retrofitHelper.getAllRegionRank(type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<AllRegionRank>(mView) {
                    override fun onSuccess(t: AllRegionRank) {
                        mView?.showAllRegionRank(t.rank.list)
                    }
                }))

    }
}
