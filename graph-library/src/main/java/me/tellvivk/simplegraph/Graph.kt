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


data class Point<T>(
    val x: T,
    val y: T,
    val text: String = ""
)


val defaultGraphTheme = object : GraphTheme {
    override fun getBackgroundColor(): Int {
        return Color.WHITE
    }

    override fun getBackgroundPaint(): Paint {
        return Paint().apply { color = getBackgroundColor() }
    }

    override val color: Int
        get() = Color.BLACK

    override fun getTextColor(): Int {
        return Color.BLACK
    }

    override fun getGridPaint(): Paint {
        return Paint().apply {
            color = Color.GREEN
        }
    }

    override val gridGap: Float
        get() = 70f
}
