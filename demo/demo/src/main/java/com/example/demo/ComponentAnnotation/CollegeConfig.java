package com.example.demo.ComponentAnnotation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Annotation
@Configuration
@ComponentScan(basePackages = "com.example.demo.student")
public class CollegeConfig {
}
