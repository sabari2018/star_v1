package fr.istic.mob.starv2.model;


/**
 * Sadou & Issa.
 */

public class Trip {

    private int tripId;
    private int routeId;
    private int serviceId;
    private String headSign;
    private  int directionId;
    private String blockId;
    private String wheelchairAccessible;

    public Trip(int tripId, int routeId, int serviceId, String headSign, int directionId, String blockId, String wheelchairAccessible) {
        this.tripId = tripId;
        this.routeId = routeId;
        this.serviceId = serviceId;
        this.headSign = headSign;
        this.directionId = directionId;
        this.blockId = blockId;
        this.wheelchairAccessible = wheelchairAccessible;
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

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getHeadSign() {
        return headSign;
    }

    public void setHeadSign(String headSign) {
        this.headSign = headSign;
    }

    public int getDirectionId() {
        return directionId;
    }

    public void setDirectionId(int directionId) {
        this.directionId = directionId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public void setWheelchairAccessible(String wheelchairAccessible) {
        this.wheelchairAccessible = wheelchairAccessible;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId:" + tripId +
                ", routeId:" + routeId +
                ", serviceId:" + serviceId +
                ", headSign:'" + headSign + '\'' +
                ", directionId:" + directionId +
                ", blockId:" + blockId +
                ", wheelchairAccessible:'" + wheelchairAccessible + '\'' +
                '}';
    }

}
