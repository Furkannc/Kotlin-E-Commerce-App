package com.example.e_commerceapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteHelper(val context: Context) : SQLiteOpenHelper(context,SQLiteHelper.DATABASE_NAME,null,SQLiteHelper.DATABASE_VERSION) {
    private val TABLE_PRODUCT="Product"
    private val TABLE_CART="Cart"
    private val COL_ID = "id"
    private val COL_COVER = "cover"
    private val COL_NAME = "name"
    private val COL_PRICE = "price"
    private val COL_CATEGORY = "category"
    private val COL_ABOUT = "about"
    companion object {
        private val DATABASE_NAME = "ProductDB"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createProductTable = "CREATE TABLE IF NOT EXISTS $TABLE_PRODUCT (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_COVER INTEGER," +
                "$COL_NAME VARCHAR(256),"+
                "$COL_PRICE INTEGER,"+
                "$COL_CATEGORY VARCHAR(256),"+
                "$COL_ABOUT VARCHAR(256))"

       val createCartTable = "CREATE TABLE IF NOT EXISTS $TABLE_CART (" +
                "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_COVER INTEGER," +
                "$COL_NAME VARCHAR(256),"+
                "$COL_PRICE INTEGER,"+
                "$COL_CATEGORY VARCHAR(256),"+
                "$COL_ABOUT VARCHAR(256))"

        db?.execSQL(createProductTable)
        db?.execSQL(createCartTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertProduct(product: ProductModel){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_COVER , product.cover)
        contentValues.put(COL_NAME, product.name)
        contentValues.put(COL_PRICE, product.price)
        contentValues.put(COL_CATEGORY, product.category)
        contentValues.put(COL_ABOUT, product.about)

        val result = db.insert(TABLE_PRODUCT,null,contentValues)

        Toast.makeText(context,if(result != -1L) "Registration Successful!" else "Registration failed.", Toast.LENGTH_SHORT).show()
    }

    fun insertCart(product: ProductModel){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_COVER , product.cover)
        contentValues.put(COL_NAME, product.name)
        contentValues.put(COL_PRICE, product.price)
        contentValues.put(COL_CATEGORY, product.category)
        contentValues.put(COL_ABOUT, product.about)

        val result = db.insert(TABLE_CART,null,contentValues)

        Toast.makeText(context,if(result != -1L) "Added to cart" else "Not added to cart", Toast.LENGTH_SHORT).show()
    }

    fun readProductDataSelectedList(category: String):MutableList<ProductModel>{
        val productList = mutableListOf<ProductModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PRODUCT WHERE $COL_CATEGORY = ?"
        val result = sqliteDB.rawQuery(query, arrayOf(category))
        if(result.moveToFirst()){
            do {
                var product = ProductModel(result.getInt(result.getColumnIndex(COL_ID).toInt()),//id
                    result.getInt(result.getColumnIndex(COL_COVER).toInt()),//cover
                    result.getString(result.getColumnIndex(COL_NAME).toInt()).toString(),//name
                    result.getInt(result.getColumnIndex(COL_PRICE).toInt()),//price
                result.getString(result.getColumnIndex(COL_CATEGORY).toInt()).toString(),//category
                result.getString(result.getColumnIndex(COL_ABOUT).toInt()).toString()//about
                )
                productList.add(product)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return productList
    }
    fun readProductDataAllList():MutableList<ProductModel>{
        val productList = mutableListOf<ProductModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PRODUCT"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var product = ProductModel(result.getInt(result.getColumnIndex(COL_ID).toInt()),//id
                    result.getInt(result.getColumnIndex(COL_COVER).toInt()),//cover
                    result.getString(result.getColumnIndex(COL_NAME).toInt()).toString(),//name
                    result.getInt(result.getColumnIndex(COL_PRICE).toInt()),//price
                result.getString(result.getColumnIndex(COL_CATEGORY).toInt()).toString(),//category
                result.getString(result.getColumnIndex(COL_ABOUT).toInt()).toString()//about
                )
                productList.add(product)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return productList
    }
    fun readCartDataList():MutableList<ProductModel>{
        val productList = mutableListOf<ProductModel>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_CART"
        val result = sqliteDB.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var product = ProductModel(result.getInt(result.getColumnIndex(COL_ID).toInt()),//id
                    result.getInt(result.getColumnIndex(COL_COVER).toInt()),//cover
                    result.getString(result.getColumnIndex(COL_NAME).toInt()).toString(),//name
                    result.getInt(result.getColumnIndex(COL_PRICE).toInt()),//price
                    result.getString(result.getColumnIndex(COL_CATEGORY).toInt()).toString(),//category
                    result.getString(result.getColumnIndex(COL_ABOUT).toInt()).toString()//about
                )
                productList.add(product)
            }while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return productList
    }
    fun readProductData(id:Int): ProductModel {
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_PRODUCT WHERE $COL_ID = ?"
        val result = sqliteDB.rawQuery(query, arrayOf(id.toString()))
        result?.moveToFirst()

        var product = ProductModel(result.getInt(result.getColumnIndex(COL_ID).toInt()),//id
            result.getInt(result.getColumnIndex(COL_COVER).toInt()),//cover
            result.getString(result.getColumnIndex(COL_NAME).toInt()).toString(),//name
            result.getInt(result.getColumnIndex(COL_PRICE).toInt()),//price
            result.getString(result.getColumnIndex(COL_CATEGORY).toInt()).toString(),//category
            result.getString(result.getColumnIndex(COL_ABOUT).toInt()).toString()//about
                )

        result.close()
        sqliteDB.close()
        return product
    }
 fun deleteCartData(id:Int){
        val sqliteDB = this.writableDatabase

        val i = sqliteDB.delete(TABLE_CART,"id = ?", arrayOf(id.toString()))
        Toast.makeText(context,if(i!=-1)"Item deleted" else "item not deleted",Toast.LENGTH_SHORT).show()
        sqliteDB.close()
    }

}