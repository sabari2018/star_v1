package fr.istic.mob.starv1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusRoute(@PrimaryKey val route_id:String, val agency_id:String?, val route_short_name:String?,
               val route_long_name:String, val route_desc:String?, val route_type:String?,
               val route_url:String?, val route_color:String?,
               val route_text_color:String?,
               val route_sort_order:String?)