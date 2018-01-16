package com.sam.sct.mytest.action;


import com.sam.sct.mytest.beans.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Value("${girl.name}")
    private String name;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hello","/hi","/"},method = RequestMethod.GET)
    public String say(){
        return girlProperties.getAge()+" hi you !! "+name;
    }
}
