package dev.draftine.ui.extension

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Base64
import android.util.Patterns
import android.view.View

fun String.linkify(
    linkColor: Int = Color.BLUE,
    linkClickAction: ((link: String) -> Unit)? = null
): SpannableStringBuilder {
    val builder = SpannableStringBuilder(this)
    val matcher = Patterns.WEB_URL.matcher(this)
    while (matcher.find()) {
        val start = matcher.start()
        val end = matcher.end()
        builder.setSpan(ForegroundColorSpan(linkColor), start, end, 0)
        val onClick = object : ClickableSpan() {
            override fun onClick(view: View) {
                linkClickAction?.invoke(matcher.group())
            }
        }
        builder.setSpan(onClick, start, end, 0)
    }

    return builder
}

fun String.decode(): String {
    return Base64.decode(this, Base64.DEFAULT).toString(Charsets.UTF_8)
}

fun String.encode(): String {
    return Base64.encodeToString(this.toByteArray(Charsets.UTF_8), Base64.DEFAULT)
}