package ru.elikhanov.temperaturesensor.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.elikhanov.temperaturesensor.models.Sensor;
import ru.elikhanov.temperaturesensor.services.SensorsService;

/**
 * @author Elikhanov
 */

@AllArgsConstructor
@Component
public class SensorValidator implements Validator {

    SensorsService sensorsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        if (sensorsService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "This name is already taken");
    }
}
