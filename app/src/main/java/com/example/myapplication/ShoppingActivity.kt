package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


    var list = (1..10).toList()
    var sum = list.customSum{ it % 2 == 1}


    println("The sum is $sum")



    }
    fun List<Int>.customSum(sumFunction: (Int) -> (Boolean)) : Int {
        var sum = 0
        for (item in this) {
            if (sumFunction(item)) {
                sum += item
            }
        }
        return sum
    }
}