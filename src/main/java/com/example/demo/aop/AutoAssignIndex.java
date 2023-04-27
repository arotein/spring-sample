package com.example.demo.aop;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface AutoAssignIndex {
}
