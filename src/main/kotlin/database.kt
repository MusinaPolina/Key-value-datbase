import java.io.File
import java.io.PrintWriter
import java.io.FileOutputStream

fun getPathName(): String {
    return "src/database/database.txt"
}

fun findValue(key: String): String? {
    for (line in File(getPathName()).readLines()) {
        val (currentKey, value) = line.split(' ')
        if (key == currentKey) {
            return value
        }
    }
    return null
}

fun modifyElement(key: String, value: String) {
    removeElement(key)
    addElement(key, value)
}

fun removeElement(key: String) {
    val file = File(getPathName())
    val lines = file.readLines()
    val text = "${(lines.filter {it.split(' ')[0] != key}).joinToString("\n")}\n"
    file.writeText(text)
}

fun addElement(key: String, value: String) {
    val line = "$key $value"
    val writer = FileOutputStream(getPathName(), true).bufferedWriter()
    writer.write(line)
    writer.newLine()
    writer.close()
}

