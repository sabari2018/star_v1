package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.BusRoute
import fr.istic.mob.starv1.entity.Calender

@Dao
interface CalenderDao {

    @Query("SELECT * FROM calender")
    fun getAll(): List<Calender>

    @Insert
    fun insertAll(vararg calenders: Calender)

    @Delete
    fun delete(calender: Calender)
}