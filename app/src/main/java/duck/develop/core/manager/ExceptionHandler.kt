package duck.develop.core.manager

import android.os.Build
import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by Hwang on 2019-07-29.
 *
 * Description :
 */
class ExceptionHandler: Thread.UncaughtExceptionHandler {
    private var defaultHandler: Thread.UncaughtExceptionHandler? = null
    private val lineSeparator = "\n"

    init {
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        val stackTrace = StringWriter()
        e.printStackTrace(PrintWriter(stackTrace))
        val errorContent = StringBuilder()
        errorContent.append(lineSeparator)
        errorContent.append("************ CAUSE OF ERROR ************")
        errorContent.append(lineSeparator)
        errorContent.append(stackTrace.toString())
        errorContent.append("************ CAUSE OF ERROR ************")
        errorContent.append(lineSeparator)
        val causeOfError = StringBuilder()
        causeOfError.append(stackTrace.toString())

        errorContent.append(lineSeparator)
        errorContent.append("************ DEVICE INFORMATION ***********")
        errorContent.append(lineSeparator)
        errorContent.append("Brand: ")
        errorContent.append(Build.BRAND)
        errorContent.append(lineSeparator)
        errorContent.append("Device: ")
        errorContent.append(Build.DEVICE)
        errorContent.append(lineSeparator)
        errorContent.append("Model: ")
        errorContent.append(Build.MODEL)
        errorContent.append(lineSeparator)
        errorContent.append("Id: ")
        errorContent.append(Build.ID)
        errorContent.append(lineSeparator)
        errorContent.append("Product: ")
        errorContent.append(Build.PRODUCT)
        errorContent.append(lineSeparator)
        errorContent.append("************ DEVICE INFORMATION ***********")
        errorContent.append(lineSeparator)

        errorContent.append(lineSeparator)
        errorContent.append("************ FIRMWARE OF ANDROID ************")
        errorContent.append(lineSeparator)
        errorContent.append("SDK: ")
        errorContent.append(Build.VERSION.SDK_INT)
        errorContent.append(lineSeparator)
        errorContent.append("Release: ")
        errorContent.append(Build.VERSION.RELEASE)
        errorContent.append(lineSeparator)
        errorContent.append("Incremental: ")
        errorContent.append(Build.VERSION.INCREMENTAL)
        errorContent.append(lineSeparator)
        errorContent.append("************ FIRMWARE OF ANDROID ************")
        errorContent.append(lineSeparator)

        errorContent.append(lineSeparator)
        errorContent.append("************ DEBUG INFORMATION ************")
        errorContent.append(lineSeparator)
        errorContent.append("isAlive:")
        errorContent.append(t.isAlive)
        errorContent.append(lineSeparator)
        errorContent.append("************ DEBUG INFORMATION ************")
        errorContent.append(lineSeparator)

        Logger.e(errorContent.toString())
        defaultHandler?.uncaughtException(t, e)
    }
}