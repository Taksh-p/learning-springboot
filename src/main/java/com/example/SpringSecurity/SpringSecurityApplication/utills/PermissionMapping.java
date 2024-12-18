package com.example.SpringSecurity.SpringSecurityApplication.utills;

import com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Permission;
import com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Permission.*;
import static com.example.SpringSecurity.SpringSecurityApplication.entities.enums.Role.*;

public class PermissionMapping {

   public static Map<Role, Set<Permission>> map = Map.of(
            USER, Set.of(POST_VIEW, USER_VIEW),
            CREATOR, Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE),
            ADMIN,Set.of(POST_CREATE, USER_UPDATE, POST_UPDATE, USER_CREATE, USER_DELETE, POST_DELETE)
    );

   public static Set<SimpleGrantedAuthority> getAuthoritiesForRole(Role role){
       return map.get(role)
               .stream()
               .map(permission -> new SimpleGrantedAuthority(permission.name()))
               .collect(Collectors.toSet());
   }
}
