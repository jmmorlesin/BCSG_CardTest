package com.bcsg.exception;

/**
 * Controlled exception with cards
 * @author Jose M. Morlesin
 */
public class CardException extends Exception {
    public CardException() {
    }

    public CardException(String message) {
        super(message);
    }
}