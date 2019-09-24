package duck.develop.core.manager

import android.util.Log
import com.facebook.stetho.inspector.protocol.module.Debugger
import duck.develop.calculator.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Hwang on 2018-10-18.
 *
 * Description : 로그 매니저
 * 
 * 업데이트 예정 사항
 * 1. TAG에 관하여.. 필터링 너무 불편함 
 *  - 경우 (1) 기본적으로 사용되는 TAG(ex:Logger Class Name)가 있고 사용자가 Global Tag를 설정하면 해당 TAG로 모두 변경되어 출력
 *  - 경우 (2) 기본적으로 클래스명으로 로그가 남고 사용자가 Override 하거나 Global Tag를 설정하면 해당 TAG로 모두 변경되어 출력 
 * 2. Key Value 데이터(json, map, xml 등등) 정렬 및 정렬 옵션 오버라이드 처리
 *  - 기본적으로 사용자가 옵션(정렬 여부)을 설정해 놓으면 옵션대로 출력하되 바로 오버라이드해서 설정을 바꿔 출력할 수 있도록
 * 3. 네트워크 인터셉트 로그 
 *  - 이건 아직 더 공부해봐야겠지만 그냥 인터셉트에 바로 로그를 남겨도 되고 Adapter 같은거 만들어서 남겨도 될듯
 */
class Logger {
    companion object {
        private val TAG = "_${Logger::class.java.simpleName}_"

        /*
         * error log
         */
        fun e(message: String?) {
            e(TAG, message ?: "")
        }
        fun e(message: String?, tr: Throwable) {
            e(TAG, message ?: "", tr)
        }
        fun e(tag: String, message: String) {
            if (BuildConfig.DEBUG) Log.e(tag, buildMessage(message))
        }
        fun e(tag: String, message: String, tr: Throwable) {
            if (BuildConfig.DEBUG) Log.e(tag, buildMessage(message), tr)
        }
        /*
         * warning log
         */
        fun w(message: String) {
            w(TAG, message)
        }
        fun w(tag: String, message: String) {
            if (BuildConfig.DEBUG) Log.w(tag, buildMessage(message))
        }
        /*
         * Information log
         */
        fun i(message: String) {
            i(TAG, message)
        }
        fun i(message: String, tr: Throwable) {
            i(TAG, message, tr)
        }
        fun i(tag: String, message: String) {
            if (BuildConfig.DEBUG) Log.i(tag, buildMessage(message))
        }
        fun i(tag: String, message: String, tr: Throwable) {
            if (BuildConfig.DEBUG) Log.i(tag, buildMessage(message), tr)
        }
        /*
         * debug log
         */
        fun d(message: String) {
            d(TAG, message)
        }
        fun d(tag: String, message: String) {
            if (BuildConfig.DEBUG) Log.d(tag, buildMessage(message))
        }
        /*
         * Verbose log
         */
        fun v(tag: String, message: String) {
            if (BuildConfig.DEBUG) Log.v(tag, buildMessage(message))
        }
        /**
         * 현재 StackTrace에서 Logger 클래스 정보를 제외하고 그 외 클래스 정보와 메세지를 결합하여 반환하는 함수
         * @param message 로그에 출력하려는 메세지
         * @return 클래스 정보와 사용자가 전달한 메세지
         */
        private fun buildMessage(message: String): String {
            val logger = Logger::class.java.name
            val sb = StringBuilder()

            //현재 시간
            val date = Date(System.currentTimeMillis())
            val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
            val currentDate = dateFormat.format(date)
            sb.append(currentDate).append(":")

            //스택 정보
            val stacks = Thread.currentThread().stackTrace

            for (i in 4 until stacks.size) {
                val element = stacks[i]
                if (element.className.length < logger.length || logger != element.className.substring(0, logger.length)) {
                    sb.append("(${element.fileName}:${element.lineNumber})")
                        .append("." + element.methodName + ":")
                    break
                }
            }
            sb.append(message)

            return sb.toString()
        }
    }
}