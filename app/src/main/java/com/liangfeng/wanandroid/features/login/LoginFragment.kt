package com.liangfeng.wanandroid.features.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.liangfeng.wanandroid.R
import com.liangfeng.wanandroid.network.Observers
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.find
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.HORIZONTAL as LayoutParamsHORIZONTAL

/**
 * Created by mzf on 2018/9/20.
 * Email:liangfeng093@gmail.com
 * Desc:
 */
class LoginFragment : Fragment() {

    companion object {
        val USER_NAME = "UserName"
        val PASS_WORD = "PassWord"
    }

    val TAG = this.javaClass.name

    var isSave = false

    //页面控件id
    private val et_user_name = 1
    private val et_pwd = 2
    private val cb_save = 3
    private val btn_login = 4


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return UI {
            constraintLayout {
                val etUserName = editText {
                    id = et_user_name
                    hint = getString(R.string.login_user_name_hint)
                }.lparams(height = dip(50), width = dip(200)) {
                    topMargin = dip(160)
                    topToTop = PARENT_ID
                    leftToLeft = PARENT_ID //左侧的相对定位，左侧依赖于父容器的左侧
                    rightToRight = PARENT_ID //右侧的相对定位，右侧依赖于父容器的右侧
                }

                var etPassWord = editText {
                    id = et_pwd
                    hint = getString(R.string.login_pass_word_hint)
                }.lparams(height = dip(50), width = dip(200)) {
                    topMargin = dip(20)
                    topToBottom = et_user_name //控件顶部依赖于其它控件的地步
                    leftToLeft = PARENT_ID //左侧的相对定位，左侧依赖于父容器的左侧
                    rightToRight = PARENT_ID //右侧的相对定位，右侧依赖于父容器的右侧
                }

                button(R.string.login_btn) {
                    id = btn_login
                    onClick {
                        val userName = etUserName.text.toString().trim()
                        val passWord = etPassWord.text.toString().trim()
                        if (userName.isEmpty()) {
                            ToastUtils.showLong(resources?.getString(R.string.login_tip_1))
                            return@onClick
                        }
                        if (passWord.isEmpty()) {
                            ToastUtils.showLong(resources?.getString(R.string.login_tip_2))
                            return@onClick
                        }
                        login(userName, passWord)

                    }
                }.lparams(height = wrapContent, width = dip(200)) {
                    leftToLeft = PARENT_ID //左侧的相对定位，左侧依赖于父容器的左侧
                    rightToRight = PARENT_ID //右侧的相对定位，右侧依赖于父容器的右侧
                    topToTop = PARENT_ID
                    bottomToBottom = PARENT_ID
                }

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    checkBox {
                        id = cb_save
                        onCheckedChange { buttonView, isChecked ->
                            LogUtils.e("isChecked:" + isChecked)
                            isSave = isChecked
                        }
                    }.lparams(height = wrapContent, width = wrapContent) {

                    }
                    textView(R.string.login_tip_3).lparams(height = wrapContent, width = wrapContent) {
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }.lparams(height = wrapContent, width = wrapContent) {
                    topToBottom = btn_login
                    leftToLeft = btn_login
                    topMargin = dip(20)
                }


            }
        }.view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val instance = SPUtils.getInstance()
        val strUserName = instance?.getString(USER_NAME, "")
        val strPwd = instance?.getString(PASS_WORD, "")
        if (strUserName!!.isNotEmpty()) {
            find<EditText>(et_user_name).setText(strUserName)
        }
        if (strPwd!!.isNotEmpty()) {
            find<EditText>(et_pwd).setText(strPwd)
            find<CheckBox>(cb_save).isChecked = true
        } else {
            find<CheckBox>(cb_save).isChecked = false
        }


    }

    /**
     * 登录
     */
    fun login(userName: String, passWord: String) {
        RemoteDateManger?.login(LoginRequestBody(userName, passWord)
                , object : Observers.LoginObserver() {
            override fun onNext(body: LoginResponseBody) {
                if (body?.errorCode == -1) {//登录异常
                    LogUtils.e(TAG, ">>>>>>>登录异常:" + body)
                    ToastUtils.showLong("登录异常")
                } else {//登录成功
                    LogUtils.e(TAG, ">>>>>>>登录成功:" + body)
                    ToastUtils.showLong("登录成功")
                    rememberUserNameAndPwd(isSave, userName, passWord)
//                    Navigation.findNavController(btn_login).navigate(R.id.action_loginFragment_to_homeFragment)
                }
            }
        })
    }

    /**
     * 记住账号密码
     */
    fun rememberUserNameAndPwd(isSave: Boolean, userName: String, passWord: String) {
        var instance = SPUtils.getInstance()
        if (isSave) {
            instance?.put(USER_NAME, userName)
            instance?.put(PASS_WORD, passWord)
        } else {
            instance?.remove(USER_NAME)
            instance?.remove(PASS_WORD)
        }
    }
}