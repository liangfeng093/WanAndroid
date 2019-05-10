package com.liangfeng.wanandroid.bean

/**
 * Created by mzf on 2018/9/22.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
data class CommonWebsiteRespBody(var data: List<Data>?,
                                 var errorCode: Int,
                                 var errorMsg: String) {
    data class Data(var icon: String,
                    var id: Int,
                    var link: String,
                    var name: String,
                    var order: Int,
                    var visible: Int)
}