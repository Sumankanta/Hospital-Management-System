package infy.deom.HMS.dto;

import infy.deom.HMS.entity.Type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {

    private String username;
    private String password;
    private String name;

    // Only for study purpose not preferred for production grid application
    private Set<RoleType> roles = new HashSet<>();
}
