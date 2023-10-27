package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI;

import java.io.Serializable;
import java.util.List;

public class TrainService implements Serializable {
    private String operator;
    // who is the opperator e.g "West Midlands Trains"
    private String std;
    //arrival time
    private String etd;
    // if it is on time or not

    private String nrccMessages;
    //any disttrubtion
    private String areServicesAvailable;
    // true or false

    private List<Destination> destination;

    public List<Destination> getDestination() {
        return destination;
    }

    public void setDestination(List<Destination> destination) {
        this.destination = destination;
    }

    public String getNrccMessages() {
        return nrccMessages;
    }

    public void setNrccMessages(String nrccMessages) {
        this.nrccMessages = nrccMessages;
    }

    public String getAreServicesAvailable() {
        return areServicesAvailable;
    }

    public void setAreServicesAvailable(String areServicesAvailable) {
        this.areServicesAvailable = areServicesAvailable;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getStd() {
        return std;
    }

    public void setStd(String std) {
        this.std = std;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }




    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getOperator());
        sb.append(" ");
        String ArivalTime = getEtd() != null ? getEtd() : "No Arival Time";
        sb.append(ArivalTime);
        sb.append(" ");
        sb.append(getDestination().get(0).toString());
        sb.append(" ");
        String nrc = getNrccMessages() != null ? getNrccMessages() : "No Notice Message";
        sb.append(nrc);
        return sb.toString();
    }
}
