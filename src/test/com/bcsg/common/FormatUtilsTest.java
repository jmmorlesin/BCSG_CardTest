package test.com.bcsg.common;

import com.bcsg.common.FormatUtils;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * JUnit Test case
 * @author Jose M. Morlesin
 */
public class FormatUtilsTest {

    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat("MMM-yyyy");
    }

    @Test
    public void getFormatDateUpperCaseCorrect() throws Exception {
        assertEquals("Feb-2015", FormatUtils.getFormatDate(sdf.parse("Feb-2015")));
    }

    @Test
    public void getFormatDateLowerCaseCorrect() throws Exception {
        assertEquals("Feb-2015", FormatUtils.getFormatDate(sdf.parse("feb-2015")));
    }

    @Test
    public void getFormatDateIncorrect() throws Exception {
        assertNotEquals("Feb-2015", FormatUtils.getFormatDate(sdf.parse("mar-2015")));
    }

    @Test
    public void getDateFromStringLowerCase() throws Exception {
        String expiryDateString = "feb-2015";
        Date expiryDate = FormatUtils.getDateFromString(expiryDateString);
        assertEquals("Feb-2015", FormatUtils.getFormatDate(expiryDate));
    }

    @Test
    public void getDateFromStringUpperCase() throws Exception {
        String expiryDateString = "Feb-2015";
        Date expiryDate = FormatUtils.getDateFromString(expiryDateString);
        assertEquals("Feb-2015", FormatUtils.getFormatDate(expiryDate));
    }

    @Test
    public void getDateFromStringIncorrect() throws Exception {
        String expiryDateString = "mar-2015";
        Date expiryDate = FormatUtils.getDateFromString(expiryDateString);
        assertNotEquals("Format: mar-2015", "Feb-2015", FormatUtils.getFormatDate(expiryDate));
    }

    @Test
    public void getCardNumberFormatDashes() throws Exception {
        String cardNumber = "1111-1111-1111-1111";
        assertEquals("1111-xxxx-xxxx-xxxx", FormatUtils.getCardNumberFormat(cardNumber));
    }

    @Test
    public void getCardNumberFormatDashesShort() throws Exception {
        String cardNumber = "1111-1111-111-11";
        assertEquals("1111-xxxx-xxx-xx", FormatUtils.getCardNumberFormat(cardNumber));
    }

    @Test
    public void getCardNumberFormatSpaces() throws Exception {
        String cardNumber = "1111 1111 111 11";
        assertEquals("1111 xxxx xxx xx", FormatUtils.getCardNumberFormat(cardNumber));
    }

    @Test
    public void getCardNumberFormatWithoutSpaces() throws Exception {
        String cardNumber = "1111111111111111";
        assertEquals("1111xxxxxxxxxxxx", FormatUtils.getCardNumberFormat(cardNumber));
    }

    @Test
    public void getCardNumberFormatShort() throws Exception {
        String cardNumber = "111";
        assertEquals("111", FormatUtils.getCardNumberFormat(cardNumber));
    }

    @Test
    public void getCardNumberFormatLimit() throws Exception {
        String cardNumber = "11111";
        assertEquals("Card number: 11111", "1111x", FormatUtils.getCardNumberFormat(cardNumber));
    }

}