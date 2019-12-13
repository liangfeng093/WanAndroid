package com.liangfeng.wanandroid.base

/**
 * Created by mzf on 2018/12/13.
 * Email:liangfeng093@gmail.com
 * Desc:json最外层字段
 */
data class DataContainer<T>(val errorCode: String, val errorMsg: String, val data: T)
/*
open class DataContainer<T> {
    var errorCode: String = ""
    var errorMsg: String = ""
    var data: T? = null
    override fun toString(): String {
        return "DataContainer(errorCode='$errorCode', errorMsg='$errorMsg', data=$data)"
    }


}*/
