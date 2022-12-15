package com.example.myapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.db.entities.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {

    //abstakcyjna metoda która dziedziczy funkcje z shoppingDao
    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()


        // v - przedrostek operator sprawia, że metoda wywoływana jest za kazdym razem przy utworzeniu instancji  klasy ShoppingDatabase
        // (jest abstrakcyjna, więc pewnie będzie gdzieś dziedziczona przez jakąć klasę, dzięki czemu od razu wywoła się metoda invoke
        // i zostanie sprawdzone, czy już istnieje baza danych. jesli bedzie instancja (ShoppingDatabase) dalej null, to zostanie utworzona DB
        // (zaraz po ponownym sprawdzeniu, czy nie jest null) funkcją createDatabase (jest poniżej) używając ROOM i metody databaseBuilder,
        // której przekazujemy potrzebne parametry takie jak kontekst(dalej nie wiem co to, ale chyba nie do konca musze xd),
        // nazwę klasy(???? czy na pewno nazwę???? )
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").build()

    }


}