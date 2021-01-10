package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.StopTime

@Dao
interface StopTimeDao {

    @Query("SELECT * FROM StopTime")
    fun getAll(): MutableList<StopTime>

    @Insert
    fun insert(stopTimes: StopTime)

    @Delete
    fun delete(stopTime: StopTime)
}