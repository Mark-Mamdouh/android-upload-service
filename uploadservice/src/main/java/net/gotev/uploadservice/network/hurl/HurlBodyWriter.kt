package net.gotev.uploadservice.network.hurl

import java.io.IOException
import java.io.OutputStream
import net.gotev.uploadservice.network.BodyWriter

class HurlBodyWriter(private val stream: OutputStream, listener: OnStreamWriteListener) :
    BodyWriter(listener) {
    @Throws(Exception::class)
    override fun internalWrite(bytes: ByteArray) {
        try {
            println("writing-> bytes: $bytes with size ${bytes.size}")
            stream.write(bytes)
            println("finished writing without specific length ${bytes.size}")
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw exception
        }
    }

    @Throws(Exception::class)
    override fun internalWrite(bytes: ByteArray, lengthToWriteFromStart: Int) {
        try {
            println("writing-> bytes:$bytes lengthToWriteFromStart:$lengthToWriteFromStart with size: ${bytes.size}")
            stream.write(bytes, 0, lengthToWriteFromStart)
            println("finished writing with length $lengthToWriteFromStart and size: ${bytes.size}")
        } catch (exception: Exception) {
            exception.printStackTrace()
            throw exception
        }
    }

    @Throws(IOException::class)
    override fun flush() {
        stream.flush()
    }

    @Throws(IOException::class)
    override fun close() {
        stream.close()
    }
}
