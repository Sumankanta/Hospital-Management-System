package infy.deom.HMS.security;

import infy.deom.HMS.entity.Type.PermissionType;
import infy.deom.HMS.entity.Type.RoleType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static infy.deom.HMS.entity.Type.PermissionType.*;
import static infy.deom.HMS.entity.Type.RoleType.*;

public class RolePermissionMapping {

    private static final Map<RoleType, Set<PermissionType>> map = Map.of(
            PATIENT, Set.of(PATIENT_READ, APPOINTMENT_READ, APPOINTMENT_WRITE),
            DOCTOR, Set.of(APPOINTMENT_DELETE, APPOINTMENT_WRITE, APPOINTMENT_READ, PATIENT_READ),
            ADMIN, Set.of(PATIENT_READ, PATIENT_WRITE, APPOINTMENT_READ, APPOINTMENT_WRITE, APPOINTMENT_DELETE, USER_MANAGE, REPORT_VIEW)
    );

    public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(RoleType role) {
        return map.get(role).stream().map(permission ->new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
