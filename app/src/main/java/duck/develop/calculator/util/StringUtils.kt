package duck.develop.calculator.util

/**
 * Created by Hwang on 2018-10-18.
 *
 * Description :
 */
class StringUtils {
    companion object {
        fun toAlias(data: String): String {
            var result = ""
            var isConvert = false

            for (ch in data.toCharArray()) {
                if (isConvert) {
                    isConvert = false
                    if (ch in 'a'..'z') {
                        result += (ch.toInt() - ('a' - 'A')).toChar()
                    }
                } else {
                    when (ch) {
                        '_' -> isConvert = true
                        in 'A'..'Z' -> result += "_" + (ch.toInt() + ('a' - 'A')).toChar()
                        else -> result += ch
                    }
                }
            }
            return result
        }
        fun getAliasWithUnderBar(data: String): String {
            var result = ""
            for (i in data.indices) {
                val ch = data[i]
                result += if (ch.toInt() in 65..90 && i == 0) {
                    (ch.toInt() + 32).toChar().toString()
                } else if (ch.toInt() in 65..90) {
                    "_" + (ch.toInt() + 32).toChar().toString()
                } else {
                    ch.toString()
                }
            }
            return result
        }
    }
}