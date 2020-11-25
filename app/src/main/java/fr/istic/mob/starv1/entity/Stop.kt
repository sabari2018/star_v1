package fr.istic.mob.starv1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stop(@PrimaryKey val stop_id:String, val stop_code:String, val stop_name:String,
           val stop_desc:String, val stop_lat:String, val stop_lon:String,
           val zone_id:String, val stop_url:String, val location_type:String,
           val parent_station:String, val stop_timezone:String, val wheelchair_boarding:String)