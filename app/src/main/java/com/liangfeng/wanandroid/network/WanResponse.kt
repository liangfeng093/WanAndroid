package com.liangfeng.wanandroid.network

/**
 * @Description:
 * @Author:zhifeng.mo
 * @Date:2019/12/13
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)