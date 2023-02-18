package ru.elikhanov.temperaturesensor.utils;

/**
 * @author Elikhanov
 */

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException(String message) {
        super(message);
    }
}
