package com.atm.buenas_practicas_java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final ThymeleafConfig.AppProperties appProperties;

    @Autowired
    public GlobalControllerAdvice(ThymeleafConfig.AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("appBaseUrl", appProperties.getBaseUrl());
    }

}
