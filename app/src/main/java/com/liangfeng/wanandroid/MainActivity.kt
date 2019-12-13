package com.liangfeng.wanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.textView

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        MainActivityUI().setContentView(this)
    }


    //将back事件委托出去。若栈中有两个以上Fragment，点击back键就会返回到上一个Fragment。
    override fun onSupportNavigateUp(): Boolean {
//        var fragment = supportFragmentManager?.findFragmentById(R.id.fragment)
        return NavHostFragment.findNavController(fragment).navigateUp()
    }

}


class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = ui.apply {
        constraintLayout {
            textView {
                text = "HOME页面"
            }.lparams()
        }
    }.view

}