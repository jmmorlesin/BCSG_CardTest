package com.bcsg.servlet;

import com.bcsg.model.Card;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Servlet to manage the insert of card data manually, one row at a time.
 * @author Jose M. Morlesin
 */
@WebServlet(name = "CardServlet")
public class CardServlet extends CommonServlet {

    //Parameter names
    private static final String BANK_PARAM = "bank";
    private static final String CARD_NUMBER_PARAM = "cardNumber";
    private static final String EXPIRY_DATE_PARAM = "expiryDate";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Card> cardList = obtainCardList(session);

        Card card = obtainCardFromParams(request);
        List<String> errorList = validateAndGenerateCorrectCard(card);
        if (errorList.isEmpty()) {
            cardList.add(card);
        }

        Collections.sort(cardList);
        session.setAttribute(CARD_LIST_PARAM, cardList);
        session.setAttribute(ERROR_LIST_PARAM, errorList);
        prepareRedirection(request, response);
    }

    /**
     * Read the params to create a Card
     * @param request
     * @return
     */
    private Card obtainCardFromParams(HttpServletRequest request) {
        Card card = new Card();
        card.setBank(request.getParameter(BANK_PARAM));
        card.setCardNumber(request.getParameter(CARD_NUMBER_PARAM));
        card.setExpiryDateFromParam(request.getParameter(EXPIRY_DATE_PARAM));
        return card;
    }
}
