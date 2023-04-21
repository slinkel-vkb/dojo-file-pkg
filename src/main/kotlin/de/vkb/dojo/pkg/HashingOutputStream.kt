package de.vkb.dojo.pkg

import java.io.OutputStream
import java.security.MessageDigest

class HashingOutputStream(
    val upstream: OutputStream,
    algorithm: String
): OutputStream() {
    private val digester = MessageDigest.getInstance(algorithm)


    fun digest(): String {
        return digester.digest().joinToString("") { "%02x".format(it) }
    }

    override fun write(b: ByteArray) {
        upstream.write(b)
        digester.update(b)
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        upstream.write(b, off, len)
        digester.update(b, off, len)
    }

    override fun write(b: Int) {
        upstream.write(b)
        digester.update(b.toByte())
    }

    override fun close() {
        upstream.close()
        super.close()
    }
}
