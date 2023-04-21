package de.vkb.dojo.pkg

import java.io.File
import java.io.OutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class Zipper(
    outputStream: OutputStream,
    val baseDir: File
): AutoCloseable {
    private val output = ZipOutputStream(outputStream)

    private fun add(dir: File, relpath: String) {
        for (fileLike in dir.listFiles() ?: emptyArray()) {
            val entrypath = "$relpath${fileLike.name}"
            println("$fileLike  -> $entrypath")
            if (fileLike.isDirectory) {
                val entry = ZipEntry("$entrypath/") // die pfad ermittlung is quick n dirty
                output.putNextEntry(entry)
                output.closeEntry()
                add(fileLike, "$entrypath/")
            } else {
                val entry = ZipEntry(entrypath)
                output.putNextEntry(entry)
//                val buffer = ByteArray(4096)
                fileLike.inputStream().use { inp ->
                    inp.copyTo(output, 4096)
//                    var len = inp.read(buffer)
//                    while (len > 0) {
//                        output.write(buffer, 0, len)
//                        len = inp.read(buffer)
//                    }
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
