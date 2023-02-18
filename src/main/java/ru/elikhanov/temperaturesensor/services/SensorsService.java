package ru.elikhanov.temperaturesensor.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elikhanov.temperaturesensor.models.Sensor;
import ru.elikhanov.temperaturesensor.repositories.SensorsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Elikhanov
 */


@Service
@Transactional
@AllArgsConstructor
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Transactional()
    public void save(Sensor sensor) {
        addToSensor(sensor);
        sensorsRepository.save(sensor);
    }

    @Transactional
    public void addToSensor(Sensor sensor) {
        sensor.setCreatedAt(LocalDateTime.now());
    }

    public Optional<Sensor> findByName(String name) {
        Optional<Sensor> sensor = Optional.ofNullable(sensorsRepository.findByName(name));
        return Optional.ofNullable(sensor.orElse(null));
    }

    public List<Sensor> findAllSensors() {
        return sensorsRepository.findAll();
    }
}
