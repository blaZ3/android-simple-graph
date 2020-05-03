package me.tellvivk.simplegraph

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

interface Graph {

    fun refresh(canvas: Canvas?)
    fun release()

}


interface ILineGraph : Graph {

}


data class Point(
    val x: Float,
    val y: Float,
    val text: String = ""
)


val defaultGraphTheme = object : GraphTheme {
    override val backgroundColor: Int
        get() {
            return Color.WHITE
        }

    override val backgroundPaint: Paint
        get() {
            return Paint().apply { color = backgroundColor }
        }

    override val color: Int
        get() = Color.BLACK

    override val textColor: Int
        get() {
            return Color.BLACK
        }

    override val gridPaint: Paint
        get() {
            return Paint().apply {
                color = Color.GREEN
            }
        }

    override val gridGap: Float
        get() = 70f
}
