package com.liangfeng.wanandroid.base

/**
 * Created by mzf on 2018/12/13.
 * Email:liangfeng093@gmail.com
 * Desc:json最外层字段
 */
open class DataContainer<T> {
    var errorCode: String = ""
    var errorMsg: String = ""
    var data: T? = null
    override fun toString(): String {
        return "DataContainer(errorCode='$errorCode', errorMsg='$errorMsg', data=$data)"
    }


}