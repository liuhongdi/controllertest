package com.controllertest.demo.controller;

import com.controllertest.demo.result.RestResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/home1")
    public String home1() {
        return "1111";
    }

    @GetMapping("/loginget")
    public RestResult loginget(@RequestParam(value="username",required = true,defaultValue = "") String username,
                           @RequestParam(value="password",required = true,defaultValue = "") String password) {
        /*
        if (username.equals("")) {
            return "error";
        }else {
            return "success";
        }
        */
        //return "1111";
        if (username.equals("")) {
            //Map resMap = new HashMap();
            //resMap.put("goodsId",goodsId);
            return RestResult.error(1,"failed");
        } else {

            return RestResult.success("success");
        }
    }

    @PostMapping("/loginpost")
    public String loginpost(@RequestParam(value="username",required = true,defaultValue = "") String username,
                                @RequestParam(value="password",required = true,defaultValue = "") String password) {

        if (username.equals("")) {
            return "error";
        }else {
            return "success";
        }

    }
}
