package me.tellvivk.simplegraph

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.applyDimension
import android.view.View


@SuppressLint("ViewConstructor")
class LineGraph<T>(
    context: Context,
    private val adapter: GraphAdapter<T>,
    private val showLegend: Boolean = true,
    private val showGrid: Boolean = false
) : ILineGraph, View(context) {

    private var margin = applyDimension(COMPLEX_UNIT_DIP, 8f, resources.displayMetrics)

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

            plot(it)
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

    private fun plot(canvas: Canvas) {
    }
}
