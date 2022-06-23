package com.example.e_commerceapp.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.ProductModel
import com.example.e_commerceapp.ProductView
import com.example.e_commerceapp.R

class RcProductsAdapter(private val mDataset: MutableList<ProductModel>, private val mContext: Context) :

    RecyclerView.Adapter<RcProductsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Views being defined
        var imgViewProduct: ImageView = itemView.findViewById<View>(R.id.imgProdutc) as ImageView
        var txtProductName: TextView = itemView.findViewById<View>(R.id.txtProductName) as TextView
        var txtProductPrice: TextView = itemView.findViewById<View>(R.id.txtPrice) as TextView

    }
    override fun getItemCount(): Int {
        //List size taking
        return mDataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //All views fulling with Product List
        holder.imgViewProduct.setImageResource(mDataset[position].cover)
        holder.txtProductName.text = mDataset[position].name
        holder.txtProductPrice.text = mDataset[position].price.toString() + "$"

        //if any items selected
        holder.imgViewProduct.setOnClickListener {
            //intent creating
            var intent= Intent(mContext, ProductView::class.java)
            //id putting in intent
            intent.putExtra("id",mDataset[position].id)
            //activity starting
            mContext.startActivity(intent)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(
            R.layout.products_layout, parent, false
        )
        return ViewHolder(view)
    }


}