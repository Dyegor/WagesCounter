package org.dima.counter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/counter")
public class CounterController {

    @RequestMapping(value = "/addWeek")
    public String addWeek() {
        return "";
    }
}
