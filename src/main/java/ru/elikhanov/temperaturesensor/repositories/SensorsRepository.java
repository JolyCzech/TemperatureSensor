package ru.elikhanov.temperaturesensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elikhanov.temperaturesensor.models.Sensor;

/**
 * @author Elikhanov
 */

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

    Sensor findByName(String name);
}
