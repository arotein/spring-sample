package com.example.demo;

import org.junit.jupiter.api.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class JsSample {
    @Test
    void jsTest() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("graal.js");

        try {
            // 자바스크립트 코드 실행
            engine.eval("console.log('Hello, JavaScript from Java!')");
            engine.eval("var x = 10; var y = 20; var z = x + y;");
            Object result = engine.eval("z");
            System.out.println("Result of JavaScript calculation: " + result);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
