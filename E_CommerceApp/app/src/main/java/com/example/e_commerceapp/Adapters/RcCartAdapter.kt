package com.example.e_commerceapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.ProductModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SQLiteHelper


class RcCartAdapter(private val mDataset: MutableList<ProductModel>, private val mContext: Context) :
    RecyclerView.Adapter<RcCartAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Views being defined
        var imgCart: ImageView = itemView.findViewById<View>(R.id.imgCart) as ImageView
        var txtCartProductName: TextView = itemView.findViewById<View>(R.id.txtCartProductName) as TextView
        var txtCartProductPrice: TextView = itemView.findViewById<View>(R.id.txtCartProductPrice) as TextView
        var btnRemove: Button = itemView.findViewById<View>(R.id.btnRemoveInCart) as Button


    }
    override fun getItemCount(): Int {
        //taking item list length
        return mDataset.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //views fulling with list
        holder.imgCart.setImageResource(mDataset[position].cover)
        holder.txtCartProductName.text = mDataset[position].name
        holder.txtCartProductPrice.text = mDataset[position].price.toString()+"$"
        holder.btnRemove.setOnClickListener {
            val db=SQLiteHelper(mContext)
            db.deleteCartData(mDataset[position].id)
        }


    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(
            R.layout.cart_layout, parent, false
        )
        return ViewHolder(view)
    }
}