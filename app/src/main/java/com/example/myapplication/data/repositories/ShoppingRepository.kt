package com.example.myapplication.data.repositories

import com.example.myapplication.data.db.ShoppingDatabase
import com.example.myapplication.data.db.entities.ShoppingItem

//do tej klasy zostanie wrzucony obiekt DB. Tu bedziemy implementować metody bazy danych które zdefiniowaliśmy w ShoppingDao. W tym repo chcemy je przywołać

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    //po wywołaniu metody upsert/delete/getAllShoppingItems w tej klasie zostaje wywołana metoda ze shoppingdatabase dziedzicząca z interfejsu shoppingDao 3 metody upsert/delete/getAllShoppingItems
    suspend fun upsert(item: ShoppingItem) : Unit {

        return db.getShoppingDao().upsert(item)
    }

    suspend fun delete(item: ShoppingItem) : Unit = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()


}