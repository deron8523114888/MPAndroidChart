package com.example.mpandroidchart

import android.content.Context
import android.view.View
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

val list_name = arrayListOf("Animate X", "Animate Y", "Animate XY", "Change Chart")

class ListAdapter(context: Context, val viewConstract: ViewConstract) :
    CommonAdapter<String>(context, R.layout.item_list, list_name) {


    override fun convert(holder: ViewHolder?, t: String?, position: Int) {

        holder?.setText(R.id.tv_chart, list_name[position])

        holder?.itemView?.setOnClickListener(View.OnClickListener {

            viewConstract.reShowChart(list_name[position])

        })

    }

}