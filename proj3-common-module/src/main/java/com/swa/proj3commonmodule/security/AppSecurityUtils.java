package com.swa.proj3commonmodule.security;

import com.swa.proj3commonmodule.security.enums.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AppSecurityUtils {

    public static Set<UserRole> convertStringRolesSetToEnumSet(Set<String> roles) {
        Set<UserRole> userRoleSet = new HashSet<>();
        for (String role : roles) {
            userRoleSet.add(Enum.valueOf(UserRole.class, role));
        }
        return userRoleSet;
    }

    public static CustomUserDetails getCurrentUserPrinciple() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof CustomUserDetails) {
                return ((CustomUserDetails) principal);
            }
        }
        return null;
    }

    public static Optional<Long> getCurrentUserId() {
        Optional<Long> optionalUserId = Optional.ofNullable(getCurrentUserPrinciple())
                .map(customUserDetails -> customUserDetails.getId());
        return optionalUserId;
    }

}
