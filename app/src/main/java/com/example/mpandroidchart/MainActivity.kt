package com.example.mpandroidchart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IBubbleDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewConstract {


    var currentChart = "Bar Chart"
    val chartArray = mutableListOf("Bar Chart", "Line Chart", "Bubble Chart")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        click()

    }


    fun init() {

        var linearlayout = LinearLayoutManager(this)
        linearlayout.orientation = LinearLayoutManager.VERTICAL

        rv_list.layoutManager = linearlayout
        rv_list.adapter = ListAdapter(this, this)

        barChartInit()
        lineChartInit()
        bubbleChartInit()
    }

    fun click() {

        bt_list.setOnClickListener(View.OnClickListener {

            when (rv_list.visibility) {
                RecyclerView.VISIBLE -> rv_list.visibility = RecyclerView.GONE
                RecyclerView.GONE -> rv_list.visibility = RecyclerView.VISIBLE
            }

        })
    }

    fun barChartInit() {

        var mathBarArrayEntry = ArrayList<BarEntry>()
        val mathScore = arrayListOf(39f, 54f, 69f, 83f, 99f, 53f, 52f, 35f, 93f)

        var englishBarArrayEntry = ArrayList<BarEntry>()
        val englishScore = arrayListOf(66f, 24f, 45f, 27f, 56f, 84f, 93f, 42f, 9f)

        for (i in 0 until mathScore.size) {
            val barEntry1 = BarEntry((2 * i + 1).toFloat(), mathScore[i])
            val barEntry2 = BarEntry((2 * i + 2).toFloat(), englishScore[i])
            mathBarArrayEntry.add(barEntry1)
            englishBarArrayEntry.add(barEntry2)
        }

        // 第一層 (每一個長條圖設計)
        val barMathSet = BarDataSet(mathBarArrayEntry, "Math") // 設置資料意義
        barMathSet.setColor(Color.BLUE) // 設置圖形顏色

        val barEnglishSet = BarDataSet(englishBarArrayEntry, "English") // 設置資料意義
        barEnglishSet.setColor(Color.GREEN) // 設置圖形顏色

        // 第二層 (蒐集所有長條圖)
        val barArrayDataSet = ArrayList<IBarDataSet>()
        barArrayDataSet.add(barMathSet)        //看有幾個長條圖，都丟進 list 裡面
        barArrayDataSet.add(barEnglishSet)     //看有幾個長條圖，都丟進 list 裡面

        // 第三層 (整張圖表設計)
        val allBarData = BarData(barArrayDataSet)
        allBarData.barWidth = 0.2f;  // 圖的寬度
        allBarData.setValueTextSize(10f) // 圖上字的大小


        bc_data.let {
            it.animateXY(3000, 3000)
            it.data = allBarData

//            it.setDrawGridBackground(true) //網格
//            it.setDrawBarShadow(false)     //背景陰影
//            it.setDrawBorders(false)       //顯示邊界
//            it.setPadding(1,1,1,1)
//
//            // 動畫
//            it.animateY(1000, Easing.Easi.Linear);
//            it.animateX(1000, Easing.EasingOption.Linear);
//            it.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            it.setOnTouchListener(null);


        }
    }

    fun lineChartInit() {

        var mathLineArrayEntry = ArrayList<Entry>()
        val mathScore = arrayListOf(39f, 54f, 69f, 83f, 99f, 53f, 52f, 35f, 93f)

        var englishLineArrayEntry = ArrayList<Entry>()
        val englishScore = arrayListOf(66f, 24f, 45f, 27f, 56f, 84f, 93f, 42f, 9f)

        for (i in 1 until mathScore.size) {
            val barEntry1 = BarEntry((2 * i + 1).toFloat(), mathScore[i])
            val barEntry2 = BarEntry((2 * i + 2).toFloat(), englishScore[i])
            mathLineArrayEntry.add(barEntry1)
            englishLineArrayEntry.add(barEntry2)
        }

        val lineMathSet = LineDataSet(mathLineArrayEntry, "Math") // 設置資料意義
        lineMathSet.setColor(Color.BLUE) // 設置圖形顏色

        val lineEnglishSet = LineDataSet(englishLineArrayEntry, "English") // 設置資料意義
        lineEnglishSet.setColor(Color.GREEN) // 設置圖形顏色

        val lineArrayDataSet = ArrayList<ILineDataSet>()
        lineArrayDataSet.add(lineMathSet)        //看有幾個長條圖，都丟進 list 裡面
        lineArrayDataSet.add(lineEnglishSet)     //看有幾個長條圖，都丟進 list 裡面
        lc_data.data = LineData(lineArrayDataSet)


    }

    fun bubbleChartInit() {

        var mathBubbleArrayEntry = ArrayList<BubbleEntry>()
        val mathScore = arrayListOf(39f, 54f, 69f, 83f, 99f, 53f, 52f, 35f, 93f)

        var englishBubbleArrayEntry = ArrayList<BubbleEntry>()
        val englishScore = arrayListOf(66f, 24f, 45f, 27f, 56f, 84f, 93f, 42f, 9f)

        for (i in 1 until mathScore.size) {
            val bubbleEntry1 =
                BubbleEntry((2 * i + 1).toFloat(), (2 * i + 1).toFloat(), mathScore[i])
            val bubbleEntry2 =
                BubbleEntry((2 * i + 2).toFloat(), (2 * i + 1).toFloat(), englishScore[i])
            mathBubbleArrayEntry.add(bubbleEntry1)
            englishBubbleArrayEntry.add(bubbleEntry2)
        }

        val bubbleMathSet = BubbleDataSet(mathBubbleArrayEntry, "Math") // 設置資料意義
        bubbleMathSet.setColor(Color.BLUE) // 設置圖形顏色

        val bubbleEnglishSet = BubbleDataSet(englishBubbleArrayEntry, "English") // 設置資料意義
        bubbleEnglishSet.setColor(Color.GREEN) // 設置圖形顏色

        val bubbleArrayDataSet = ArrayList<IBubbleDataSet>()
        bubbleArrayDataSet.add(bubbleMathSet)        //看有幾個泡泡圖，都丟進 list 裡面
        bubbleArrayDataSet.add(bubbleEnglishSet)     //看有幾個泡泡圖，都丟進 list 裡面
        bbc_data.data = BubbleData(bubbleArrayDataSet)


    }

    override fun reShowChart(aniimate: String) {
        rv_list.visibility = RecyclerView.GONE
        when (aniimate) {
            "Animate X" -> animateX()
            "Animate Y" -> animateY()
            "Animate XY" -> animateXY()
            "Change Chart" -> {
                bc_data.visibility = BarChart.GONE
                bbc_data.visibility = BarChart.GONE
                lc_data.visibility = BarChart.GONE
                pickerView()
            }
        }
    }

    fun animateX() {
        when (currentChart) {
            "Bar Chart" -> bc_data.animateX(2000)
            "Line Chart" -> lc_data.animateX(2000)
            "Bubble Chart" -> bbc_data.animateX(2000)
        }
    }

    fun animateY() {
        when (currentChart) {
            "Bar Chart" -> bc_data.animateY(3000)
            "Line Chart" -> lc_data.animateY(3000)
            "Bubble Chart" -> bbc_data.animateY(3000)
        }
    }

    fun animateXY() {
        when (currentChart) {
            "Bar Chart" -> bc_data.animateXY(2000, 3000)
            "Line Chart" -> lc_data.animateXY(2000, 3000)
            "Bubble Chart" -> bbc_data.animateXY(2000, 3000)
        }
    }

    fun pickerView() {
        val pvOptions =
            OptionsPickerBuilder(this@MainActivity,
                OnOptionsSelectListener { chooseChart, option2, options3, v -> //返回的分别是三个级别的选中位置

                    when (currentChart) {
                        "Bar Chart" -> bc_data.visibility = BarChart.GONE
                        "Line Chart" -> lc_data.visibility = LineChart.GONE
                        "Bubble Chart" -> bbc_data.visibility = BubbleChart.GONE
                    }

                    when (chooseChart) {
                        0 -> {
                            bc_data.visibility = BarChart.VISIBLE
                            currentChart = "Bar Chart"
                        }
                        1 -> {
                            lc_data.visibility = LineChart.VISIBLE
                            currentChart = "Line Chart"
                        }
                        2 -> {
                            bbc_data.visibility = BubbleChart.VISIBLE
                            currentChart = "Bubble Chart"
                        }
                    }

                }).build<String>()

        pvOptions.setPicker(chartArray)
        pvOptions.show()
    }


}
