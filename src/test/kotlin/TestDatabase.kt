import java.io.File
import kotlin.test.*

internal class TestDatabase {

    private val testDB: String = "testDB"

    @Test
    fun testGetPathName() {
        assertEquals("src/database/$testDB.txt", getPathName(testDB))
    }

    @Test
    fun testClearDatabase() {
        File(getPathName(testDB)).writeText("Test text")
        clearDatabase(testDB)
        assert(File(getPathName(testDB)).readLines().isEmpty())
    }

    @Test
    fun testAddElement() {
        clearDatabase(testDB)

        addElement("100", "123", testDB)
        addElement("0", "0", testDB)
        addElement("0", "55", testDB)
        addElement("qwerty", "asdfgh", testDB)
        assertEquals(listOf("100 123", "0 0", "qwerty asdfgh"), File(getPathName(testDB)).readLines())

        clearDatabase(testDB)
    }

    @Test
    fun testFindValue() {
        clearDatabase(testDB)

        addElement("100", "123", testDB)
        addElement("!!!", "???", testDB)

        assertEquals("123", findValue("100", testDB))
        assertEquals("???", findValue("!!!", testDB))
        assertNull(findValue("???", testDB))

        clearDatabase(testDB)
    }

    @Test
    fun testRemoveElement() {
        clearDatabase(testDB)

        addElement("100", "123", testDB)
        addElement("100", "xxx", testDB)
        removeElement("100", testDB)
        assertNull(findValue("100", testDB))

        removeElement("11", testDB)
        assertNull(findValue("11", testDB))

        addElement("qwerty", "asdfgh", testDB)
        removeElement("qwerty", testDB)
        assertNull(findValue("qwerty", testDB))
        addElement("qwerty", "!!!", testDB)
        assertEquals("!!!", findValue("qwerty", testDB))

        clearDatabase(testDB)
    }
}
