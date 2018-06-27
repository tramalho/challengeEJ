package br.com.tramalho.enjoeitest.presentation

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.text.style.StrikethroughSpan
import android.text.SpannableString


class StrikethroughTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    override fun setText(text: CharSequence?, type: BufferType?) {

        val spannable = SpannableString(text)
        spannable.setSpan(StrikethroughSpan(), 0, spannable.length, 0)

        super.setText(spannable, type)
    }
}