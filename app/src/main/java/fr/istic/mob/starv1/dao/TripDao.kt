package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.Calender
import fr.istic.mob.starv1.entity.Trip

@Dao
interface TripDao {
    @Query("SELECT * FROM Trip")
    fun getAll(): MutableList<Calender>

    @Insert
    fun insertAll(vararg trips: Trip)

    @Delete
    fun delete(trip: Trip)
}