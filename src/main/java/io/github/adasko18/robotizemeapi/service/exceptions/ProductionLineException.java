package io.github.adasko18.robotizemeapi.service.exceptions;

public class ProductionLineException extends RuntimeException {
    public ProductionLineException() {
        super("Production line not found");
    }
}
