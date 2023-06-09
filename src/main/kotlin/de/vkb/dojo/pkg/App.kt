/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package de.vkb.dojo.pkg

import java.io.File
import java.security.MessageDigest

class App(
    val inputDir: File,
    val zipOutputFile: File,
    val contentsOutputFile: File,
    val zipSigOutputFile: File
) {
    fun machMal() {
        val start = System.currentTimeMillis()
//        Zipper(zipOutputFile.outputStream().buffered(4096), inputDir).use { zipper ->
//            zipper.zip()
//        }
//        contentsOutputFile.writer().use { writer ->
//            Hasher(writer, inputDir).hash()
//        }


        contentsOutputFile.writer().use { writer ->
            HashingOutputStream(zipOutputFile.outputStream().buffered(4096), "SHA-1").use { out ->
                ZipperHasher(out, inputDir, writer).use { zipper ->
                    zipper.zip()
                }
                zipSigOutputFile.writer().use { writer ->
                    writer.append(out.digest())
                    writer.append("\n")
                }
            }
        }

//        zipSigOutputFile.writer().use { writer ->
//            val digester = MessageDigest.getInstance("SHA-1")
//            val buffer = ByteArray(4096)
//            zipOutputFile.inputStream().use { inp ->
//                var len = inp.read(buffer)
//                while (len > 0) {
//                    digester.update(buffer, 0, len)
//                    len = inp.read(buffer)
//                }
//                writer.append(digester.digest().joinToString("") { "%02x".format(it) })
//                writer.append("\n")
//            }
//        }

        println("ich hab ${System.currentTimeMillis()-start}ms gebraucht")
    }

}

fun main() {
    App(
        File("./input/"),
        File("./output.zip"),
        File("./output.lst"),
        File("./output.sum"),
    ).machMal()
}
