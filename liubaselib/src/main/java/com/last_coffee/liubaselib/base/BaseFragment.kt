package com.last_coffee.liubaselib.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType
import java.util.logging.Logger

/**
 *
 * @PackAge：com.last_coffee.liubaselib.base
 * @创建人：Lc
 * @Desc：
 * @Time: 一月
 *
 **/
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment(),IBaseActivityView {
   lateinit var  mContext : Context
    lateinit var mViewModel: VM
    lateinit var mViewBinding: VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initData()
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz1 = type.actualTypeArguments[0] as Class<VM>
        mViewModel = ViewModelProvider(this).get(clazz1)

        val clazz2 = type.actualTypeArguments[1] as Class<VB>
        val method = clazz2.getMethod("inflate", LayoutInflater::class.java)
        mViewBinding = method.invoke(null, layoutInflater) as VB
    }

    override fun onStart() {
        super.onStart()
        startObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return mViewBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initOnClick()
    }

    abstract fun startObserver()
    abstract fun getLoadingDialogText(): String
    abstract fun showLoadingDialog(message: String)
    abstract fun hideLoadingDialog()
}