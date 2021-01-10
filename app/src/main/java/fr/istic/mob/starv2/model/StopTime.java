package fr.istic.mob.starv2.model;


/**
 * Sadou & Issa.
 */

public class StopTime {

    private int tripId;
    private String arrivalTime;
    private String departureTme;
    private int stopId;
    private String StopSequence;

    public StopTime(int tripId, String arrivalTime, String departureTme, int stopId, String stopSequence) {
        this.tripId = tripId;
        this.arrivalTime = arrivalTime;
        this.departureTme = departureTme;
        this.stopId = stopId;
        StopSequence = stopSequence;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTme() {
        return departureTme;
    }

    public void setDepartureTme(String departureTme) {
        this.departureTme = departureTme;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public String getStopSequence() {
        return StopSequence;
    }

    public void setStopSequence(String stopSequence) {
        StopSequence = stopSequence;
    }

    @Override
    public String toString() {
        return "StopTime{" +
                "tripId:" + tripId +
                ", arrivalTime:'" + arrivalTime + '\'' +
                ", departureTme:'" + departureTme + '\'' +
                ", stopId:" + stopId +
                ", StopSequence:'" + StopSequence + '\'' +
                '}';
    }

    public String sql() {
        return "("+tripId+",'"+arrivalTime+"','"+departureTme+"',"+stopId+",'"+StopSequence+"')";
    }
}
