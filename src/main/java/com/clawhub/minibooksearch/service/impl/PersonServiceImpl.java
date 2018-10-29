package com.clawhub.minibooksearch.service.impl;

import com.clawhub.minibooksearch.entity.Person;
import com.clawhub.minibooksearch.mapper.PersonMapper;
import com.clawhub.minibooksearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <Description> 人员信息逻辑<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:28<br>
 */
@Service
public class PersonServiceImpl implements PersonService {

    /**
     * The Person mapper.
     */
    @Autowired
    private PersonMapper personMapper;

    /**
     * 新增人员信息
     *
     * @param person 人员信息
     */
    @Override
    public void addPerson(Person person) {
        personMapper.insert(person);
    }
}
