package com.liangfeng.wanandroid.bean

/**
 * Created by mzf on 2018/9/22.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
data class HomeBannerRespBody(var data: List<Data>?,
                              var errorCode: Int,
                              var errorMsg: String) {
    data class Data(var desc: String,
                    var id: Int,
                    var imagePath: String,
                    var isVisible: Int,
                    var order: Int,
                    var title: String,
                    var `type`: Int,
                    var url: String)
}