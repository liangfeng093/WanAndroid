package com.liangfeng.wanandroid.features.login

/**
 * Created by mzf on 2018/9/21.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginRequestBody {
    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    var username = ""
    var password = ""
    override fun toString(): String {
        return "LoginRequestBody(username='$username', password='$password')"
    }

}