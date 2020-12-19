package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy

class SuperDiskFormatStrategy : SuperFormatStrategy(){




    override fun log(priority: Int, tag: String?, message: String?) {
            if(tag!=null)
            {
                composeTag(tag)
            }
            if(!message.isNullOrEmpty())
            {

            }
    }

    private fun composeTag(tag: String?){
        if(!tag.isNullOrEmpty())
        {
            mTag+="-$tag"
        }
    }

    private fun logTopBorder(){
//            mLogStrategy!!.log()
    }


}