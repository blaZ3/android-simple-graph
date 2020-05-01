package me.tellvivk.simplegraph

import android.graphics.Paint


interface GraphTheme {
    fun getBackgroundColor(): Int
    fun getBackgroundPaint(): Paint
    fun getTextColor(): Int
    fun getGridPaint(): Paint
}

abstract class GraphAdapter<T> {

    abstract fun getData(): List<T>

    open fun getTheme(): GraphTheme {
        return defaultGraphTheme
    }

    abstract fun getTitle(): String

    abstract fun getXTitle(): String

    abstract fun getYTitle(): String

}
