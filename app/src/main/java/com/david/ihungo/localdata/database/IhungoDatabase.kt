package com.david.ihungo.localdata.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.ihungo.localdata.database.dao.TaskDao
import com.david.ihungo.model.Task

@Database(entities = [Task::class], version = 5)
abstract class IhungoDatabase: RoomDatabase() {
    abstract fun getRecipeDao(): TaskDao
}












/*
package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.domain.model.Product


@Database(entities = [Product::class], version = 13)
abstract class IhungoDatabase: RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
}

 */
