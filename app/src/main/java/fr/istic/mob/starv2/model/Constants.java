package fr.istic.mob.starv2.model;

/**
 * Sadou & Issa.
 */

public class Constants implements StarContract {
    public static final String CREATE_BUS_ROUTE_TABLE = "CREATE TABLE IF NOT EXISTS "+ BusRoutes.CONTENT_PATH +
            "("+BusRoutes.BusRouteColumns.SHORT_NAME + " TEXT NOT NULL PRIMARY KEY , "+
            BusRoutes.BusRouteColumns.LONG_NAME+" TEXT, "+
            BusRoutes.BusRouteColumns.DESCRIPTION+" TEXT, "+
            BusRoutes.BusRouteColumns.TYPE+" TEXT,"+
            BusRoutes.BusRouteColumns.COLOR+" TEXT, "+
            BusRoutes.BusRouteColumns.TEXT_COLOR+" TEXT );";

    public static final String CREATE_TRIPS_TABLE = "CREATE TABLE IF NOT EXISTS "+ Trips.CONTENT_PATH +
            "("+Trips.TripColumns.ROUTE_ID+" INTEGER  NOT NULL PRIMARY KEY,"+
            Trips.TripColumns.SERVICE_ID+" INTEGER,"+
            Trips.TripColumns.HEADSIGN+" TEXT,"+
            Trips.TripColumns.DIRECTION_ID+" INTEGER,"+
            Trips.TripColumns.BLOCK_ID+" TEXT,"+
            Trips.TripColumns.WHEELCHAIR_ACCESSIBLE+" TEXT );";


    public static final String CREATE_STOPS_TABLE = "CREATE TABLE IF NOT EXISTS "+ Stops.CONTENT_PATH +
            "("+Stops.StopColumns.NAME + " TEXT NOT NULL PRIMARY KEY, "+
            Stops.StopColumns.DESCRIPTION+" TEXT,"+
            Stops.StopColumns.LATITUDE+" INTEGER, "+
            Stops.StopColumns.LONGITUDE+" INTEGER,"+
            Stops.StopColumns.WHEELCHAIR_BOARDING+" TEXT );";


    public static final String CREATE_STOP_TIMES_TABLE = "CREATE TABLE IF NOT EXISTS "+ StopTimes.CONTENT_PATH +
            "("+ StopTimes.StopTimeColumns.TRIP_ID + " INTEGER NOT NULL PRIMARY KEY,"+
            StopTimes.StopTimeColumns.ARRIVAL_TIME+" TEXT,"+
            StopTimes.StopTimeColumns.DEPARTURE_TIME+" TEXT,"+
            StopTimes.StopTimeColumns.STOP_ID+" INTEGER,"+
            StopTimes.StopTimeColumns.STOP_SEQUENCE+" TEXT );";


    public static final String CREATE_CALENDAR_TABLE = "CREATE TABLE IF NOT EXISTS "+ Calendar.CONTENT_PATH +
            "("+ Calendar.CalendarColumns.MONDAY + " TEXT , "+
            Calendar.CalendarColumns.TUESDAY+" TEXT,"+
            Calendar.CalendarColumns.WEDNESDAY+" TEXT,"+
            Calendar.CalendarColumns.THURSDAY+" TEXT,"+
            Calendar.CalendarColumns.FRIDAY+" TEXT," +
            Calendar.CalendarColumns.SATURDAY+" TEXT,"+
            Calendar.CalendarColumns.SUNDAY+" TEXT,"+
            Calendar.CalendarColumns.START_DATE+" TEXT,"+
            Calendar.CalendarColumns.END_DATE+" TEXT"+
            " );";
}
