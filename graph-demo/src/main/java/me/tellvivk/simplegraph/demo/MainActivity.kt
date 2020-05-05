package me.tellvivk.simplegraph.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.tellvivk.simplegraph.GraphAdapter
import me.tellvivk.simplegraph.GraphData
import me.tellvivk.simplegraph.GraphType.DISTINCT
import me.tellvivk.simplegraph.LineGraph
import me.tellvivk.simplegraph.Point

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: GraphAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DemoAdapter()

        val lineGraph = LineGraph(
            context = this, adapter = adapter, showGrid = true, showLegend = true
        )

        linegraphFrame.removeAllViews()
        linegraphFrame.addView(lineGraph)
        lineGraph.invalidate()
    }


}


open class DemoAdapter : GraphAdapter() {
    override val graphData: GraphData
        get() = GraphData(
            type = DISTINCT,
            xRange = Pair(0L, 10L),
            yRange = Pair(0L, 10L),
            xValues = listOf(0f, 1f, 2f, 3f, 4f, 5f, 10f),
            yValues = listOf(0f, 1f, 2f, 3f, 4f, 5f, 10f),
            points = listOf(
                Point(1f, 1f, "One"),
                Point(2f, 3f, "Two"),
                Point(3f, 3f, "Three"),
                Point(4f, 7f, "Four"),
                Point(5f, 5f, "Five")
            )
        )

    override val title: String
        get() {
            return "Graph title"
        }

    override val xTitle: String
        get() {
            return "X"
        }

    override val yTitle: String
        get() {
            return "Y"
        }
}
