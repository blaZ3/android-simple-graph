package me.tellvivk.simplegraph

import android.graphics.Paint


interface GraphTheme {
    val backgroundColor: Int
    val backgroundPaint: Paint
    val color: Int
    val textColor: Int
    val gridPaint: Paint
    val gridGap: Float
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
