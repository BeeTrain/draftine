package dev.draftine.utils.model

import androidx.annotation.DrawableRes
import dev.draftine.utils.R
import java.io.Serializable

@Suppress("ConstructorParameterNaming")
enum class Currency(
    private val _code: Int,
    private val _shortName: String,
    private val _symbol: String = _shortName,
    @DrawableRes val flagRes: Int? = null
) : Serializable {
    RUR(810, "RUR", String(Character.toChars(0x20BD)), R.drawable.flag_russia),
    RUB(643, "RUB", String(Character.toChars(0x20BD)), R.drawable.flag_russia),
    USD(840, "USD", String(Character.toChars(0x0024)), R.drawable.flag_united_states),
    EUR(978, "EUR", String(Character.toChars(0x20AC))),
    GBP(826, "GBP", String(Character.toChars(0x00A3))),
    CHF(756, "CHF", String(Character.toChars(0x20A3))),
    JPY(392, "JPY", String(Character.toChars(0x00A5))),
    UAH(980, "UAH", String(Character.toChars(0x20B4))),
    KZT(398, "KZT", String(Character.toChars(0x20B8))),
    CNY(156, "CNY", String(Character.toChars(0x5143))),
    UNKNOWN(-1, "");

    var code = _code
        private set

    var shortName = _shortName
        private set

    var symbol = _symbol
        private set

    companion object {

        fun fromCode(code: Int): Currency {
            for (currency in values()) {
                if (currency.code == code) {
                    return currency
                }
            }

            return UNKNOWN.apply { this.code = code }
        }

        fun fromShortName(shortName: String): Currency {
            for (currency in values()) {
                if (currency.shortName.equals(shortName, ignoreCase = true)) {
                    return currency
                }
            }

            return UNKNOWN.apply { this.shortName = shortName }
        }
    }
}