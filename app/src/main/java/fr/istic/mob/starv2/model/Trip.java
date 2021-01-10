package fr.istic.mob.starv2.model;


/**
 * Sadou & Issa.
 */

public class Trip {

    private int tripId;
    private int route_id;
    private int service_id;
    private String trip_headsign;
    private  int direction_id;
    private String block_id;
    private String wheelchair_accessible;

    public Trip(int tripId, int routeId, int serviceId, String headSign, int directionId, String blockId, String wheelchairAccessible) {
        this.tripId = tripId;
        this.route_id = routeId;
        this.service_id = serviceId;
        this.trip_headsign = headSign;
        this.direction_id = directionId;
        this.block_id = blockId;
        this.wheelchair_accessible = wheelchairAccessible;
    }

/*    public Trip(int routeId, int serviceId, String headSign, int directionId, String blockId, String wheelchairAccessible) {
        this.routeId = routeId;
        this.serviceId = serviceId;
        this.headSign = headSign;
        this.directionId = directionId;
        this.blockId = blockId;
        this.wheelchairAccessible = wheelchairAccessible;
    }*/

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getTrip_headsign() {
        return trip_headsign;
    }

    public void setTrip_headsign(String trip_headsign) {
        this.trip_headsign = trip_headsign;
    }

    public int getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(int direction_id) {
        this.direction_id = direction_id;
    }

    public String getBlock_id() {
        return block_id;
    }

    public void setBlock_id(String block_id) {
        this.block_id = block_id;
    }

    public String getWheelchair_accessible() {
        return wheelchair_accessible;
    }

    public void setWheelchair_accessible(String wheelchair_accessible) {
        this.wheelchair_accessible = wheelchair_accessible;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId:" + tripId +
                ", routeId:" + route_id +
                ", serviceId:" + service_id +
                ", headSign:'" + trip_headsign + '\'' +
                ", directionId:" + direction_id +
                ", blockId:" + block_id +
                ", wheelchairAccessible:'" + wheelchair_accessible + '\'' +
                '}';
    }

}
