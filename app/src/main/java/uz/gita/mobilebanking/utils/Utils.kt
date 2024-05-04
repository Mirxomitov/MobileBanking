package uz.gita.mobilebanking.utils

import timber.log.Timber

fun logger(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}


fun <T> List<T>.new(): MutableList<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}