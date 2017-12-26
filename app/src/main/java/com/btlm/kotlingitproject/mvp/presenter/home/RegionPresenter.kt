package com.btlm.kotlingitproject.mvp.presenter.home


import com.btlm.kotlingitproject.base.BaseListSubscriber
import com.btlm.kotlingitproject.base.RxPresenter
import com.btlm.kotlingitproject.bean.region.Region
import com.btlm.kotlingitproject.mvp.contract.home.RegionContract
import com.btlm.kotlingitproject.network.helper.RetrofitHelper
import com.btlm.kotlingitproject.rx.rxSchedulerHelper
import com.btlm.kotlingitproject.utils.JsonUtils
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.yoyiyi.soleil.bean.region.RegionTagType
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:首页直播Presenter
 */
class RegionPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<RegionContract.View>(), RegionContract.Presenter<RegionContract.View> {

    override fun getRegionData() {
        val subscriber = Flowable.just(JsonUtils.readJson("region.json"))
                .flatMap {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val array = `object`.getAsJsonArray("data")
                    val regionTypes = array.mapTo(ArrayList<RegionTagType>()) { gson.fromJson(it, RegionTagType::class.java) }
                    mView?.showRegionType(regionTypes)
                    mRetrofitHelper.getRegion()
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseListSubscriber<Region>(mView) {
                    override fun onSuccess(t: List<Region>) {
                        mView?.showRegion(t)
                    }
                })
        addSubscribe(subscriber)
    }

}
