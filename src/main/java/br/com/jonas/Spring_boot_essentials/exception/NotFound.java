package br.com.jonas.Spring_boot_essentials.exception;

public class NotFound extends Exception {

    public NotFound(String massage) {
        super(massage);
    }
}
