package ru.elikhanov.temperaturesensor.utils;

import lombok.*;

/**
 * @author Elikhanov
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private long timestamp;

}
