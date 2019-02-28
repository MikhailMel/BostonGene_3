package ru.scratty.translator

import com.beust.klaxon.Klaxon
import ru.scratty.http.HttpPost
import ru.scratty.http.HttpUrl

class Translator(apiKey: String) {

    companion object {
        private const val SCHEME = "https"
        private const val HOST = "translate.yandex.net"
        private const val PATH = "/api/v1.5/tr.json/translate"

        private const val KEY_PARAMETER = "key"
        private const val TEXT_PARAMETER = "text"
        private const val LANG_PARAMETER = "lang"
    }

    private val url = HttpUrl(SCHEME, HOST, path = PATH)
    private val postParameters = hashMapOf(
            KEY_PARAMETER to apiKey
    )

    fun translate(text: String, from: Language, to: Language): String {
        postParameters[TEXT_PARAMETER] = text
        postParameters[LANG_PARAMETER] = "${from.shortName}-${to.shortName}"

        val response = HttpPost(url, postParameters).execute()

        return if (response.code == 200) {
            Klaxon().parse<TranslationResult>(response.data)!!.text.joinToString { s -> s }
        } else {
            Klaxon().parse<TranslationError>(response.data)!!.message
        }
    }
}