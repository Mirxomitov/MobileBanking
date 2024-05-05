package uz.gita.mobilebanking.utils

import timber.log.Timber

fun logger(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}


fun <T> List<T>.new(): MutableList<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

fun String.hidePartOfNumber(): String {

    if (this.length == 9) {
        return "+998 ${this.substring(0, 2)} ••• •• ${this.substring(7, 9)}"
    }

    return this
}