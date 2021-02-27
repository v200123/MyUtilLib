package com.last_coffee.myutillib

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *
 * @PackAge：com.last_coffee.myutillib
 * @创建人：Lc
 * @Desc：
 * @Time: 二月
 *
 **/
class Adapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_main_text) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_item_msg, item)
    }

}