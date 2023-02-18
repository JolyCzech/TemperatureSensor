package ru.elikhanov.temperaturesensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Elikhanov
 */

@Entity
@Table(name = "sensor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 3, max = 30, message = "Name should longer than 2 and shorter than 30")
    private String name;

    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
    private List<Measurement> measurementList;

    @NotNull
    private LocalDateTime createdAt;

}
