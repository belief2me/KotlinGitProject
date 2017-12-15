package com.btlm.kotlingitproject.bean.app

/**
 * Created by Administrator on 2017/12/15.
 */
data class Splash(var code : Int,var message : String,
                  var tt1 : Int,var ver : String,var data : List<Data>) {
    data class Data(var id: Int,
                    var `type`: Int,
                    var animate: Int,
                    var duration: Int,
                    var start_time: Int,
                    var end_time: Int,
                    var thumb: String,
                    var hash: String,
                    var times: Int,
                    var skip: Int,
                    var uri: String)
}