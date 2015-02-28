package com.bcsg.servlet;

import com.bcsg.common.FormatUtils;
import com.bcsg.helper.CardHelper;
import com.bcsg.model.Card;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Common Servlet with the common methods and values.
 * @author Jose M. Morlesin
 */
public abstract class CommonServlet extends HttpServlet {
    //URL to redirect the servlet
    protected static final String REDIRECT_TO = "/index.jsp";

    //List of params
    public static final String CARD_LIST_PARAM = "cardList";
    public static final String ERROR_LIST_PARAM = "errorList";

    //List of errors
    protected static final String BANK_ERROR_MESSAGE = "Bank can't be blank";
    protected static final String CARD_NUMBER_ERROR_MESSAGE = "Card number can't be blank. Accepts numbers, dashes and spaces";
    protected static final String EXPIRY_DATE_ERROR_MESSAGE = "Expiry date can't be blank. Format must be like Feb-2015";

    /**
     * Prepare the redirection to the main page
     * @param request
     * @param response
     */
    protected void prepareRedirection(HttpServletRequest request, HttpServletResponse response) {
        String context_path = request.getContextPath();
        String site = context_path + REDIRECT_TO;
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", site);
    }

    /**
     * Get the Card list from Session, or will be created if not exist
     * @param session
     * @return
     */
    protected List<Card> obtainCardList(HttpSession session) {
        List<Card> cardList = (List<Card>) session.getAttribute(CARD_LIST_PARAM);
        if (cardList == null) {
            cardList = new ArrayList<>();
        }
        return cardList;
    }

    /**
     * Method to validate if the card data are correct. In this case, update the expiry date
     * @param card
     * @return The list of errors found. The list is empty if don't find errors
     */
    protected List<String> validateAndGenerateCorrectCard(Card card) {
        List<String> errors = new ArrayList<>();
        if (!CardHelper.isBankNameValid(card.getBank())) {
            errors.add(BANK_ERROR_MESSAGE);
        }
        if (!CardHelper.isCardNumberValid(card.getCardNumber())) {
            errors.add(CARD_NUMBER_ERROR_MESSAGE);
        }
        if (!CardHelper.isExpiryDateValid(card.getExpiryDateFromParam())) {
            errors.add(EXPIRY_DATE_ERROR_MESSAGE);
        }
        if (errors.isEmpty()) {
            if (!updateExpiryDate(card)) {
                errors.add(EXPIRY_DATE_ERROR_MESSAGE);
            }
        }
        return errors;
    }

    /**
     * Needs to be called after the validation. If the card data is valid, generate the expiry date
     * @param card
     * @return
     */
    private boolean updateExpiryDate(Card card) {
        boolean isValid = true;
        try {
            Date expiryDate = FormatUtils.getDateFromString(card.getExpiryDateFromParam());
            card.setExpiryDate(expiryDate);
        } catch (ParseException e) {
            isValid = false;
        }
        return isValid;
    }
}
