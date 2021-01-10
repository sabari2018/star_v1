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
