package com.liangfeng.wanandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.name
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }


    //将back事件委托出去。若栈中有两个以上Fragment，点击back键就会返回到上一个Fragment。
    override fun onSupportNavigateUp(): Boolean {
//        var fragment = supportFragmentManager?.findFragmentById(R.id.fragment)
        return NavHostFragment.findNavController(fragment).navigateUp()
    }

}
