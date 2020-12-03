package fr.istic.mob.starv2.modele

data class BusRoute( val route_id:String, val agency_id:String?, val route_short_name:String?,
               val route_long_name:String, val route_desc:String?, val route_type:String?,
               val route_url:String?, val route_color:String?,
               val route_text_color:String?,
               val route_sort_order:String?)