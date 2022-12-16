package com.example.myapplication.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.data.db.ShoppingDatabase
import com.example.myapplication.data.repositories.ShoppingRepository

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)


    }
}