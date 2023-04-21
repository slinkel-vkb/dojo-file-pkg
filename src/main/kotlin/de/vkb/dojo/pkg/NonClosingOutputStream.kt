package de.vkb.dojo.pkg

import java.io.OutputStream

class NonClosingOutputStream(
    val upstream: OutputStream
): OutputStream() {
    override fun write(b: ByteArray) {
        upstream.write(b)
    }

    override fun write(b: ByteArray, off: Int, len: Int) {
        upstream.write(b, off, len)
    }

    override fun write(b: Int) {
        upstream.write(b)
    }
}
