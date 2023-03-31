package com.rickyslash.noteroomapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// defining Room Database using SQLite Abstraction (Room Persistence Library)
// define database schema, 'Note::class' is the entity, and 'version' is 'your database version'
@Database(entities = [Note::class], version = 1)
// 'abstract class' is a class that 'can have abstract method'
abstract class NoteRoomDatabase: RoomDatabase() {

    // 'abstract method' is a method that 'is declared', but 'not implemented' (not defined) in a class
    // 'only abstract class' 'can have abstract method'
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile // this means the variable will be manipulated by multiple threads concurrently
        private var INSTANCE: NoteRoomDatabase? = null

        // function to make singleton instance of this class
        @JvmStatic // this annotation will generate static method for 'Java' so it could be accessed directly. It has no relevancy for Kotlin
        fun getDatabase(context: Context): NoteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteRoomDatabase::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as NoteRoomDatabase
        }
    }
}