package fr.istic.mob.starv2.model;


/**
 * Sadou & Issa.
 */
public class BusRoute {

    private String route_id;
    private String route_short_name;
    private String route_long_name;
    private String route_desc;
    private String route_type;
    private String route_color;
    private String route_text_color;

    public BusRoute(String route_id, String shortName, String longName, String description, String type, String color, String textColor) {
        this.route_id = route_id;
        this.route_short_name = shortName;
        this.route_long_name = longName;
        this.route_desc = description;
        this.route_type = type;
        this.route_color = color;
        this.route_text_color = textColor;
    }


    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public String getRoute_color() {
        return route_color;
    }

    public String getRoute_text_color() {
        return route_text_color;
    }

    @Override
    public String toString() {
        return "BusRoute{" +
                "route_id:'" + route_id + '\'' +
                ", shortName:'" + route_short_name + '\'' +
                ", longName:'" + route_long_name + '\'' +
                ", description:'" + route_desc + '\'' +
                ", type:'" + route_type + '\'' +
                ", color:'" + route_color + '\'' +
                ", textColor:'" + route_text_color + '\'' +
                '}';
    }

}
