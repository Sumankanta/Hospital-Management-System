package infy.deom.HMS.dto;

import infy.deom.HMS.entity.Type.BloodGroupType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientResponseDto {

    private Long patientIdid;
    private String name;
    private String gender;
    private LocalDate birthDate;
    private BloodGroupType bloodGroup;
}
