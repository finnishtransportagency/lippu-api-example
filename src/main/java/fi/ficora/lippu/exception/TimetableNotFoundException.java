/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.exception;

/**
 * This exception is thrown when timetable
 * was not found for a product.
 *
 * @author markkuko
 */
public class TimetableNotFoundException extends Exception {
    public TimetableNotFoundException(String message) {
        super(message);
    }
}
