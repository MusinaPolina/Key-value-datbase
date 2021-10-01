import java.io.File
import java.io.FileOutputStream

const val DATABASE: String = "database"

fun getPathName(database: String = DATABASE): String {
    return "src/database/$database.txt"
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

fun modifyElement(key: String, value: String, database: String = DATABASE) {
    removeElement(key, database)
    addElement(key, value, database)
}

fun removeElement(key: String, database: String = DATABASE) {
    val file = File(getPathName(database))
    val lines = file.readLines()
    val text = (lines.filter {it.split(' ')[0] != key}).joinToString("\n")
    file.writeText(text)
}

fun addElement(key: String, value: String, database: String = DATABASE) {
    if (findValue(key, database) != null) {
        return
    }
    val line = "$key $value"
    val writer = FileOutputStream(getPathName(database), true).bufferedWriter()
    writer.write(line)
    writer.newLine()
    writer.close()
}

fun clearDatabase(database: String = DATABASE) {
    File(getPathName(database)).bufferedWriter().close()
}