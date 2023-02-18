package ru.elikhanov.temperaturesensor.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author Elikhanov
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SensorDTO {

    @NotEmpty
    @Size(min = 3, max = 30, message = "Name should longer than 2 and shorter than 30")
    private String name;


}
