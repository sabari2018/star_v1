package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.BusRoute
import fr.istic.mob.starv1.entity.Stop

@Dao
interface StopDao {

    @Query("SELECT * FROM stop")
    fun getAll(): List<Stop>

    @Insert
    fun insertAll(vararg stops: Stop)

    @Delete
    fun delete(stop: Stop)
}