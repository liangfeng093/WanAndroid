package com.greatwall.multimedia.network

import com.liangfeng.wanandroid.features.login.LoginResponseBody
import com.liangfeng.wanandroid.network.Banner
import com.liangfeng.wanandroid.network.WanResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @Description:
 * @Author:zhifeng.mo
 * @Date:2019/12/13
 */
interface RetrofitService {
    companion object {
        val BaseURL: String = "https://www.wanandroid.com"//访问接口的ip和端口
//        val testBaseURL: String = "http://v.juhe.cn/"//聚合数据测试接口
//        val videoUrl = BaseURL + "XXX/"//音频文件的加载路径
//        val imgUrl = BaseURL + "img/"//图片的加载路径
    }

    /**
     * 登录
     */
    @POST("/user/login")
    @FormUrlEncoded//以表单的形式发送参数
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponseBody

    /**
     * 首页banner
     */
    @GET("/banner/json")
    fun homeBanner(): WanResponse<List<Banner>>
}