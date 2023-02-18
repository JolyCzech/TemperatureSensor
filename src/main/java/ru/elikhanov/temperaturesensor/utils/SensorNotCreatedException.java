package ru.elikhanov.temperaturesensor.utils;

/**
 * @author Elikhanov
 */

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String message) {
        super(message);
    }
}
