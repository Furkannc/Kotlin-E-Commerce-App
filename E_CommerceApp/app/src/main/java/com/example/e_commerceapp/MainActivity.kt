package com.example.e_commerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerceapp.Adapters.RcDiscountAdapter
import com.example.e_commerceapp.Adapters.RcProductsAdapter
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //Lists and db
    lateinit var discountList :MutableList<Int>
    lateinit var productList :MutableList<ProductModel>
    var db= SQLiteHelper(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Rectlerview adapter settings
        rcViewDiscounts.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false)
        discountList= mutableListOf(R.drawable.discount, R.drawable.discount2)
        rcViewDiscounts.adapter= RcDiscountAdapter(discountList,this)

        //Prodctlist Data
        productList=db.readProductDataAllList()

        //Database inserting
        if(productList.isEmpty()){
            productList= mutableListOf( ProductModel(1,
                R.drawable.applewatch,"Appple Watch",850,"watch","Apple smart Watch"),
                ProductModel(2, R.drawable.casio,"Casio Watch",250,"watch","casio old Watch"),
                ProductModel(3,
                    R.drawable.xiaomiband,"xiaomi mi band",150,"watch","xiaomi smart band"),
                ProductModel(4,
                    R.drawable.rolex,"Rolex Rich watch",2500,"watch","rolex watch for rich"),
                ProductModel(5,
                    R.drawable.tshirtblack,"Tshirt black",23,"tshirt","Black tshirt for men"),
                ProductModel(6,
                    R.drawable.tshirtblue,"Tshirt blue",29,"tshirt","Blue tshirt for men"),
                ProductModel(7, R.drawable.tshirtred,"Tshirt red",34,"tshirt","Red tshirt"),
                ProductModel(8,
                    R.drawable.tshirtwomen,"Tshirt for women",24,"tshirt","Blue tshirt for women"),
                ProductModel(9, R.drawable.pumashoe,"Puma shoe",67,"shoe","Puma shoe for men"),
                ProductModel(10,
                    R.drawable.adidasblack,"Adidas shoe",87,"shoe","Adidas shoe for men"),
                ProductModel(11,
                    R.drawable.convers,"Convers shoe",59,"shoe","Convers shoe for women"),
                ProductModel(12, R.drawable.nikeblack,"Nike shoe",64,"shoe","Nike shoe for men"),
                ProductModel(13,
                    R.drawable.dresscaro,"Dress caro",78,"dress","Dress caro for women"),
                ProductModel(14,
                    R.drawable.dressjersey,"Dress Jersey",98,"dress","Dress jersey for women"),
                ProductModel(15,
                    R.drawable.dressnavyblue,"Dress NavyBlue",108,"dress","Dress NavyBlue for women"),
                ProductModel(16,
                    R.drawable.dressspink,"Dress Pink",78,"dress","Dress Pink for women"),
                ProductModel(17, R.drawable.pcasus,"Asus pc",708,"pc","Asus pc"),
                ProductModel(18, R.drawable.pcdell,"Dell pc",601,"pc","Dell pc"),
                ProductModel(19, R.drawable.pclenovo,"Lenovo pc",910,"pc","Lenovo pc"),
                ProductModel(20, R.drawable.pcmacbook,"Apple pc",1200,"pc","Apple macbook air"),
                ProductModel(21, R.drawable.phonehiking,"Hiking phone",600,"phone","Hiking phone"),
                ProductModel(22,
                    R.drawable.phoneiphone,"Iphone phone",1000,"phone","Apple iphone "),
                ProductModel(23,
                    R.drawable.phonesamsung,"Samsung phone",900,"phone","Samsung mobile "),
                ProductModel(24, R.drawable.phonexiaomi,"Xiaomi phone",400,"phone","xiaomi redmi"),
                ProductModel(25, R.drawable.tvarcelik,"Arcelik tv",4000,"tv","Arcelik Tv"),
                ProductModel(26, R.drawable.tvsamsung,"Samsung tv",5000,"tv","Samsung smart Tv"),
                ProductModel(27, R.drawable.tvsony,"Sony tv",6000,"tv","Sony smart Tv"),
                ProductModel(27, R.drawable.tvvestel,"Vestel tv",3000,"tv","Vestel smart Tv"),
            )
           for(i in productList)
            db.insertProduct(i)


            //RecylerView for products data
            productList=db.readProductDataAllList()

        }

        //RecylerView adapter setting for products data
        rcViewProducts.layoutManager=GridLayoutManager(this,2)

        rcViewProducts.adapter= RcProductsAdapter(productList,this)


        //toggle button settings

        toggleButtonGroup.addOnButtonCheckedListener{ _: MaterialButtonToggleGroup, checkedId: Int, isChecked: Boolean ->
            if(isChecked){
                when(checkedId){
                    R.id.btnSeeDress -> productList=db.readProductDataSelectedList("dress")
                    R.id.btnSeePc -> productList=db.readProductDataSelectedList("pc")
                    R.id.btnSeeShoe -> productList=db.readProductDataSelectedList("shoe")
                    R.id.btnSeeTv -> productList=db.readProductDataSelectedList("tv")
                    R.id.btnSeeWatch -> productList=db.readProductDataSelectedList("watch")
                    R.id.btnSeePhone -> productList=db.readProductDataSelectedList("phone")
                    R.id.btnSeeTshirt -> productList=db.readProductDataSelectedList("tshirt")
                }

                rcViewProducts.adapter= RcProductsAdapter(productList,this)
            }
        }

        //all product listing
        btnSeeAll.setOnClickListener {
            productList=db.readProductDataAllList()
            rcViewProducts.adapter= RcProductsAdapter(productList,this)
        }

        //go to cart
        btnGoCart.setOnClickListener {
            val intent=Intent(this, Activity_Cart::class.java)
            startActivity(intent)
        }
    }

}