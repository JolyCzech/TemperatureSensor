package ru.elikhanov.temperaturesensor.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

/**
 * @author Elikhanov
 */

@Entity
@Table(name = "Measurement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;


    @NotNull(message = "value should not be null")
    @Range(min = -100, max = 100, message = "value should be between -100 and 100")
    private float value;

    @NotNull(message = "raining should not be empty")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    @NotNull
    private LocalDateTime createdAt;

}
