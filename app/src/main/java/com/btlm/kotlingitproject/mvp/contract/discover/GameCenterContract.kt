package com.btlm.kotlingitproject.mvp.contract.discover

import com.btlm.kotlingitproject.base.BaseContract
import com.btlm.kotlingitproject.bean.discover.GameCenter


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:游戏中心Contract
 */

interface GameCenterContract {

    interface View : BaseContract.BaseView {

        fun showGameCenter(gameCenter: GameCenter)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getGameCenterData()
    }
}
