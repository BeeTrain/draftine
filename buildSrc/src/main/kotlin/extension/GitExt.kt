package extension

import org.codehaus.groovy.runtime.ProcessGroovyMethods.execute
import org.codehaus.groovy.runtime.ProcessGroovyMethods.getText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun branchName(): String {
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

fun versionName(): String {
    val tag = getText(execute("git describe --tags")).split("-").first().trim()
    val versionTagRegex = Regex("^([vd]+)([\\.\\d])+\$")

    return if (tag.matches(versionTagRegex)) {
        tag.substring(1)
    } else {
        "1.0.0"
    }
}

fun versionCode(): Int {
    return ((Date().time / 1000 - 1451606400) / 10).toInt()
}

fun refHash(): String {
    return getText(execute("git rev-parse HEAD")).substring(0, 8)
}

fun buildDate(): String {
    return SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault()).format(Date())
}

fun changes(): String {
    val gitTag = getText(execute("git tag"))
    println("gitTag = ${gitTag}")

    val tagList = gitTag.split("\n")
    println("tagList = ${tagList}")

    return if (tagList.size < 2) {
        ""
    } else {
        val lastTags = tagList.takeLast(2)
        println("lastTags = ${lastTags}")

        val changes = getText(execute("git log --pretty=oneline ^${lastTags.first()} ${lastTags.last()}"))
        println("changes = ${changes}")

        changes
    }
}

fun prepareReleaseNotes(): String {
    return "Branch: ${branchName()}\n" +
        "Version: ${versionName()}\n" +
        "Version Code: ${versionCode()}\n" +
        "Commit: ${refHash()}\n" +
        "Build date: ${buildDate()}\n" +
        "Changes: \n ${changes()}"
}