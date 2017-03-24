package com.luchoct.utf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtfDetailsController {

    @RequestMapping("/")
    public String getControllerDescription() {
        return "UtfDetailsController invoked.";
    }
}