package ru.elikhanov.temperaturesensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elikhanov.temperaturesensor.models.Measurement;

import java.util.List;

/**
 * @author Elikhanov
 */

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findAllByRainingIsTrue();
}
