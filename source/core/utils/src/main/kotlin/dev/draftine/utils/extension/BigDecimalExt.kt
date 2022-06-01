package dev.draftine.utils.extension

import dev.draftine.utils.COMMA
import dev.draftine.utils.DOT
import dev.draftine.utils.EMPTY
import dev.draftine.utils.formatter.DecimalFormatter
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

private const val FRACTION_RULE_NORMALIZER = 1
private const val PERCENT_FORMAT = "%.2f %%"
private const val PERCENT_FORMAT_WITHOUT_TRAILING_ZEROES = "%s%%"
private val ONE_HUNDRED = BigDecimal(100)
private const val LENGTH_FRACTION = 2

private val amountFormatter by lazy {
    DecimalFormatter().apply {
        negativePrefix = "\u2212"
        minimumFractionDigits = LENGTH_FRACTION
    }
}

fun String.parseBigDecimal(): BigDecimal? {
    return try {
        var value = this.normalizeAmountValue(EMPTY)
        value = value.removeNotDigits()!!
        return BigDecimal(value)
    } catch (e: Exception) {
        null
    }
}

fun BigDecimal.percentageValue(to: BigDecimal): BigDecimal {
    return this.multiply(ONE_HUNDRED).divide(to, MathContext.DECIMAL128)
}

fun BigDecimal.isSame(value: BigDecimal): Boolean {
    return this.compareTo(value) == 0
}

fun BigDecimal.isInteger(): Boolean {
    return this.signum() == 0 || this.scale() <= 0 || this.stripTrailingZeros().scale() <= 0
}

fun BigDecimal.isZero(): Boolean {
    return this.isSame(BigDecimal.ZERO)
}

fun BigDecimal.isMoreThanZero(): Boolean {
    return this.compareTo(BigDecimal.ZERO) == 1
}

fun BigDecimal.isMoreThan(value: BigDecimal): Boolean {
    return this.compareTo(value) == 1
}

fun BigDecimal.isMoreOrSameThan(value: BigDecimal): Boolean {
    return isMoreThan(value) || isSame(value)
}

fun BigDecimal.isLessThanZero(): Boolean {
    return this.compareTo(BigDecimal.ZERO) == -1
}

fun BigDecimal.isLessThan(value: BigDecimal): Boolean {
    return this.compareTo(value) == -1
}

fun BigDecimal.isLessOrSameThan(value: BigDecimal): Boolean {
    return isLessThan(value) || isSame(value)
}

fun BigDecimal.negateSafely(): BigDecimal {
    return when (this.isMoreThanZero()) {
        true -> this.negate()
        false -> this
    }
}

fun BigDecimal.normalizeAndGetIntValue(): Int {
    val sharesCountInt = this.toInt()
    return if (this > BigDecimal(FRACTION_RULE_NORMALIZER)) {
        sharesCountInt - FRACTION_RULE_NORMALIZER
    } else {
        sharesCountInt
    }
}

fun BigDecimal.toPercentageString() = PERCENT_FORMAT.format(this)

fun BigDecimal.formatToPercentageStringWithoutTrailingZeroes(): String {
    return PERCENT_FORMAT_WITHOUT_TRAILING_ZEROES.format(
        this.setScale(2, RoundingMode.HALF_UP)
            .stripTrailingZeros()
            .toPlainString()
            .replace(DOT, COMMA)
    )
}

fun BigDecimal.format(
    currencySymbol: String,
    lengthFraction: Int = LENGTH_FRACTION,
    needToCutOffFractionIfInteger: Boolean = true
): String {
    val formatValue = amountFormatter.format(this).replace(DOT, COMMA)
    val builder = StringBuilder(formatValue)
    if (needToCutOffFractionIfInteger) builder.cutOffFractionIfInteger(this, lengthFraction)
    builder.append(" $currencySymbol")
    return builder.toString()
}

private fun StringBuilder.cutOffFractionIfInteger(value: BigDecimal, lengthFraction: Int) {
    if (value.isInteger()) {
        val separatorIndex = indexOf(COMMA)
        replace(separatorIndex, separatorIndex + lengthFraction + 1, EMPTY)
    }
}