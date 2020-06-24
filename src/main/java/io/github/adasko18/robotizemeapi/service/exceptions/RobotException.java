package io.github.adasko18.robotizemeapi.service.exceptions;

public class RobotException extends RuntimeException {
    public RobotException() {
        super("Robot not found");
    }
}
