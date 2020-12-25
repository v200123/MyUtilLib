package com.last_coffee.myutillib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.last_coffee.liubaselib.BaseActivity
import com.last_coffee.myutillib.databinding.ActivityMainBinding
import com.lc.liuchanglib.logger.SuperLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Logger


class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>() {
    private val mMyMViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun startObserver() {
        mViewModel.mCheckInStatueData.observe(this){
            Toast.makeText(this,"当前的请求成功了,能否签到：${it.mCanCheckIn}，签到天数:${it.mContinuousCheckInDays}",Toast.LENGTH_SHORT).show()
        }
    }

    override fun initView() {
        mDataBinding.text01.text = "0055156165156"
        mDataBinding.text01.setOnClickListener{

            mViewModel.getCheckStatue()

        }
    }

    override fun initData() {
        mViewModel = mMyMViewModel

    }

    override fun initNeedRefreshData() {

    }

    override fun restoreData() {

    }
}