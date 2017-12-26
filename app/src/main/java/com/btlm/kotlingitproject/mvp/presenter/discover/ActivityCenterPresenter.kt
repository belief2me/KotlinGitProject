package com.btlm.kotlingitproject.mvp.presenter.discover

import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.discover.ActivityCenter
import com.btlm.kotlingitproject.mvp.contract.discover.ActivityCenterContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:活动中心resenter
 */

class ActivityCenterPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<ActivityCenterContract.View>(), ActivityCenterContract.Presenter<ActivityCenterContract.View> {

    override fun getActivityCenterData(page: Int, pageSize: Int) {
        addSubscribe(retrofitHelper.getActivityCenter(page, pageSize)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<ActivityCenter>(mView) {
                    override fun onSuccess(t: ActivityCenter) {
                            mView?.showActivityCenter(t.list, t.total)
                    }
                }))
    }
}
