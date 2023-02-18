package ru.elikhanov.temperaturesensor.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.elikhanov.temperaturesensor.DTO.MeasurementDTO;
import ru.elikhanov.temperaturesensor.models.Measurement;
import ru.elikhanov.temperaturesensor.services.MeasurementsService;
import ru.elikhanov.temperaturesensor.utils.MeasurementNotCreatedException;
import ru.elikhanov.temperaturesensor.utils.MeasurementValidator;
import ru.elikhanov.temperaturesensor.utils.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Elikhanov
 */

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementsControllers {

    private final MeasurementValidator measurementValidator;
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;


    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementsService.findAllMeasurements()
                .stream()
                .map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(
            @RequestBody @Valid MeasurementDTO measurementDTO,
            BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurement, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError e : errors) {
                errorMsg.append(e.getField())
                        .append(" - ").append(e.getDefaultMessage())
                        .append(";");
            }

            throw new MeasurementNotCreatedException(errorMsg.toString());
        }

        measurementsService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementsService.findAllRainyDays().size();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }


    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
