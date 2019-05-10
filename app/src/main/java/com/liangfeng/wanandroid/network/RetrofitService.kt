package com.liangfeng.wanandroid.features.login

import com.liangfeng.wanandroid.base.DataContainer
import com.liangfeng.wanandroid.bean.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by mzf on 2018/7/13.
 * Email:liangfeng093@gmail.com
 * Desc:管理所有的接口请求
 */
interface RetrofitService {

    companion object {
        val BaseURL: String = "https://www.wanandroid.com"//访问接口的ip和端口
        val testBaseURL: String = "http://v.juhe.cn/"//聚合数据测试接口
        val videoUrl = BaseURL + "XXX/"//音频文件的加载路径
        val imgUrl = BaseURL + "img/"//图片的加载路径
    }

    /**
     * 登录
     */
    @POST("/user/login")
    @FormUrlEncoded//以表单的形式发送参数
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<LoginResponseBody>

    /********************************** 首页相关 **********************************/
    /**
     * 首页文章列表
     */
    @GET("/article/list/{pageNumber}/json")
    fun homeArticleList(@Path("pageNumber") pageNumber: Int): Observable<DataContainer<HomeArticleListRespBody>>


    /**
     * 首页banner
     */
    @GET("/banner/json")
    fun homeBanner(): Observable<HomeBannerRespBody>


    /**
     * 常用网站
     */
    @GET("/friend/json")
    fun commonWebsite(): Observable<CommonWebsiteRespBody>

    /**
     * 搜索热词
     */
    @GET("/hotkey/json")
    fun search4HotWords(): Observable<Search4HotWordRespBody>


    /********************************** 体系 **********************************/


    /**
     * 体系数据
     */
    @GET("/tree/json")
    fun systemData(): Observable<SystemDataRespBody>

    /**
     * 知识体系下的文章
     */
    @GET("/article/list/{pageNumber}/json?cid=60")
    fun articlesUnderTheKnowledgeSystem(@Path("pageNumber") pageNumber: Int,
                                        @Query("cid") cid: String): Observable<ArticlesUnderTheKnowledgeSystemRespBody>

    /**
     * 导航
     */
    @GET("/navi/json")
    fun navigation(): Observable<NavigationRespBody>


    /**
     * 项目分类
     */
    @GET("/project/tree/json")
    fun typesOfSection(): Observable<TypesOfSectionRespBody>

    /**
     * 项目列表数据
     */
    @GET("/project/list/{pageNumber}/json")
    fun projectListData(@Path("pageNumber") pageNumber: Int,
                        @Query("cid") cid: String): Observable<ProjectListDataRespBody>

    /********************************** 收藏 **********************************/

    /**
     * 搜索
     */
    @POST("/article/query/{pageNumber}/json")
    @FormUrlEncoded//以表单的形式发送参数
    fun search(@Path("pageNumber") pageNumber: Int,
               @Field("k") keyWord: String)
}
