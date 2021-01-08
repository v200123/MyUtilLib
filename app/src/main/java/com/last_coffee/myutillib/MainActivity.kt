package com.last_coffee.myutillib

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kongzue.dialogx.dialogs.FullScreenDialog
import com.kongzue.dialogx.interfaces.OnBindView
import com.last_coffee.liubaselib.httpUtils.ErrorState
import com.last_coffee.myutillib.bean.UserInfoBean
import com.last_coffee.myutillib.databinding.ActivityMainBinding
import com.lc.mybaselibrary.start
import com.tencent.mmkv.MMKV


class MainActivity : MyBaseActivity<MainViewModel,ActivityMainBinding>() {
    private val mMyMViewModel by viewModels<MainViewModel>()
    private val mmkv = MMKV.defaultMMKV()
    private lateinit var mUserData:UserInfoBean
    private val mAdapter by lazy { Adapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun startObserver() {
        mViewModel.mStateLiveData.observe(this){
                if(it is ErrorState)
                {
                    mAdapter.addData("${it.message}，错误代码：${it.errorCode}")
                }
        }
        mViewModel.mUserInfoData.observe(this){
            mUserData = it
            mAdapter.addData("你好：${it.mUsername}")
        }
        mViewModel.mCheckInStatueData.observe(this){
            mAdapter.addData("能否签到：${if(!it.mCanCheckIn)"不能" else "能"}，连续签到天数:${it.mContinuousCheckInDays}")

        }
        mViewModel.mCheckInData.observe(this){
            mAdapter.addData(it)
        }
        mMyMViewModel.mOtherMessage.observe(this){
            mAdapter.addData(it)
        }
    }

    override fun initView() {
        mDataBinding.btnLogin.setOnClickListener {

            start<LoginActivity> {  }
        }

        mDataBinding.text01.setOnClickListener{
            mAdapter.data.clear()
            mAdapter.notifyDataSetChanged()
            mViewModel.getCheckStatue()
        }
        mDataBinding.btnClear.setOnClickListener {
            mmkv!!.clearAll()
            showInputMessage()
        }

        mDataBinding.rvShowMsg.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        mDataBinding.cbMainAutoCheck.isChecked = mmkv!!.getBoolean(ISAUTOCHECKIN,false)
        mDataBinding.cbMainAutoCheck.setOnCheckedChangeListener { buttonView, isChecked -> mmkv.putBoolean(ISAUTOCHECKIN,isChecked) }

    }

    override fun initData() {
        mViewModel = mMyMViewModel
        if(!mmkv!!.contains("Token"))
        {
//            showInputMessage()
        }
        if(mDataBinding.cbMainAutoCheck.isChecked)
            mViewModel.getCheckStatue()

    }

    override fun initNeedRefreshData() {
    }

    override fun restoreData() {

    }
    private fun showInputMessage(){
        FullScreenDialog.show(object : OnBindView<FullScreenDialog>(R.layout.dialog_input_msg){
            override fun onBind(dialog: FullScreenDialog?, v: View?) {
                val token = v!!.findViewById<EditText>(R.id.EditText)
                val sessionId = v.findViewById<EditText>(R.id.EditText2)
                val btnEnsure = v.findViewById<Button>(R.id.btn_ensure)

                btnEnsure.setOnClickListener {
                    if(token.text.toString().isEmpty() || sessionId.text.toString().isEmpty())
                    {
                        Toast.makeText(this@MainActivity,"请输入sessionID和Token",Toast.LENGTH_SHORT).show()
                    }else{
                        mmkv!!.putString("XSRF-TOKEN",token.text.toString())
                        mmkv!!.putString("session_id",sessionId.text.toString())
                        dialog?.dismiss()
                    }
                }
            }

        })
    }


    inner class Adapter : BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_main_text){
        override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.tv_item_msg,item)
        }

    }

}