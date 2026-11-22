package com.example.cafeassignmentapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

internal class GridRVAdapter(
    private val itemList: List<MenueItem>,
    private val context: Context
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        return itemList.size
    }

    override fun getItem(position: Int): Any? {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.gridview_item, null)
        }

        val courseIV = convertView!!.findViewById<ImageView>(R.id.idIVCourse)
        val courseTV = convertView!!.findViewById<TextView>(R.id.idTVCourse)
        val priceTV = convertView!!.findViewById<TextView>(R.id.idTVPrice)

        val item = itemList[position]
        courseIV.setImageResource(item.imageRes)
        courseTV.text = item.name
        priceTV.text = item.price

        return convertView
    }
}
