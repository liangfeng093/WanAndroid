package com.liangfeng.wanandroid.features.login

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:登录返回的对象
 */

data class LoginResponseBody(var data: Data?,
                             var errorCode: Int,//-1:登录异常，0:登录成功
                             var errorMsg: String) {
    data class Data(var email: String,
                    var icon: String,
                    var id: Int,
                    var password: String,
                    var token: String,
                    var `type`: Int,
                    var username: String,
                    var collectIds: MutableList<String>)
}
