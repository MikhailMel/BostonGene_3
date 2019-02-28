package ru.scratty

import ru.scratty.translator.Language
import ru.scratty.translator.Translator
import java.util.*

object Launcher {

    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isEmpty()) {
            println("Не найден API-ключ")
            return
        }

        val scanner = Scanner(System.`in`)

        val translator = Translator(args[0])

        var line: String
        do {
            print("Введите строку для перевода (на английском языке): ")
            line = scanner.nextLine()

            if (line == "/exit") {
                break
            }

            println(translator.translate(line, Language.ENGLISH, Language.RUSSIAN))
        } while (true)
    }
}