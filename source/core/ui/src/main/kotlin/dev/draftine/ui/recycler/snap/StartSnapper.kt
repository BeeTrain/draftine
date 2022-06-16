package dev.draftine.ui.recycler.snap

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView


class StartSnapper : LinearSnapHelper() {

    private var verticalHelper: OrientationHelper? = null
    private var horizontalHelper: OrientationHelper? = null

    companion object {

        infix fun attachTo(recyclerView: RecyclerView) {
            recyclerView.onFlingListener = null
            StartSnapper().attachToRecyclerView(recyclerView)
        }
    }

    override fun calculateDistanceToFinalSnap(
        layoutManager: RecyclerView.LayoutManager,
        targetView: View
    ): IntArray {
        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }
        if (layoutManager.canScrollVertically()) {
            out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager))
        } else {
            out[1] = 0
        }
        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return if (layoutManager is LinearLayoutManager) {
            if (layoutManager.canScrollHorizontally()) {
                getStartView(layoutManager, getHorizontalHelper(layoutManager))
            } else {
                getStartView(layoutManager, getVerticalHelper(layoutManager))
            }
        } else super.findSnapView(layoutManager)
    }

    private fun distanceToStart(targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun getStartView(
        layoutManager: RecyclerView.LayoutManager,
        helper: OrientationHelper
    ): View? {
        if (layoutManager is LinearLayoutManager) {
            val firstChild = layoutManager.findFirstVisibleItemPosition()

            if (firstChild == RecyclerView.NO_POSITION || layoutManager.isLastItem()) {
                return null
            }
            val child = layoutManager.findViewByPosition(firstChild)
            val shouldReturnChild = helper.getDecoratedEnd(child) >= helper.getDecoratedMeasurement(child) / 2 &&
                    helper.getDecoratedEnd(child) > 0

            return when {
                shouldReturnChild -> child
                layoutManager.isLastItem() -> null
                else -> layoutManager.findViewByPosition(firstChild + 1)
            }
        }
        return super.findSnapView(layoutManager)
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return verticalHelper.takeIf { it != null } ?: OrientationHelper.createVerticalHelper(layoutManager)
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        return horizontalHelper.takeIf { it != null } ?: OrientationHelper.createHorizontalHelper(layoutManager)
    }

    private fun LinearLayoutManager.isLastItem(): Boolean {
        return findLastCompletelyVisibleItemPosition() == itemCount - 1
    }
}