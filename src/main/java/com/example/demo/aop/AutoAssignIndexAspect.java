package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@Aspect
@Component
public class AutoAssignIndexAspect {
    @AfterReturning(value = "@annotation(com.example.demo.aop.AutoAssignIndex)", returning = "response")
    public void autoAssign(List<?> response) {
        if (response != null && !response.isEmpty()) {
            for (int i = 0; i < response.size(); i++) {
                try {
                    Object object = response.get(i);
                    Field indexField = object.getClass().getDeclaredField("index");
                    indexField.setAccessible(true);
                    indexField.set(object, i);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                }
            }
        }
    }
}
