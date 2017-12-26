package com.btlm.kotlingitproject.mvp.presenter.recommend


import com.btlm.kotlingitproject.base.BaseSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.recommend.AllStationRank
import com.btlm.kotlingitproject.mvp.contract.recommend.AllStationRankContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:全区排行presenter
 */
class AllStationRankPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<AllStationRankContract.View>(), AllStationRankContract.Presenter<AllStationRankContract.View> {


    /* @Override
    public void getAllRegionRankData(String type) {
        BaseSubscriber<AllRegionRank> subscriber = mRetrofitHelper.getAllRegionRank(type)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<AllRegionRank>(mView) {
                    @Override
                    public void onSuccess(AllRegionRank allRegionRank) {
                        mView.showAllRegionRank(allRegionRank.rank.list);
                    }
                });
        addSubscribe(subscriber);

    }*/

    override fun getAllStationRankData(type: String) {
        addSubscribe(retrofitHelper.getAllStationRank(type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<AllStationRank>(mView) {
                    override fun onSuccess(t: AllStationRank) {
                        mView?.showAllStationRank(t.rank.list)
                    }
                }))
    }
}
