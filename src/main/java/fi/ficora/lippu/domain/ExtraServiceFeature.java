/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.domain;

public class ExtraServiceFeature {

    private String title;
    private String description;
    private String extraServiceReservationId;
    private Fare fare;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtraServiceReservationId() {
        return extraServiceReservationId;
    }

    public void setExtraServiceReservationId(String extraServiceReservationId) {
        this.extraServiceReservationId = extraServiceReservationId;
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }
}
