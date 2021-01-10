package fr.istic.mob.starv2.model;

/**
 * Sadou & Issa.
 */

public class Calendar {


    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
    private String end_date;
    private String start_date;

    public Calendar(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday, String startDate, String endDate) {
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.end_date = startDate;
        this.start_date = endDate;
    }
    @Override
    public String toString() {
        return "Calendar{" +
                "monday='" + monday + '\'' +
                ", tuesday='" + tuesday + '\'' +
                ", wednesday='" + wednesday + '\'' +
                ", thursday='" + thursday + '\'' +
                ", friday='" + friday + '\'' +
                ", saturday='" + saturday + '\'' +
                ", sunday='" + sunday + '\'' +
                ", startDate='" + end_date + '\'' +
                ", endDate='" + start_date + '\'' +
                '}';
    }

}
