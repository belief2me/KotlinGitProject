package com.btlm.kotlingitproject.mvp.presenter.region



import com.btlm.kotlingitproject.base.BaseObjectSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.region.RegionRecommend
import com.btlm.kotlingitproject.mvp.contract.region.RegionTypeRecommendContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:分区推荐presenter
 */

class RegionTypeRecommendPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<RegionTypeRecommendContract.View>(), RegionTypeRecommendContract.Presenter<RegionTypeRecommendContract.View> {

    override fun getRegionRecommendData(tid: Int) {
        val subscriber = retrofitHelper.getRegionRecommend(tid)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<RegionRecommend>(mView) {
                    override fun onSuccess(t: RegionRecommend) {
                        mView?.showRegionRecommend(t)
                    }
                })
        addSubscribe(subscriber)
    }

}
