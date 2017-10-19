package com.lanou.test;

import com.lanou.dao.StudentDao;
import com.lanou.dao.impl.StudentDaoImpl;
import com.lanou.domain.Student;
import org.junit.Test;

/**
 * Created by dllo on 17/10/18.
 */
public class StudentDaoTest {
    @Test
    public void save(){
        //创建一个dao层的对象
        StudentDao dao = new StudentDaoImpl();
        //创建要保存的对象
        Student student = new Student("照照","男",18);

        System.out.println("保存前:"+student);

        dao.save(student);//保存
        System.out.println("保存后:" + student);
    }

    @Test
    public void login(){
        StudentDao studentDao = new StudentDaoImpl();
        boolean result = studentDao.login("照照", "123");
        System.out.println("登录结果:" + result);
    }
}
