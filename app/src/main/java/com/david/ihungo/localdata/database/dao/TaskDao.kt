package com.david.ihungo.localdata.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.ihungo.model.Task

@Dao
interface TaskDao {
    /*
    @Query("SELECT * FROM TaskTable ORDER BY articleDescription ASC")
    suspend fun getAll():List<Task>
    */

    @Query("SELECT * FROM TaskTable WHERE parent=0 ORDER BY id DESC")
    suspend fun getAll():List<Task>

    @Query("SELECT * FROM TaskTable WHERE id = :taskId ")
    suspend fun getOneTask(taskId:Int):Task

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Task>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(recipes:Task)

    @Query("DELETE FROM TaskTable")
    suspend fun deleteAll()

    @Query("DELETE FROM TaskTable WHERE id = :taskId")
    suspend fun deleteTask(taskId:Int)

    @Query("UPDATE TaskTable SET done = 1 WHERE id = :taskId")
    suspend fun markAsDone(taskId:Int)

    @Query("SELECT * FROM TaskTable WHERE parent=:parent ORDER BY id DESC")
    suspend fun getDetails(parent:Int):List<Task>


    /*
    @Query("SELECT * FROM TaskTable WHERE parent=:parent and done=1 ORDER BY id DESC")
    suspend fun getSubtasksWithStateDone(parent:Int):List<Task>
*/



    /*
    @Query("SELECT * FROM Article WHERE local_id = :local_id")
    suspend fun getById(local_id: Int): Task

    @Query("SELECT * FROM Article WHERE articleDescription LIKE :recipeHash ORDER BY articleDescription ASC")
    fun getFiltered(recipeHash: String): List<Task>

    @Query("UPDATE Article SET consumedQuantity = :consumibleNewQuantity WHERE local_id = :localIdArticle")
    suspend fun updateConsumedQuantity(localIdArticle:Int, consumibleNewQuantity:Int):Int
    */
}