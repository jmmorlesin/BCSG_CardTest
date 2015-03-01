package com.bcsg.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Main model to the test
 * Created by Jose on 27/02/2015.
 */
public class Card implements Comparable<Card>, Serializable{
    private String bank;
    private String cardNumber;
    private Date expiryDate;
    //Used to save the expiryDate from params
    private transient String expiryDateFromParam;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDateFromParam() {
        return expiryDateFromParam;
    }

    public void setExpiryDateFromParam(String expiryDateFromParam) {
        this.expiryDateFromParam = expiryDateFromParam;
    }

    /**
     * Method to sort the data by Expiry date in descending order
     * @param o
     * @return
     */
    @Override
    public int compareTo(Card o) {
        return o.getExpiryDate().compareTo(expiryDate);
    }
}