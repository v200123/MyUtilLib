package com.lc.liuchanglib.logger

import com.lc.liuchanglib.logger.loggerInterface.SuperFormatStrategy

open class SuperBaseLogAdapter(var mFormatStrategy: SuperFormatStrategy =  SuperPrettyFormatStrategy.Builder().build()) {


}