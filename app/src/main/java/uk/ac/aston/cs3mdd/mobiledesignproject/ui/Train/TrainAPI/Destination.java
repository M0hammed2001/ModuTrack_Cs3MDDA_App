package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI;

import java.io.Serializable;

public class Destination implements Serializable {
//    private String destination;
//    // gets the sub class desination where in the crs, locationname, via is located
    private String crs;
    //abbreviation for location name

    private String via;
    // will show via what erea the train will travel


    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    private String locationName;
    //location name is the station e.g "Birmingham New Street"

    public String getCrs() {
        return crs;
    }

    public void setCrs(String crs) {
        this.crs = crs;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getLocationName());
        sb.append(", ");
        sb.append(getCrs());
        sb.append(", ");
        String via = getVia() != null ? getVia() : "";
        sb.append(via);
        return sb.toString();
    }
}
