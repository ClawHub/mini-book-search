package com.clawhub.minibooksearch.service;

import com.clawhub.minibooksearch.entity.Person;

/**
 * <Description> 人员信息接口<br>
 *
 * @author ClawHub<br>
 * @version 1.0<br>
 * @taskId <br>
 * @create 2018-10-25 21:25<br>
 */
public interface PersonService {
    /**
     * 新增人员信息
     *
     * @param person 人员信息
     */
    void addPerson(Person person);
}
