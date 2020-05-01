package me.tellvivk.simplegraph

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.widget.FrameLayout

@SuppressLint("ViewConstructor")
class LineGraph<T>(
    context: Context,
    private val adapter: GraphAdapter<T>,
    private val showLegend: Boolean = true,
    private val showGrid: Boolean = false
) : ILineGraph, FrameLayout(context) {

    override fun refresh(canvas: Canvas?) {
        canvas?.let {

            it.drawLine(0f, 10f, width.toFloat(), 10f, adapter.getTheme().getGridPaint())

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
}
