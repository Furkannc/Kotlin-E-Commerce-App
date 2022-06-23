package com.example.e_commerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.e_commerceapp.ProductModel
import com.example.e_commerceapp.R
import com.example.e_commerceapp.SQLiteHelper
import kotlinx.android.synthetic.main.activity_product_view.*

class ProductView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_view)
        //Db connecting
        var db= SQLiteHelper(this)

        //Selected id taking
        val id = intent.getIntExtra("id",-1)

        //Selected product fetching in database
        val product=db.readProductData(id)

        //All views fulling
        imgProductInView.setImageResource(product.cover)
        txtProductAboutInView.text=product.about
        txtProductNameInView.text=product.name
        txtProductPriceInView.text=product.price.toString()+"$"

        //Add to Cart button / inserting to Cart Table in databasew
        btnProductAddToCart.setOnClickListener {
            val item= ProductModel(product.id,product.cover,product.name, product.price,product.category,product.about)
            db.insertCart(item)
        }

    }
}