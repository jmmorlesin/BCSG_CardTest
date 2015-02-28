package com.bcsg.helper;

import com.bcsg.common.Constants;

import java.text.ParseException;

/**
 * Helper for the Card class validation
 * @author Jose M. Morlesin
 */
public class CardHelper {

    //About the different format allowed in
    private static final int MIN_LENGTH_CARD_NUMBER = 4;

    /**
     * Bank name validation. It must be informed
     * @param bankName
     * @return
     */
    public static boolean isBankNameValid(String bankName) {
        return bankName != null && !bankName.trim().isEmpty();
    }

    /**
     * Consideration of valid Card numbers, all of them with MIN_LENGTH_CARD_NUMBER or more characters.
     * Accepts numbers, dashes and spaces
     * Different formats accepted: xxxx-xxxx-xxxx-xxxx, xxxxxxxxxxxxxxxx, xxxx xxxx xxxx xxx
     * @param cardNumber
     * @return
     */
    public static boolean isCardNumberValid(String cardNumber) {
        boolean isValid = true;
        if (cardNumber == null || cardNumber.trim().isEmpty() || cardNumber.trim().length()<MIN_LENGTH_CARD_NUMBER) {
            isValid = false;
        } else {
            String newCardNumber = cardNumber.replaceAll("-", "").replaceAll(" ", "");
            String regexOnlyNumbers = "\\d+";
            isValid = newCardNumber.matches(regexOnlyNumbers);
        }
        return isValid;
    }

    /**
     * Expiry date validation. Must be a correct format date
     * @param expiryDate
     * @return
     */
    public static boolean isExpiryDateValid(String expiryDate) {
        boolean isValid = false;
        try {
            if (expiryDate != null) {
                Constants.DATE_FORMAT.parse(expiryDate);
                isValid = true;
            }
        } catch (ParseException e) {
        }
        return isValid;
    }
}
