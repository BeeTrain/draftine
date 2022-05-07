package dev.draftine.lint.detector

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.XmlContext
import com.android.tools.lint.detector.api.XmlScannerConstants
import org.w3c.dom.Element

@Suppress("UnstableApiUsage")
class ExternalViewUsageDetector : ResourceXmlDetector() {

    companion object {

        private const val UI_MODULE_PACKAGE = "dev.draftine.ui."
        private val ignoredTags = listOf("include", "fragment", "merge", "tag", "view", "View")

        private const val DESCRIPTION = "Marks views that are not part of the :ui module."
        private const val EXPLANATION = """
            Use views only from the :ui module.
            If the module does not have a suitable view, it must be added.
        """

        private const val ID = "ExternalViewUsage"
        private val CATEGORY = Category.CORRECTNESS
        private const val PRIORITY = 5
        private val SEVERITY = Severity.WARNING

        @JvmStatic
        internal val ISSUE = Issue.create(
            ID,
            DESCRIPTION,
            EXPLANATION,
            CATEGORY,
            PRIORITY,
            SEVERITY,
            Implementation(
                ExternalViewUsageDetector::class.java,
                Scope.RESOURCE_FILE_SCOPE
            )
        )
    }

    override fun appliesTo(folderType: ResourceFolderType): Boolean = ResourceFolderType.LAYOUT == folderType

    override fun getApplicableElements() = XmlScannerConstants.ALL

    override fun visitElement(context: XmlContext, element: Element) {
        if (!element.tagName.contains(UI_MODULE_PACKAGE) && ignoredTags.contains(element.tagName).not()) {
            context.report(ISSUE, context.getLocation(element), EXPLANATION)
        }
    }
}