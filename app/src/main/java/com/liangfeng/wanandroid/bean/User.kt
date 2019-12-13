package com.liangfeng.wanandroid.bean

/**
 * @Description:
 * @Author:zhifeng.mo
 * @Date:2019/12/13
 */
data class User(var email: String,
                var icon: String,
                var id: Int,
                var password: String,
                var token: String,
                var `type`: Int,
                var username: String,
                var collectIds: MutableList<String>)