import java.io.File
import java.io.FileOutputStream

const val DATABASE: String = "database"

fun getPathName(database: String = DATABASE): String {
    return "database/$database.txt"
}

fun findValue(key: String, database: String = DATABASE): String? {
    require(key.isNotEmpty())
    for (line in File(getPathName(database)).readLines()) {
        require(line.isNotBlank())
        val (currentKey, value) = line.split(' ')
        if (key == currentKey) {
            return value
        }
    }
    return null
}

fun modifyElement(key: String, value: String, database: String = DATABASE): Boolean {
    val exists = removeElement(key, database)
    addElement(key, value, database)
    return exists
}

fun removeElement(key: String, database: String = DATABASE): Boolean {
    val file = File(getPathName(database))
    val lines = file.readLines()
    val text = (lines.filter {it.split(' ')[0] != key}).joinToString("\n")
    file.writeText(text)
    return lines.any { it.split(' ')[0] == key }
}

fun addElement(key: String, value: String, database: String = DATABASE): Boolean {
    if (findValue(key, database) != null) {
        return false
    }
    val line = "$key $value"
    val writer = FileOutputStream(getPathName(database), true).bufferedWriter()
    writer.write(line)
    writer.newLine()
    writer.close()
    return true
}

fun clearDatabase(database: String = DATABASE) {
    File(getPathName(database)).bufferedWriter().close()
}