package com.last_coffee.myutillib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lc.liuchanglib.logger.SuperLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SuperLogger().d("hahahahah")
        GlobalScope.launch(Dispatchers.IO) {
            SuperLogger().d("当前我是在IO线程了")
            //do what you want
        }
    }
}