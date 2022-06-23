package com.example.e_commerceapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R

class RcDiscountAdapter(private val mDataset: MutableList<Int>, private val mContext: Context) :
    RecyclerView.Adapter<RcDiscountAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Views being defined
        var imgViewDiscount: ImageView = itemView.findViewById<View>(R.id.imgViewDiscount) as ImageView
    }
    override fun getItemCount(): Int {
        //Item list length taking
        return mDataset.size
    }
    //Like Banner
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //views fulling with item list
        holder.imgViewDiscount.setImageResource(mDataset[position])
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(
            R.layout.discount_layout, parent, false
        )
        return ViewHolder(view)
    }
}