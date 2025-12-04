package ai.villanova.themis.server.service;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}