package ru.elikhanov.temperaturesensor.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.elikhanov.temperaturesensor.models.Measurement;
import ru.elikhanov.temperaturesensor.services.SensorsService;

/**
 * @author Elikhanov
 */

@AllArgsConstructor
@Component
public class MeasurementValidator implements Validator {
    SensorsService sensorsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (sensorsService.findByName(measurement.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "", "This sensor was not created");
    }
}
