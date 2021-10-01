import kotlin.text.StringBuilder

enum class Commands {
    Add, Remove, Modify, Clear, Help, Wrong
}

fun parseInput(args: Array<String>): List<String> {
    val input = args.toMutableList()
    if (input.isEmpty()) {
        return listOf(Commands.Help.name)
    }
    return when (input[0]) {
        "-a", "-add" -> {
            input[0] = Commands.Add.name
            if (input.size == 3)
                input
            else
                listOf(Commands.Wrong.name)
        }
        "-r", "--remove" -> {
            input[0] = Commands.Remove.name
            if (input.size == 2)
                input
            else
                listOf(Commands.Wrong.name)
        }
        "-m", "--modify" -> {
            input[0] = Commands.Modify.name
            if (input.size == 3)
                input
            else
                listOf(Commands.Wrong.name)
        }
        "-c", "--clear" -> {
            input[0] = Commands.Clear.name
            if (input.size == 1)
                input
            else
                listOf(Commands.Wrong.name)
        }
        "-h", "--help" -> {
            listOf(Commands.Help.name)
        }
        else -> {
            listOf(Commands.Help.name)
        }
    }
}

fun printHelp() {
    val text = StringBuilder()
    text.append("Usage: COMMAND [command arguments]\n\n")
        .append("Commands:\n")
    val names : List<String> = listOf(  "-a, --add KEY VALUE",
                                        "-r, --remove KEY",
                                        "-m, --modify KEY VALUE",
                                        "-c, --clear",
                                        "-h, --help")
    val descriptions: List<String> = listOf(    "Add a pair KEY VALUE",
                                                "Remove a KEY",
                                                "Replace a value of KEY with VALUE",
                                                "Clear database",
                                                "Show this message and exit")
    val pad = 30
    descriptions.forEachIndexed { i, description ->
        text.append(names[i])
            .append(String().padStart(pad - names[i].length, ))
            .append(description)
            .append('\n')
    }
    println(text.toString())
}

fun printWrong() {
    println("Something went wrong")
    printHelp()
}

fun main(args: Array<String>) {
    val input = parseInput(args)
    when (input[0]) {
        Commands.Add.name -> addElement(input[1], input[2])
        Commands.Remove.name -> removeElement(input[1])
        Commands.Modify.name -> modifyElement(input[1], input[2])
        Commands.Clear.name -> clearDatabase()
        Commands.Help.name -> printHelp()
        else -> printWrong()
    }
}
