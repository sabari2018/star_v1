package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.Calender

@Dao
interface CalenderDao {

    @Query("SELECT * FROM Calender")
    fun getAll(): MutableList<Calender>

    @Insert
    fun insert(calender: Calender)

    @Delete
    fun delete(calender: Calender)
}