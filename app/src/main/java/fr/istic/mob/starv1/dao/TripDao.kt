package fr.istic.mob.starv1.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.Calender
import fr.istic.mob.starv1.entity.Trip

interface TripDao {
    @Query("SELECT * FROM trip")
    fun getAll(): List<Calender>

    @Insert
    fun insertAll(vararg trips: Trip)

    @Delete
    fun delete(trip: Trip)
}