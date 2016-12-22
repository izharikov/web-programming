package com.learn.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Ihar Zharykau
 */

@Controller
@RequestMapping("/first")
public class FirstSpringController {

    public String getHelloMessage(@RequestParam(value = "name", required = false, defaultValue = "World") String pName, Model pModel){
        pModel.addAttribute("name", pName);
        return "first";
    }
}
