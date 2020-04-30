package com.qwplus.quasiworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @description
 * @Date 2020年04月30日 10:11:58
 */
@RestController
public class HelloController {

    @GetMapping("/helloWorld")
    public String hello(){

        return "hello";
    }


}
