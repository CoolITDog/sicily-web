package zy.com.cn.demo.take.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import zy.com.cn.demo.take.task.model.User;
import zy.com.cn.demo.take.task.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String home() {
        System.out.println("index");
        return "index";
    }
    @RequestMapping("/index")
    public String index() {
        System.out.println("index");
        return "/index";
    }
    @GetMapping("/time")
    @ResponseBody
    public Long getCurrentTime(){
        System.out.println("index");
        return System.currentTimeMillis();
    }

    @RequestMapping("/add")
    @ResponseBody
    public int addUser(User user){
        return userService.addUser(user);
    }
}
