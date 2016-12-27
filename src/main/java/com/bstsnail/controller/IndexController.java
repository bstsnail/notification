package com.bstsnail.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with Intellij IDEA.
 * User: Robin
 * Date: 12/26/16
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/hello")
    public String index() {
        return "Hello World";
    }
}
