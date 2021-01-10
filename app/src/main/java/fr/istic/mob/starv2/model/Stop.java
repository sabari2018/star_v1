package fr.istic.mob.starv2.model;

/**
 * Sadou & Issa.
 */

public class Stop {

    private String stop_id;
    private String name;
    private String description;
    private float latitude;
    private float longitude;
    private String wheelChairBoalding;

    public Stop(String stop_id, String name, String description, float latitude, float longitude, String wheelChairBoalding) {
        this.stop_id = stop_id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.wheelChairBoalding = wheelChairBoalding;
    }


    public String getId() {
        return stop_id;
    }

    public void setId(String id) {
        this.stop_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getWheelChairBoalding() {
        return wheelChairBoalding;
    }

    public void setWheelChairBoalding(String wheelChairBoalding) {
        this.wheelChairBoalding = wheelChairBoalding;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id:'" + stop_id + '\'' +
                ", name:'" + name + '\'' +
                ", description:'" + description + '\'' +
                ", latitude:" + latitude +
                ", longitude:" + longitude +
                ", wheelChairBoalding:'" + wheelChairBoalding + '\'' +
                '}';
    }

}
