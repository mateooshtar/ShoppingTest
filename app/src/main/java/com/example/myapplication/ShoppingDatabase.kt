package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.time.Instant

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()


        // v - przedrostek operator sprawia, że metoda wywoływana jest za kazdym razem przy utworzeniu instancji  klasy ShoppingDatabase
        // (jest abstrakcyjna, więc pewnie będzie gdzieś dziedziczona przez jakąć klasę, dzięki czemu od razu wywoła się metoda invoke
        // i zostanie sprawdzone, czy już istnieje baza danych. jesli bedzie instancja (ShoppingDatabase) dalej null, to zostanie utworzona DB
        // (zaraz po ponownym sprawdzeniu, czy nie jest null) funkcją createDatabase (jest poniżej) używając ROOM i metody databaseBuilder, której przekazujemy
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").build()

    }


}