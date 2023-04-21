package de.vkb.dojo.pkg

import java.io.File
import java.io.OutputStream
import java.io.Writer
import java.security.MessageDigest
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class ZipperHasher(
    outputStream: OutputStream,
    val baseDir: File,
    val writer: Writer,

    ): AutoCloseable {
    private val output = ZipOutputStream(outputStream)

    private fun add(dir: File, relpath: String) {
        for (fileLike in dir.listFiles() ?: emptyArray()) {
            val entrypath = "$relpath${fileLike.name}"
            println("$fileLike  -> $entrypath")
            writer.append(entrypath)
            writer.append("\t")
            if (fileLike.isDirectory) {
                val entry = ZipEntry("$entrypath/") // die pfad ermittlung is quick n dirty
                output.putNextEntry(entry)
                output.closeEntry()
                writer.append("<dir>")
                writer.append("\n")
                add(fileLike, "$entrypath/")
            } else {
                writer.append(fileLike.length().toString())
                val entry = ZipEntry(entrypath)
                output.putNextEntry(entry)
                val digester = MessageDigest.getInstance("SHA-1")
                val buffer = ByteArray(4096)
                fileLike.inputStream().use { inp ->
                    var len = inp.read(buffer)
                    while (len > 0) {
                        digester.update(buffer, 0, len)
                        output.write(buffer, 0, len)
                        len = inp.read(buffer)
                    }
                    writer.append("\t")
                    writer.append(digester.digest().joinToString("") { "%02x".format(it) })
                    writer.append("\n")
                }
                output.closeEntry()
            }
        }
    }

    fun zip() {
        add(baseDir, "")
    }

    override fun close() {
        output.close()
    }
}
