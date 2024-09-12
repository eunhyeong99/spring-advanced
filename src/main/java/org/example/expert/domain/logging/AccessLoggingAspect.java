package org.example.expert.domain.logging;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.expert.domain.common.dto.AuthUser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AccessLoggingAspect {

    private final HttpServletRequest request;

    public AccessLoggingAspect(HttpServletRequest request) {
        this.request = request;
    }

    @Before("execution(* org.example.expert.domain.comment.controller.CommentAdminController.deleteComment(..)) || " +
            "execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public void logAccess(JoinPoint joinPoint) {
        // 현재 로그인한 사용자의 ID를 가져옵니다.
        Long userId = (Long) request.getAttribute("userId");
        // 요청 시각
        String requestTime = java.time.LocalDateTime.now().toString();
        // 요청 URL
        String requestUrl = request.getRequestURI();

        // 로그 기록
        System.out.println("User ID: " + userId);
        System.out.println("Request Time: " + requestTime);
        System.out.println("Request URL: " + requestUrl);
    }
}