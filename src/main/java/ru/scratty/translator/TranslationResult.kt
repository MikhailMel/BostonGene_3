package ru.scratty.translator

data class TranslationResult(
        val code: Int,
        val lang: String,
        val text: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TranslationResult

        if (code != other.code) return false
        if (lang != other.lang) return false
        if (!text.contentEquals(other.text)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + lang.hashCode()
        result = 31 * result + text.contentHashCode()
        return result
    }
}