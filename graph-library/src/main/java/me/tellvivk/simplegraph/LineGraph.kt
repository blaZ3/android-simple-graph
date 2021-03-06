package me.tellvivk.simplegraph

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue.*
import android.view.View
import kotlin.math.roundToLong


@SuppressLint("ViewConstructor")
class LineGraph(
    context: Context,
    private val adapter: GraphAdapter,
    private val showLegend: Boolean = true,
    private val showGrid: Boolean = false
) : ILineGraph, View(context) {

    private var margin = applyDimension(COMPLEX_UNIT_DIP, 8f, resources.displayMetrics)
    private var graphTextSize = applyDimension(COMPLEX_UNIT_SP, 14f, resources.displayMetrics)
    private var pointSize = applyDimension(COMPLEX_UNIT_SP, 4f, resources.displayMetrics)
    private var plotSize = applyDimension(COMPLEX_UNIT_SP, 2f, resources.displayMetrics)

    override fun refresh(canvas: Canvas?) {
        canvas?.let {

            val heightF = height.toFloat() - (margin * 2)
            val widthF = width.toFloat() - (margin * 2)

            val topX = 0f + margin
            val topY = 0f + margin

            it.drawColor(adapter.getTheme().backgroundColor)
            it.drawPaint(adapter.getTheme().backgroundPaint)

            if (showGrid) {
                val gridPaint = adapter.getTheme().gridPaint
                for (i in 1..height) {
                    it.drawLine(
                        topX,
                        i * adapter.getTheme().gridGap,
                        widthF,
                        i * adapter.getTheme().gridGap,
                        gridPaint
                    )
                }
                for (i in 1..width) {
                    it.drawLine(
                        i * adapter.getTheme().gridGap,
                        topY,
                        i * adapter.getTheme().gridGap,
                        heightF,
                        gridPaint
                    )
                }
            }

            val borderPaint = Paint().apply {
                color = adapter.getTheme().color
                style = Paint.Style.STROKE
                strokeWidth = 8f
                isAntiAlias = true
            }
            it.drawLine(topX, heightF, widthF, heightF, borderPaint)
            it.drawLine(topX, topX, topX, heightF, borderPaint)

            plot(it, Pair(widthF, heightF), Pair(topX, topY))
        }

    }

    override fun release() {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        refresh(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        release()
    }

    private fun plot(canvas: Canvas, size: Pair<Float, Float>, start: Pair<Float, Float>) {
        adapter.graphData.run {
            if (!isValid()) throw IllegalArgumentException("Graph data is not valid")

            when (type) {
                GraphType.CONTINUOUS -> {

                    //plot the x co ordinates
                    val textPaint = Paint().apply {
                        color = adapter.getTheme().textColor
                        textSize = graphTextSize
                    }

                    //draw X
                    (xRange.first..xRange.second).run {
                        var xPoint = 0f
                        for (i in this step ((xRange.second.toFloat() - xRange.first) / 10).roundToLong()) {
                            canvas.drawText("$i", xPoint, size.second, textPaint)
                            xPoint += width / 10
                        }
                    }


                    //draw Y
                    (yRange.first..yRange.second).run {
                        var yPoint = size.second
                        for (i in this step ((yRange.second.toFloat() - yRange.first) / 10).roundToLong()) {
                            canvas.drawText("$i", start.first, yPoint, textPaint)
                            yPoint -= height / 10
                        }
                    }

                    plotValues(
                        (xRange.second - xRange.first).toFloat(),
                        (yRange.second - yRange.first).toFloat(),
                        canvas = canvas, textPaint = textPaint,
                        points = adapter.graphData.points,
                        width = size.first, height = size.second,
                        plotSize = plotSize, pointSize = pointSize
                    )


                }
                GraphType.DISTINCT -> {

                    //plot the x co ordinates
                    val textPaint = Paint().apply {
                        color = adapter.getTheme().textColor
                        textSize = graphTextSize
                    }

                    //draw X
                    (xValues).sorted().run {
                        var xPoint = 0f
                        for (i in this) {
                            canvas.drawText("$i", xPoint, size.second, textPaint)
                            xPoint += width / xValues.size
                        }
                    }


                    //draw Y
                    (yValues).sorted().run {
                        var yPoint = size.second
                        for (i in this) {
                            canvas.drawText("$i", start.first, yPoint, textPaint)
                            yPoint -= height / yValues.size
                        }
                    }

                    plotValues(
                        xValues.max()!! - xValues.min()!!,
                        yValues.max()!! - yValues.min()!!,
                        canvas = canvas, textPaint =  textPaint,
                        width = size.first, height = size.second,
                        pointSize = pointSize, plotSize = plotSize,
                        points = adapter.graphData.points
                    )


                }
            }
        }
    }


}


private fun plotValues(
    xDiff: Float, yDiff: Float, canvas: Canvas, textPaint: Paint,
    points: List<Point>, width: Float, height: Float,
    pointSize: Float, plotSize: Float
) {
    var previousPoint: Pair<Float, Float>? = null
    points.forEach { point ->
        val pointX = point.x.div(xDiff) * width
        val pointY = height - (point.y.div(yDiff) * height)
        canvas.drawCircle(pointX, pointY, pointSize, textPaint)
        previousPoint?.let {
            canvas.drawLine(it.first, it.second, pointX, pointY, textPaint.apply {
                strokeWidth = plotSize
            })
        }
        previousPoint = Pair(pointX, pointY)
    }
}
