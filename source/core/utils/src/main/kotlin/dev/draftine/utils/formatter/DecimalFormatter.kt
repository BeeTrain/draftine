package dev.draftine.utils.formatter

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

private const val FRACTION_MIN = 0
private const val FRACTION_MAX = 2
private const val GROUP_SIZE = 3
private const val SEPARATOR_DECIMAL = '.'
private const val SEPARATOR_GROUP = ' '
private const val ZERO = '0'
private const val TWO_CHARACTERS = 2

class DecimalFormatter : DecimalFormat() {

    init {
        maximumFractionDigits = FRACTION_MAX
        minimumFractionDigits = FRACTION_MIN
        isGroupingUsed = true
        groupingSize = GROUP_SIZE
        decimalFormatSymbols = DecimalFormatSymbols().apply {
            decimalSeparator = SEPARATOR_DECIMAL
            groupingSeparator = SEPARATOR_GROUP
        }
        roundingMode = RoundingMode.DOWN
    }

    private fun endsWithSeparator(text: String): Boolean {
        return with(text) {
            if (isEmpty()) {
                false
            } else {
                SEPARATOR_DECIMAL == toCharArray()[length - 1]
            }
        }
    }

    private fun endsWithOneTrailingZero(text: String): Boolean {
        return with(text) {
            if (length > TWO_CHARACTERS) {
                val endsWithZero = ZERO == toCharArray()[length - 1]
                val endsWithOneTrailingZero = SEPARATOR_DECIMAL == toCharArray()[length - 2]
                endsWithZero && endsWithOneTrailingZero
            } else {
                false
            }
        }
    }

    private fun endsWithTrailingZero(text: String, formattedText: String): Boolean {
        with(text) {
            if (contains(SEPARATOR_DECIMAL) && formattedText.contains(SEPARATOR_DECIMAL)) {
                val secondText = split(SEPARATOR_DECIMAL)[1]
                if (secondText.length > 1 && secondText.toCharArray()[1] == ZERO) {
                    return true
                }
            }
        }
        return false
    }
}