package com.liangfeng.wanandroid.bean

/**
 * Created by mzf on 2018/9/22.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
data class TypesOfSectionRespBody(var errorCode: Int,
                                  var errorMsg: String,
                                  var data: List<Children>?) {
    data class Children(var courseId: Int,
                        var id: Int,
                        var name: String,
                        var order: Int,
                        var parentChapterId: Int,
                        var visible: Int,
                        var children: List<Children>)
}