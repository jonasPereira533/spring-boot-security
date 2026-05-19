package br.com.jonas.Spring_boot_essentials.exception;

public class BadRequestException extends Exception {

    public BadRequestException(String massage) {
        super(massage);
    }
}
