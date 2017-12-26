package com.btlm.kotlingitproject.mvp.presenter.region



import com.btlm.kotlingitproject.base.BaseObjectSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.region.RegionType
import com.btlm.kotlingitproject.mvp.contract.region.RegionTypeContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:分区Type Presenter
 */
class RegionTypePresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<RegionTypeContract.View>(), RegionTypeContract.Presenter<RegionTypeContract.View> {

    override fun getRegionTypeData(rid: Int) {
        addSubscribe(retrofitHelper.getRegionType(rid)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<RegionType>(mView) {
                    override fun onSuccess(t: RegionType) {
                        mView?.showRegionType(t)
                    }
                }))
    }
}
