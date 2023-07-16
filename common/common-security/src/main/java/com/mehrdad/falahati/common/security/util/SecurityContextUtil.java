package com.mehrdad.falahati.common.security.util;

import com.mehrdad.falahati.common.security.entity.UserSecurityEntity;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityContextUtil {
    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserSecurityEntity)
            return ((UserSecurityEntity)principal).getUsername();
        else
            return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
