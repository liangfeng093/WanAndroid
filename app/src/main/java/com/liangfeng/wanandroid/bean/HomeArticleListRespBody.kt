package com.liangfeng.wanandroid.bean

/**
 * Created by mzf on 2018/9/28.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
data class HomeArticleListRespBody(var curPage: Int,
                                   var offset: Int,
                                   var over: Boolean,
                                   var pageCount: Int,
                                   var size: Int,
                                   var total: Int,
                                   var datas: MutableList<Article>) {
    data class Article(var apkLink: String,
                     var author: String,
                     var chapterId: Int,
                     var chapterName: String,
                     var collect: Boolean,
                     var courseId: Int,
                     var desc: String,
                     var envelopePic: String,
                     var fresh: Boolean,
                     var id: Int,
                     var link: String,
                     var niceDate: String,
                     var origin: String,
                     var projectLink: String,
                     var publishTime: Long,
                     var superChapterId: Int,
                     var superChapterName: String,
                     var title: String,
                     var `type`: Int,
                     var userId: Int,
                     var visible: Int,
                     var zan: Int,
                     var tags: List<Tags>) {
        data class Tags(var name: String,
                        var url: String)
    }

    override fun toString(): String {
        return "HomeArticleListRespBody(curPage=$curPage, offset=$offset, over=$over, pageCount=$pageCount, size=$size, total=$total, datas=$datas)"
    }


}