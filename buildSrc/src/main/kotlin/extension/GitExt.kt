package extension

import org.codehaus.groovy.runtime.ProcessGroovyMethods.execute
import org.codehaus.groovy.runtime.ProcessGroovyMethods.getText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getBranchName(): String {
    return getFullBranchName().split("/").last().toString()
}

fun getFullBranchName(): String {
    return try {
        System.getenv("GIT_BRANCH").takeIf {
            !it.isNullOrBlank()
        } ?: getText(execute("git rev-parse --abbrev-ref HEAD")).trim()
    } catch (ignored: Exception) {
        ""
    }
}

fun getGitVersionName(): String {
    val tag = getText(execute("git describe --tags")).split("-").first().trim()
    val versionTagRegex = Regex("^([vd]+)([\\.\\d])+\$")

    return if (tag.matches(versionTagRegex)) {
        tag.substring(1)
    } else {
        "1.0.0"
    }
}

fun getGitVersionCode(): Int {
    return ((Date().time / 1000 - 1451606400) / 10).toInt()
}

fun getGitRefHash(): String {
    return getText(execute("git rev-parse HEAD")).substring(0, 8)
}

fun buildTime(): String {
    return SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault()).format(Date())
}

fun prepareReleaseNotes(): String {
    return "Branch: ${getBranchName()}\n" +
        "Version: ${getGitVersionName()}\n" +
        "Version Code: ${getGitVersionCode()}\n" +
        "Commit: ${getGitRefHash()}\n" +
        "Build time: ${buildTime()}\n"
}