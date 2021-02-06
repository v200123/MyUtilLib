package com.lc.liuchanglib.ext

/**
 *
 * @PackAge：com.lc.liuchanglib.ext
 * @创建人：Lc
 * @Desc：关于一些List的常用工具：如，若当前为空，越界等等
 * @Time: 二月
 *
 **/

fun<T> Collection<T>.emptyDoSomething(notEmpty:()->Unit,empty:()->Unit){
    if(this.isEmpty()) notEmpty() else empty()
}

fun<T> Collection<T>?.nullOrEmptyDoSomething(doNull:()->Unit,notEmpty:()->Unit,empty:()->Unit)
{
    if(this == null) doNull() else emptyDoSomething(notEmpty,empty)
}

