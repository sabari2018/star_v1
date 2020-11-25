package fr.istic.mob.starv1.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.istic.mob.starv1.entity.BusRoute

@Dao
interface BusRouteDao {

    @Query("SELECT * FROM BusRoute")
    fun getAll(): List<BusRoute>

    @Insert
    fun insertAll(vararg busRoutes: BusRoute)

    @Delete
    fun delete(busRoute: BusRoute)
}