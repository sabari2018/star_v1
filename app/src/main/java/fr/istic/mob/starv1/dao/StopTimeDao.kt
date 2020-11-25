package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StopTimeDao {

    @Query("SELECT * FROM stoptime")
    fun getAll(): List<StopTimeDao>

    @Insert
    fun insertAll(vararg stopTimes: StopTimeDao)

    @Delete
    fun delete(stopTime: StopTimeDao)
}