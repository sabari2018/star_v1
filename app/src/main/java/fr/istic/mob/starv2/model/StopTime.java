package fr.istic.mob.starv2.model;


/**
 * Sadou & Issa.
 */

public class StopTime {

    private int trip_id;
    private String arrival_time;
    private String departure_time;
    private int stop_id;
    private String stop_sequence;

    public StopTime(int tripId, String arrivalTime, String departureTme, int stopId, String stopSequence) {
        this.trip_id = tripId;
        this.arrival_time = arrivalTime;
        this.departure_time = departureTme;
        this.stop_id = stopId;
        stop_sequence = stopSequence;
    }

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public int getStop_id() {
        return stop_id;
    }

    public void setStop_id(int stop_id) {
        this.stop_id = stop_id;
    }

    public String getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(String stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    @Override
    public String toString() {
        return "StopTime{" +
                "tripId:" + trip_id +
                ", arrivalTime:'" + arrival_time + '\'' +
                ", departureTme:'" + departure_time + '\'' +
                ", stopId:" + stop_id +
                ", StopSequence:'" + stop_sequence + '\'' +
                '}';
    }

    public String sql() {
        return "("+ trip_id +",'"+ arrival_time +"','"+ departure_time +"',"+ stop_id +",'"+ stop_sequence +"')";
    }
}
