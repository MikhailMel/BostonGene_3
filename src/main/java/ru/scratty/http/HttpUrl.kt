package ru.scratty.http

import java.net.URL

class HttpUrl(
        var scheme: String = "https",
        var host: String = "",
        var port: Int  = -1,
        var path: String = ""
) {

    fun build(): URL {
        val sb = StringBuilder().apply {
            append("$scheme://$host")
            if (port >= 0) {
                append(":$port")
            }

            if (path.isNotEmpty() && !path.startsWith('/')) {
                append("/")
            }
            append(path)
        }

        return URL(sb.toString())
    }
}