package fr.istic.mob.starv1.dao

import androidx.room.*
import fr.istic.mob.starv1.entity.Calender
import fr.istic.mob.starv1.entity.Trip

@Dao
interface TripDao {
    @Query("SELECT * FROM Trip")
    fun getAll(): MutableList<Calender>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(trips: Trip): Long

    @Delete
    fun delete(trip: Trip)
}