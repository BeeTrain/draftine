package dev.draftine.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import dev.draftine.lint.detector.ExternalViewUsageDetector

@Suppress("UnstableApiUsage")
class CustomLintRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            ExternalViewUsageDetector.ISSUE
        )

    override val api: Int
        get() = CURRENT_API
}