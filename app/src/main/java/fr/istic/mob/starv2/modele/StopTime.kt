package fr.istic.mob.starv2.modele


data class StopTime(val trip_id:String, val arrival_time:String, val departure_time:String,
               val stop_id:String, val stop_sequence:String, val stop_headsign:String,
               val pickup_type:String, val drop_off_type:String, val shape_dist_traveled:String)