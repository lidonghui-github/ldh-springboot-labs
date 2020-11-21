package cn.hd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
@Slf4j
public class AopTestController {

    @GetMapping("/demo/{id}/{name}")
    public String fun1(@PathVariable(name = "id") String id, @PathVariable(name = "name") String name) {
        log.info("id:{}",id);
        log.info("name:{}",name);
        return id+"---"+name;
    }

    @GetMapping("/demo2")
    public void fun2(@RequestParam("m") int m,@RequestParam("n") int n){
        log.info("m/n:{},{}",m,n);
        try {
            int k = m/n;
        }catch (Exception e){

        }finally {

        }

    }
}
