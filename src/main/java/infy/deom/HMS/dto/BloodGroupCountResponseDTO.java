package infy.deom.HMS.dto;

import infy.deom.HMS.entity.Type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodGroupCountResponseDTO {

    private BloodGroupType bloodGroupType;
    private Long count;
}
