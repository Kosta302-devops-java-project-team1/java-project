package main.java.com.project.exception;

public class EmailDuplicateException extends Exception{
    public EmailDuplicateException() {
    }

    public EmailDuplicateException(String message) {
        super(message);
    }
}
