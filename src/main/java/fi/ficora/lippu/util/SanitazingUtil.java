package fi.ficora.lippu.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.UUID;

/**
 * Utility functions to sanitize input for storing to the
 */
public class SanitazingUtil {

    public static boolean isValidUUID(String string) {
        try {
            UUID uuid = UUID.fromString(string);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Strips all HTML from a given string.
     *
     * @param string String to sanitize.
     * @return Returns string which has all the HTML stripped.
     */
    public static String removeHTML(String string) {
        if (string == null) {
            return null;
        }
        return Jsoup.clean(string, Whitelist.none());
    }

    /**
     * Sanitize input string
     *
     * @param string String to sanitize.
     * @return Returns sanitized input string
     */
    public static String sanitizeInput(String string) {
        if (string == null) {
            return null;
        }
        return removeHTML(string).replaceAll("[\\$\\^:\\(\\)\\n\\r\\{\\}]", "");
    }
}
