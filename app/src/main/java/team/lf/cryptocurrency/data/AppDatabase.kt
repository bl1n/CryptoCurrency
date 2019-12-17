package team.lf.cryptocurrency.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import team.lf.cryptocurrency.coins.data.CoinsDao


@Database(entities = [GeckoCoin::class], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(){

    abstract fun coinsDao(): CoinsDao


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "geckocoins-db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
//                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}