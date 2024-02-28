package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = listOf(
            1.0,
            2.0,
            2.5,
            3.0,
            3.5,
            4.0,
            5.0
        )
        val list2 = listOf(
            1,
            2,
            3,
            4,
            5
        )
        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        Log.d("getTestDataArray output", getTestDataArray().toString())
        Log.d("averageLessThanMedian output", averageLessThanMedian(list).toString())
        Log.d("getView output", getView(0,null,list2,this@MainActivity).toString())
    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers

    private fun getTestDataArray() : List<Int> = MutableList(10){Random.nextInt()}.sorted()

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean =
        listOfNumbers.sorted().let {
           it.average() < if (it.size % 2 == 0)
               (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
           else
               it[it.size / 2]
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)


    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
        return (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5,10,10,0)
            textSize = 22f
        }).apply{text = collection[position].toString()}
    }
}