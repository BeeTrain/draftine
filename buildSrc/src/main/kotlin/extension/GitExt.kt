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
    val tagsText1 = getText(execute("git tag --sort=-version:refname | head -n 2"))
    println("tagsText1 = ${tagsText1}")

    val tagsText2 = getText(execute("git tag --sort=-version:refname | Select -First 2"))
    println("tagsText2 = ${tagsText2}")

    val lastTags = tagsText1.split("\n")

    val changes = when {
        lastTags.size < 2 -> ""
        else -> getText(execute("git log --pretty=oneline ^${lastTags.first()} ${lastTags.last()}"))
    }

    println("changes = ${changes}")

    return changes
}

fun prepareReleaseNotes(): String {
    return "Branch: ${branchName()}\n" +
        "Version: ${versionName()}\n" +
        "Version Code: ${versionCode()}\n" +
        "Commit: ${refHash()}\n" +
        "Build date: ${buildDate()}\n" +
        "Changes: \n ${changes()}"
}