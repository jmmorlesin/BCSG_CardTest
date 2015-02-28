package com.bcsg.servlet;

import com.bcsg.exception.CardException;
import com.bcsg.model.Card;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Servlet to manage the upload of a CSV file with 3 columns (Bank, card number, expiry date)
 * @author Jose M. Morlesin
 */
@WebServlet(name = "CardUploadServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class CardUploadServlet extends CommonServlet {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";

    //Columns of the values
    private static final int BANK_COLUMN = 0;
    private static final int CARD_NUMBER_COLUMN = 1;
    private static final int EXPIRY_DATE_COLUMN = 2;

    //Parameter names
    private static final String FILE_PARAM = "file";

    //List of errors
    private static final String LINE_ERROR_MESSAGE = "Problem found in line: ";
    private static final String GENERIC_ERROR_MESSAGE = "Error in read the file";
    private static final String CLOSE_ERROR_MESSAGE = "Error closing file";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Card> cardList = obtainCardList(session);
        List<String> errorList = new ArrayList<>();
        final Part filePart = request.getPart(FILE_PARAM);

        BufferedReader fileReader = null;
        try {
            String line = "";
            InputStream fileContent = filePart.getInputStream();
            Reader reader = new InputStreamReader(fileContent);
            fileReader = new BufferedReader(reader);
            //Read the file line by line
            while ((line = fileReader.readLine()) != null) {
                try {
                    String[] tokens = line.split(COMMA_DELIMITER);
                    if (tokens.length > 0) {
                        Card card = obtainCardFromLine(line);
                        List<String> errorCardList = validateAndGenerateCorrectCard(card);
                        if (errorCardList.isEmpty()) {
                            cardList.add(card);
                        } else {
                            throw new CardException("Generate message error line");
                        }
                    }
                } catch (CardException | ArrayIndexOutOfBoundsException e) {
                    errorList.add(LINE_ERROR_MESSAGE + line);
                }
            }
        } catch (Exception e) {
            errorList.add(GENERIC_ERROR_MESSAGE);
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                errorList.add(CLOSE_ERROR_MESSAGE);
            }
        }

        Collections.sort(cardList);
        session.setAttribute(CARD_LIST_PARAM, cardList);
        session.setAttribute(ERROR_LIST_PARAM, errorList);
        prepareRedirection(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Read a line to create a Card
     * @param line
     * @return
     */
    private Card obtainCardFromLine(String line) {
        Card card = new Card();
        String[] tokens = line.split(COMMA_DELIMITER);
        if (tokens.length > 0) {
            card.setBank(tokens[BANK_COLUMN]);
            card.setCardNumber(tokens[CARD_NUMBER_COLUMN]);
            card.setExpiryDateFromParam(tokens[EXPIRY_DATE_COLUMN]);
        }
        return card;
    }
}
