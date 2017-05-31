package com.dyrosoft.kvbparser.utils

internal object StringUtils {

    fun advancedTrim(toTrim: String?) = toTrim?.replace(160.toChar().toString(), " ")?.trim { it <= ' ' }
}
