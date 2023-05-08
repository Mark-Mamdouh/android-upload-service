package net.gotev.uploadservice.logger

import android.os.Environment
import android.util.Log
import org.joda.time.DateTime
import java.io.File
import java.io.FileOutputStream

class DefaultLoggerDelegate : UploadServiceLogger.Delegate {

    companion object {
        private const val TAG = "UploadService"
    }

    override fun error(component: String, uploadId: String, message: String, exception: Throwable?, logFilePath: String) {
        val logMessage = "$component - (uploadId: $uploadId) - $message - ${exception?.message}"
        Log.e(TAG, logMessage, exception)
        writeLogsToFile(logMessage, logFilePath)
    }

    override fun debug(component: String, uploadId: String, message: String, logFilePath: String) {
        val logMessage = "$component - (uploadId: $uploadId) - $message"
        Log.i(TAG, logMessage)
        writeLogsToFile(logMessage, logFilePath)
    }

    override fun info(component: String, uploadId: String, message: String, logFilePath: String) {
        val logMessage = "$component - (uploadId: $uploadId) - $message"
        Log.i(TAG, logMessage)
        writeLogsToFile(logMessage, logFilePath)
    }

    private fun writeLogsToFile(content: String, path: String) {
        try {
            val folderName = "OP Talk"
            val dir = File(path, "Logs")

            if (!dir.exists()) dir.mkdirs()

            val reportFile = File(dir, "op_talk_logs.txt")
            if (reportFile.exists().not()) reportFile.createNewFile()

            FileOutputStream(reportFile, true).bufferedWriter().use { writer ->
                writer.newLine()
                writer.write(DateTime.now().toString("yyyy/MM/dd, HH:mm\n"))
                writer.write(content)
                writer.newLine()
            }

        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}
