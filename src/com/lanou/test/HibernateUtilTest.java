package com.lanou.test;

import com.lanou.domain.Student;
import com.lanou.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * Created by dllo on 17/10/17.
 */
public class HibernateUtilTest {
    @Test
    public void findAll(){
        //通过工具类获得一个session对象
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        //查询学生表中所有学生数据,变历查询结果
        List<Student> students = session.createQuery("from Student").list();
        for (Student student : students) {
            System.out.println(student);
        }
        transaction.commit();//提交本次事物
        HibernateUtil.closeSession();//关闭当前session对象
    }
}
