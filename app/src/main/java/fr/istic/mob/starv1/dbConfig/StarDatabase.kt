package fr.istic.mob.starv1.dbConfig

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.istic.mob.starv1.dao.*
import fr.istic.mob.starv1.entity.*

@Database(entities = [BusRoute::class, Calender::class, Stop::class, StopTime::class, Trip::class], version = 1)
abstract class StarDatabase: RoomDatabase(){

    abstract fun busRouteDao(): BusRouteDao
    abstract fun calenderDao(): CalenderDao
    abstract fun stopDao(): StopDao
    abstract fun stopTimeDao(): StopTimeDao
    abstract fun tripDao(): TripDao
}