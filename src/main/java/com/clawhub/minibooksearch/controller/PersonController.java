package com.clawhub.minibooksearch.controller;

import com.alibaba.fastjson.JSONObject;
import com.clawhub.minibooksearch.core.result.ResultUtil;
import com.clawhub.minibooksearch.entity.Person;
import com.clawhub.minibooksearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <Description> 人员信息网关<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:24<br>
 */
@RestController
@RequestMapping("person")
public class PersonController {
    /**
     * The Person service.
     */
    @Autowired
    private PersonService personService;

    /**
     * Add person string.
     *
     * @param person the person
     * @return the string
     */
    @PostMapping("add")
    public String addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResultUtil.getSucc();
    }

}
