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
