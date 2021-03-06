package com.last_coffee.myutillib

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import androidx.work.impl.constraints.controllers.BatteryNotLowController
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kongzue.dialogx.dialogs.FullScreenDialog
import com.kongzue.dialogx.interfaces.OnBindView
import com.last_coffee.liubaselib.httpUtils.ErrorState
import com.last_coffee.myutillib.bean.UserInfoBean
import com.last_coffee.myutillib.databinding.ActivityMainBinding
import com.lc.liuchanglib.ext.*
import com.lc.liuchanglib.init.LogInit
import com.lc.mybaselibrary.ext.getResDrawable
import com.lc.mybaselibrary.start
import com.tencent.mmkv.MMKV
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit


class MainActivity : MyBaseActivity<MainViewModel, ActivityMainBinding>() {
    private val mMyMViewModel by viewModels<MainViewModel>()
    private val mmkv = MMKV.defaultMMKV()
    private lateinit var mUserData: UserInfoBean
    private val mAdapter by inject<Adapter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun startObserver() {
        mViewModel.mStateLiveData.observe(this) {
            if (it is ErrorState) {
                hideLoadingDialog()
                addData("${it.message}，错误代码：${it.errorCode}")
            }
        }
        mViewModel.mUserInfoData.observe(this) {
            mUserData = it
            addData("你好：${it.mUsername}")
        }
        mViewModel.mCheckInStatueData.observe(this) {
            addData("能否签到：${if (!it.mCanCheckIn) "不能" else "能"}，连续签到天数:${it.mContinuousCheckInDays}")
        }
        mViewModel.mCheckInData.observe(this) {
            addData(it)
        }
        mMyMViewModel.mOtherMessage.observe(this) {
            addData(it)
        }
    }

    override fun initView() {
        initChannerl()
        mViewBinding.btnLogin.setOnClickListener {
            start<LoginActivity> { }
        }

        mViewBinding.text01.setOnClickListener {
            mAdapter.data.clear()
            mAdapter.notifyDataSetChanged()
            mViewModel.getCheckStatue()
        }

        mViewBinding.drop.apply {
            mRightDrawable = mContext.getResDrawable(R.drawable.process)!!.apply {setBounds(0,0,100,100)  }
            setContextText("这是一次尝试")
        }
        mViewBinding.drop.setOnClickListener {
            mViewBinding.drop.filterShow(arrayListOf("aaa","bbb","ccc"),{it},null){ i: Int, s: String ->
                    Toast.makeText(mContext,"当前选择的是$s",Toast.LENGTH_SHORT).show()
            }
        }


        mViewBinding.btnClear.setOnClickListener {
            mmkv!!.clearAll()
            showInputMessage()
        }

        mViewBinding.rvShowMsg.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        mViewBinding.cbMainAutoCheck.isChecked = mmkv!!.getBoolean(ISAUTOCHECKIN, false)
        mViewBinding.cbMainAutoCheck.setOnCheckedChangeListener { buttonView, isChecked -> mmkv.putBoolean(ISAUTOCHECKIN, isChecked) }
        mViewBinding.fold.setOnClickListener {
            mViewBinding.fold.openAnim()
        }
    }

    override fun initData() {
        mViewModel = mMyMViewModel
        mViewBinding.fold.setContent("sdfsdfsdfsdfsdfxcvxcvxcvsdfsdf\nsdfsdfsdfsdfwerxfdvdsfgdgf\ndfsewertrtcvcvbcvbcvbwq")

//        if(mViewBinding.cbMainAutoCheck.isChecked)
//            mViewModel.getCheckStatue()

    }

    override fun initNeedRefreshData() {
    }

    override fun restoreData() {

    }

    override fun initOnClick() {

    }

    private fun showInputMessage() {
        FullScreenDialog.show(object : OnBindView<FullScreenDialog>(R.layout.dialog_input_msg) {
            override fun onBind(dialog: FullScreenDialog?, v: View?) {
                val token = v!!.findViewById<EditText>(R.id.EditText)
                val sessionId = v.findViewById<EditText>(R.id.EditText2)
                val btnEnsure = v.findViewById<Button>(R.id.btn_ensure)

                btnEnsure.setOnClickListener {
                    if (token.text.toString().isEmpty() || sessionId.text.toString().isEmpty()) {
                        Toast.makeText(this@MainActivity, "请输入sessionID和Token", Toast.LENGTH_SHORT).show()
                    } else {
                        mmkv!!.putString("XSRF-TOKEN", token.text.toString())
                        mmkv!!.putString("session_id", sessionId.text.toString())
                        dialog?.dismiss()
                    }
                }
            }

        })
    }

    private fun addData(Message: String) {
        mAdapter.addData(Message)
        mViewBinding.rvShowMsg.smoothScrollToPosition(mAdapter.data.size)
    }


    @SuppressLint("NewApi")
    private fun initChannerl() {
//        val notificationManage: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = Notification.Builder(this, "sign01")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
            .setContentTitle("一条新通知")
            .setContentText("这是一条测试消息")
            .setAutoCancel(true)
            .build()
        notificationCreate(mContext, listOf(NoticaionInfo("sign01", "签到通知aaa",
            NotificationManager.IMPORTANCE_HIGH))).showNotification(1,notification)


    }


  public inner class signWorker(context: Context, workerParams: WorkerParameters)
        : Worker(context, workerParams) {
      override fun doWork(): Result {
                      LogInit.showDebug("当前的后台任务得到了执行了")
            val notificationManage: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification.Builder(mContext, "sign")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentTitle("一条新通知")
                        .setContentText("这是一条测试消息")
                        .setAutoCancel(true)
                        .build()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
          notificationManage.notify(1, notification)

            mMyMViewModel.getCheckStatue()
            return Result.success()
      }

//        override suspend fun doWork(): Result {
//            LogInit.showDebug("当前的后台任务得到了执行了")
//            val notificationManage: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            val notification = Notification.Builder(mContext, "sign")
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                    .setContentTitle("一条新通知")
//                    .setContentText("这是一条测试消息")
//                    .setAutoCancel(true)
//                    .build()
//            notificationManage.notify(1, notification)
//
//            mMyMViewModel.getCheckStatue()
//            return Result.success()
//        }
    }

}