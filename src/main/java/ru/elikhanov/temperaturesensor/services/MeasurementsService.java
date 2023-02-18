package ru.elikhanov.temperaturesensor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elikhanov.temperaturesensor.models.Measurement;
import ru.elikhanov.temperaturesensor.repositories.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Elikhanov
 */

@Service
@Transactional
@AllArgsConstructor
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Transactional
    public void save(Measurement measurement) {
        addToMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    @Transactional
    public void addToMeasurement(Measurement measurement) {
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public List<Measurement> findAllMeasurements() {
        return measurementsRepository.findAll();
    }

    public List<Measurement> findAllRainyDays() {
        return measurementsRepository.findAllByRainingIsTrue();
    }
}