package dev.draftine.utils.extension

fun <T> Iterable<T>.indexOrNull(element: T): Int? {
    return when (val index = indexOf(element)) {
        in 0..Int.MAX_VALUE -> index
        else -> null
    }
}