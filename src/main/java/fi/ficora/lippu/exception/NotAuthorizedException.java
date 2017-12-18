/*
 * This work is licenced under EUPL (European Union Public Licence) version 1.2.
 * See LICENSE.txt
 * See https://joinup.ec.europa.eu/sites/default/files/inline-files/EUPL%20v1_2%20EN(1)
 * .txt
 */
package fi.ficora.lippu.exception;

/**
 * This exception is thrown when client access
 * resources it has not authorized to.
 *
 * @author markkuko
 */
public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
