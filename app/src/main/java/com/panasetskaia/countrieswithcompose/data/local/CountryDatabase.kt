package com.panasetskaia.countrieswithcompose.data.local

import android.content.Context
import androidx.room.*

@Database(
    entities = [CountryDBModel::class], version = 1, exportSchema = true
)
@TypeConverters(CountryConverters::class)
abstract class CountryDatabase : RoomDatabase() {
    companion object {

        private var db: CountryDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): CountryDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        CountryDatabase::class.java,
                        DB_NAME
                    ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun countryDao(): CountryDao
}

