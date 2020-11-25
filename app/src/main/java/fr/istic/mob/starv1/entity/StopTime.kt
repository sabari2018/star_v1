package fr.istic.mob.starv1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StopTime(@PrimaryKey val trip_id:String, val arrival_time:String, val departure_time:String,
               val stop_id:String, val stop_sequence:String, val stop_headsign:String,
               val pickup_type:String, val drop_off_type:String, val shape_dist_traveled:String)