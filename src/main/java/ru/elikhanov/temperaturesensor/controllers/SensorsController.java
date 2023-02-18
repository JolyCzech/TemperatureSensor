package ru.elikhanov.temperaturesensor.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.elikhanov.temperaturesensor.DTO.SensorDTO;
import ru.elikhanov.temperaturesensor.models.Sensor;
import ru.elikhanov.temperaturesensor.services.SensorsService;
import ru.elikhanov.temperaturesensor.utils.ErrorResponse;
import ru.elikhanov.temperaturesensor.utils.SensorNotCreatedException;
import ru.elikhanov.temperaturesensor.utils.SensorValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elikhanov
 */

@RestController
@AllArgsConstructor
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorValidator sensorValidator;
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public List<SensorDTO> getAllSensors() {
        return sensorsService.findAllSensors()
                .stream()
                .map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid SensorDTO sensorDTO,
            BindingResult bindingResult) {

        @Valid Sensor sensor = convertToSensor(sensorDTO);

        sensorValidator.validate(sensor, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError e : errors) {
                errorMsg.append(e.getField())
                        .append(" - ").append(e.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorsService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);

    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException1(SensorNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }


}

