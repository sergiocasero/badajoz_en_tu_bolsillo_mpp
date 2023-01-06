import java.io.File
import java.io.FileInputStream
import java.util.*

private fun getFile(path: String, file: String): File {
    return File("$path/$file")
}

private fun getProperty(file: String, property: String): String {
    val properties = Properties()
    properties.load(FileInputStream(file))
    return properties.getProperty(property)
}

fun getSignFile(path: String, file: String): File =
    getFile(path, getProperty(file, "KEYSTORE_FILE"))

fun getSignFilePassword(file: String): String = getProperty(file, "KEYSTORE_PASSWORD")
fun getSignAlias(file: String) = getProperty(file, "KEY_ALIAS")
fun getSignAliasPassword(file: String) = getProperty(file, "KEY_PASSWORD")
fun getVersionName(file: String) = getProperty(file, "T21_APP_VERSION")

fun File.printFileName() {
    println("********** ${this.name} *********")
}
