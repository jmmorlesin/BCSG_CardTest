package com.bcsg.common;

import java.text.ParseException;
import java.util.Date;

/**
 * Common methods to format different types
 * @author Jose M. Morlesin
 */
public class FormatUtils {

    /**
     * Prepare the format of the date for the user
     * @param date
     * @return
     */
    public static String getFormatDate(Date date) {
        String dateString;
        if (date != null) {
            dateString = Constants.DATE_FORMAT.format(date);
            dateString = capitalizeFirstLetter(dateString);
        } else {
            dateString = "";
        }
        return dateString;
    }

    /**
     * Build a Date from a string with the defined correct format
     * @param dateString
     * @return
     * @throws java.text.ParseException
     */
    public static Date getDateFromString(String dateString) throws ParseException {
        return Constants.DATE_FORMAT.parse(dateString);
    }

    /**
     * Replace card number after the first 4 digits with 'x', e.g. 5601-2345-3446-5678 becomes 5601-xxxx-xxxx-xxxx
     * @param cardNumber
     * @return
     */
    public static String getCardNumberFormat(String cardNumber) {
        StringBuilder cardNumberPresentation = new StringBuilder();
        if (cardNumber != null) {
            if (cardNumber.length()>=4) {
                cardNumberPresentation.append(cardNumber.substring(0,4));
                cardNumberPresentation.append(cardNumber.substring(4).replaceAll("\\d", "x"));
            } else {
                cardNumberPresentation.append(cardNumber);
            }
        }
        return cardNumberPresentation.toString();
    }

    /**
     * Capitalize the first letter, if the length is more than 1 character
     * @param input
     * @return
     */
    private static String capitalizeFirstLetter(String input) {
        String output;
        if (input != null && input.length()>=1) {
            output = input.substring(0, 1).toUpperCase() + input.substring(1);
        } else {
            output = input;
        }
        return output;
    }
}