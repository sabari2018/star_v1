package fr.istic.mob.starv2.model;

/**
 * Sadou & Issa.
 */

public class Stop {

    private String stop_id;
    private String stop_name;
    private String stop_desc;
    private float stop_lat;
    private float stop_lon;
    private String wheelchair_boarding;

    public Stop(String stop_id, String name, String description, float latitude, float longitude, String wheelChairBoalding) {
        this.stop_id = stop_id;
        this.stop_name = name;
        this.stop_desc = description;
        this.stop_lat = latitude;
        this.stop_lon = longitude;
        this.wheelchair_boarding = wheelChairBoalding;
    }


    public String getId() {
        return stop_id;
    }

    public void setId(String id) {
        this.stop_id = id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_desc() {
        return stop_desc;
    }

    public void setStop_desc(String stop_desc) {
        this.stop_desc = stop_desc;
    }

    public float getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(float stop_lat) {
        this.stop_lat = stop_lat;
    }

    public float getStop_lon() {
        return stop_lon;
    }

    public void setStop_lon(float stop_lon) {
        this.stop_lon = stop_lon;
    }

    public String getWheelchair_boarding() {
        return wheelchair_boarding;
    }

    public void setWheelchair_boarding(String wheelchair_boarding) {
        this.wheelchair_boarding = wheelchair_boarding;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id:'" + stop_id + '\'' +
                ", name:'" + stop_name + '\'' +
                ", description:'" + stop_desc + '\'' +
                ", latitude:" + stop_lat +
                ", longitude:" + stop_lon +
                ", wheelChairBoalding:'" + wheelchair_boarding + '\'' +
                '}';
    }

}
