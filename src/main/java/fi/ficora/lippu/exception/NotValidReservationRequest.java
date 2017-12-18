/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.exception;

/**
 * This exception is thrown when request
 * reservation validation fails.
 *
 * @author markkuko
 */
public class NotValidReservationRequest extends Exception {
    public NotValidReservationRequest(String message) {
        super(message);
    }
}
