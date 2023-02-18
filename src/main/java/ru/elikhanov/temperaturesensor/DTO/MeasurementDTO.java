package ru.elikhanov.temperaturesensor.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

/**
 * @author Elikhanov
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MeasurementDTO {

    @NotNull(message = "value should not be null")
    @Range(min = -100, max = 100, message = "value should be between -100 and 100")
    private float value;

    @NotNull(message = "raining should not be empty")
    private boolean raining;

    private SensorDTO sensor;

}
