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

    override fun refresh() {

    }

    override fun release() {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        refresh()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        release()
    }
}
