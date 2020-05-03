package me.tellvivk.simplegraph

import android.graphics.Paint
import me.tellvivk.simplegraph.GraphType.CONTINUOUS
import me.tellvivk.simplegraph.GraphType.DISTINCT


interface GraphTheme {
    val backgroundColor: Int
    val backgroundPaint: Paint
    val color: Int
    val textColor: Int
    val gridPaint: Paint
    val gridGap: Float
}

enum class GraphType {
    CONTINUOUS, DISTINCT
}

data class GraphData(
    val type: GraphType = CONTINUOUS,
    val xRange: Pair<Long, Long> = Pair(0, 0),
    val yRange: Pair<Long, Long> = Pair(0, 0),
    val xValues: List<Float> = listOf(),
    val yValues: List<Float> = listOf(),
    val points: List<Point> = listOf()
) {
    fun isValid(): Boolean {
        when (type) {
            CONTINUOUS -> {
                if ((xRange.first == xRange.second) || (yRange.first == yRange.second)) {
                    throw IllegalArgumentException(
                        "Invalid xRange or yRange when type is $type"
                    )
                }
            }
            DISTINCT -> {
                if (xValues.isEmpty() || yValues.isEmpty()) {
                    throw IllegalArgumentException(
                        "xValues and yValues should not be empty when type is $type"
                    )
                }
            }
        }
        return points.isNotEmpty()
    }
}

abstract class GraphAdapter {

    abstract val graphData: GraphData

    open fun getTheme(): GraphTheme {
        return defaultGraphTheme
    }

    abstract val title: String

    abstract val xTitle: String

    abstract val yTitle: String

}
