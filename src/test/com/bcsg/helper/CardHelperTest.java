package test.com.bcsg.helper;

import com.bcsg.helper.CardHelper;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * JUnit Test case
 * @author Jose M. Morlesin
 */
public class CardHelperTest {

    @Test
    public void nameValidationCorrect() throws Exception {
        String bank = "Bank 1";
        assertTrue(CardHelper.isBankNameValid(bank));
    }

    @Test
    public void nameValidationEmpty() throws Exception {
        String bank = "";
        assertFalse(CardHelper.isBankNameValid(bank));
    }

    @Test
    public void nameValidationSpaces() throws Exception {
        String bank = "   ";
        assertFalse(CardHelper.isBankNameValid(bank));
    }

    @Test
    public void nameValidationNull() throws Exception {
        String bank = null;
        assertFalse(CardHelper.isBankNameValid(bank));
    }

    @Test
    public void cardNumberValidationCorrectFormatDashes() throws Exception {
        String cardNumber = "1111-1111-1111-1111";
        assertTrue(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationCorrectFormatDashesShort() throws Exception {
        String cardNumber = "1111-1111-1111-11";
        assertTrue(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationCorrectFormatSpaces() throws Exception {
        String cardNumber = "1111 1111 1111 1111";
        assertTrue(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationCorrectFormatWithoutSpaces() throws Exception {
        String cardNumber = "1111111111111111";
        assertTrue(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationLetters() throws Exception {
        String cardNumber = "11a1-1111-1111-1111";
        assertFalse(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationShort() throws Exception {
        String cardNumber = "11";
        assertFalse(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationEmpty() throws Exception {
        String cardNumber = "";
        assertFalse(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationSpaces() throws Exception {
        String cardNumber = "   ";
        assertFalse(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void cardNumberValidationNull() throws Exception {
        String cardNumber = null;
        assertFalse(CardHelper.isCardNumberValid(cardNumber));
    }

    @Test
    public void expiryDateCorrectLowerCase() throws Exception {
        String expiryDate = "feb-2015";
        assertTrue(CardHelper.isExpiryDateValid(expiryDate));
    }

    @Test
    public void expiryDateCorrectUpperCase() throws Exception {
        String expiryDate = "Feb-2015";
        assertTrue(CardHelper.isExpiryDateValid(expiryDate));
    }

    @Test
    public void expiryDateInCorrectMonth() throws Exception {
        String expiryDate = "F-2015";
        assertFalse(CardHelper.isExpiryDateValid(expiryDate));
    }

    @Test
    public void expiryDateEmpty() throws Exception {
        String expiryDate = "";
        assertFalse(CardHelper.isExpiryDateValid(expiryDate));
    }

    @Test
    public void expiryDateNull() throws Exception {
        String expiryDate = null;
        assertFalse(CardHelper.isExpiryDateValid(expiryDate));
    }

    @Test
    public void expiryDateInCorrectDateFormat() throws Exception {
        String expiryDate = "01/02/2015";
        assertFalse(CardHelper.isExpiryDateValid(expiryDate));
    }

    @Test
    public void expiryDateSpaces() throws Exception {
        String expiryDate = "    ";
        assertFalse(CardHelper.isExpiryDateValid(expiryDate));
    }

}