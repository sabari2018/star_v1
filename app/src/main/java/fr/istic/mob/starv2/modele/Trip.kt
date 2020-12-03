package fr.istic.mob.starv2.modele


data class Trip(val route_id:String?, val service_id:String?, val trip_id:String, val trip_headsign:String?,
           val trip_short_name:String?, val direction_id:String?, val block_id:String?,
           val shape_id:String?, val wheelchair_accessible:String?, val bikes_allowed:String?)