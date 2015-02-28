package test.com.bcsg.model;

import com.bcsg.model.Card;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * JUnit Test case
 * @author Jose M. Morlesin
 */
public class CardTest {

    private SimpleDateFormat sdf;

    @Before
    public void setUp() throws Exception {
        sdf = new SimpleDateFormat("MMM-yyyy");
    }

    @Test
    public void testDescendingOrder() throws Exception {
        Card firstCard = getCardFromDate("Bank 1", null, sdf.parse("Feb-2015"));
        Card secondCard = getCardFromDate("Bank 2", null, sdf.parse("Feb-2016"));

        List<Card> cardList = new ArrayList<>();
        cardList.add(firstCard);
        cardList.add(secondCard);

        Card card = cardList.get(0);
        assertTrue("Insert order", card.getBank().equals(firstCard.getBank()));

        Collections.sort(cardList);

        card = cardList.get(0);
        assertTrue("Sort don't works", card.getBank().equals(secondCard.getBank()));
    }

    private Card getCardFromDate(String bank, String cardNumber, Date expiryDate) throws Exception {
        Card card = new Card();
        card.setBank(bank);
        card.setCardNumber(cardNumber);
        card.setExpiryDate(expiryDate);
        return card;
    }
}