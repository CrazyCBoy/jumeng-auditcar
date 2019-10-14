package com.jumeng.auditcar.web.controller.carSys;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class VueController {

    @RequestMapping("/info")
    public String VueTest(){
        return "OOOOOOOOOOOOOOOOOOOOKKKKKKKKKKK";
    }
}
