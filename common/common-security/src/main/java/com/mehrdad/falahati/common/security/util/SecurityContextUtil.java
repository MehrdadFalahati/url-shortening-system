package com.mehrdad.falahati.common.security.util;

import com.mehrdad.falahati.common.security.entity.UserNamesPrincipal;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityContextUtil {
    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserNamesPrincipal)
            return ((UserNamesPrincipal)principal).getUsername();
        else
            return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
