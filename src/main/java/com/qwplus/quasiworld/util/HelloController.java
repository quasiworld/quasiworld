package com.qwplus.quasiworld.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/**
 * @ClassName: HelloController
 * @description
 * @Date 2020年04月30日 10:11:58
 */
@RestController
public class HelloController {

    @GetMapping("/md5/{md5}")
    public String hello(@PathVariable("md5") String md5){
        return MD5.MD5(md5);
    }
}
