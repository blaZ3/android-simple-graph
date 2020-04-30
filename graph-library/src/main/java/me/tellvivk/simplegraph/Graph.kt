package me.tellvivk.simplegraph

import android.graphics.Color
import android.graphics.Paint

interface Graph {

    fun refresh()
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
        return Paint()
    }

    override fun getTextColor(): Int {
        return Color.BLACK
    }
}
