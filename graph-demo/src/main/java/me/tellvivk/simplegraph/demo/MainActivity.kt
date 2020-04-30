package me.tellvivk.simplegraph.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.tellvivk.simplegraph.GraphAdapter
import me.tellvivk.simplegraph.GraphTheme
import me.tellvivk.simplegraph.LineGraph
import me.tellvivk.simplegraph.Point

class MainActivity : AppCompatActivity() {


    private lateinit var adapter: GraphAdapter<Point<Int>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = DemoAdapter()

        val lineGraph = LineGraph(
            context = this, adapter = adapter, showGrid = true, showLegend = true
        )

        linegraphFrame.removeAllViews()
        linegraphFrame.addView(lineGraph)
    }


}


open class DemoAdapter : GraphAdapter<Point<Int>>() {

    override fun getData(): List<Point<Int>> {
        TODO("Not yet implemented")
    }

    override fun getTitle(): String {
        TODO("Not yet implemented")
    }

    override fun getXTitle(): String {
        TODO("Not yet implemented")
    }

    override fun getYTitle(): String {
        TODO("Not yet implemented")
    }

    override fun getTheme(): GraphTheme {
        return super.getTheme()
    }
}
