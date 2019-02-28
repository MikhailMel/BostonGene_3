package ru.scratty.http

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection

class HttpPost(private val url: HttpUrl,
               private val parameters: Map<String, String> = emptyMap()) {

    fun execute(): Response {
        val connection = url.build().openConnection() as HttpURLConnection
        connection.requestMethod = "POST"

        if (parameters.isNotEmpty()) {
            val sb = StringBuilder()
            parameters.entries.forEach {
                sb.append("${it.key}=${it.value}&")
            }
            sb.removeRange(sb.length - 1, sb.length - 1)

            connection.doOutput = true
            DataOutputStream(connection.outputStream).apply {
                writeBytes(sb.toString())
                flush()
            }.close()
        }

        val responseCode = connection.responseCode

        val inputStream = if (responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream
        } else {
            connection.errorStream
        }

        val response = StringBuilder()
        BufferedReader(InputStreamReader(inputStream)).apply {
            lines().forEach {
                response.appendln(it)
            }
        }.close()

        return Response(responseCode, response.toString())
    }
}