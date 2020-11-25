package fr.istic.mob.starv1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Calender(@PrimaryKey val service_id:String, val monday:String, val tuesday:String,
               val wednesday:String, val thursday:String,
               val friday:String, val saturday:String, val sunday:String,
               val start_date:String, val end_date:String)