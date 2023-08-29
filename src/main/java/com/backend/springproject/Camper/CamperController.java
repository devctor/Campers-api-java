package com.backend.springproject.Camper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamperController {

    @GetMapping("/camper")
    public  String index() { return "camper /GET";}
}
