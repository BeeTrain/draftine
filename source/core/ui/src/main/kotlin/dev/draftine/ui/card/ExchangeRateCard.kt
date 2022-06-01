package dev.draftine.ui.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.isVisible
import dev.draftine.ui.R
import dev.draftine.ui.container.ConstraintContainer
import dev.draftine.ui.extension.bind
import dev.draftine.ui.extension.setClickableExt
import dev.draftine.ui.extension.setOnClickWithDelayListener
import dev.draftine.ui.image.Image
import dev.draftine.ui.image.ImageView
import dev.draftine.ui.text.TextView

class ExchangeRateCard
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val exchangeRateCardCard by bind<CardView>(R.id.exchange_rate_card)
    private val exchangeRateCardRoot by bind<ConstraintContainer>(R.id.exchange_rate_card_root)
    private val exchangeRateCardTitle by bind<TextView>(R.id.exchange_rate_card_title)
    private val exchangeRateCardDate by bind<TextView>(R.id.exchange_rate_card_date)
    private val exchangeRateCardValue by bind<TextView>(R.id.exchange_rate_card_value)
    private val exchangeRateCardLink by bind<ImageView>(R.id.exchange_rate_card_link)
    private val exchangeRateCardIcon by bind<ImageView>(R.id.exchange_rate_card_icon)

    var currencyIcon: Image? = null
        set(value) {
            field = value
            exchangeRateCardIcon.loadImage(value)
            exchangeRateCardIcon.isVisible = value != null
        }

    var title: String? = null
        set(value) {
            field = value
            val isVisible = !value.isNullOrEmpty()
            exchangeRateCardTitle.text = value.orEmpty()
            exchangeRateCardTitle.isVisible = isVisible
        }

    var date: String? = null
        set(value) {
            field = value
            val isVisible = !value.isNullOrEmpty()
            exchangeRateCardDate.text = value.orEmpty()
            exchangeRateCardDate.isVisible = isVisible
        }

    var value: String? = null
        set(value) {
            field = value
            exchangeRateCardValue.text = value.orEmpty()
            exchangeRateCardValue.isVisible = !value.isNullOrEmpty()
        }

    var linkIconClickListener: ((View?) -> Unit)? = null
        set(value) {
            field = value

            val isClickable = value != null
            exchangeRateCardLink.isVisible = isClickable
            exchangeRateCardLink.setClickableExt(isClickable)
            exchangeRateCardLink.setOnClickWithDelayListener { field?.invoke(it) }
        }

    init {
        inflate(context, R.layout.exchange_rate_card, this)
    }
}