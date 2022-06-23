package com.example.e_commerceapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.Adapters.RcCartAdapter
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_layout.*

class Activity_Cart : AppCompatActivity() {

    //List price first 0
    var num=0
    //Cart List
    lateinit var cartList :MutableList<ProductModel>
    //Db
    var db= SQLiteHelper(this)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        //RcCart settings
        rcCartProduct.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL, false)

        //Cartlist fulling
        cartList= db.readCartDataList()

        //RcCart adapter connecting
        rcCartProduct.adapter= RcCartAdapter(cartList,this)
        cartList.forEach{
            num+=it.price
        }

        //All item price in textView
        txtAllProductPrice.text= "$num$"

       btnRefresh.setOnClickListener {
           num=0
           cartList= db.readCartDataList()
           rcCartProduct.adapter= RcCartAdapter(cartList,this)
           cartList.forEach{
               num+=it.price
           }
           txtAllProductPrice.text= "$num$"
       }
        }


}