package com.lanou.test;

import com.lanou.domain.Student;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by dllo on 17/10/17.
 * 针对student的单元测试
 */
public class StudentTest {
    //session的工厂类对象,是线程安全的,一个数据库对应一个session工厂类对象
    //通常在项目初始化时初始化该对象,用Configuration对像创建
    private SessionFactory sessionFactory ;
    /*
    真正执行CRUD操作的数据库连接对象,代表了一次数据库的连接,是非线程安全的
     */
    private Session session;
    /*
    事物对象,与jdbc中的事物类似,只不过hibernate中的事物不是自动提交的,而jdbc中的事物会自动提交,hibernate中的事物需要手动添加
     */
    private Transaction transaction;
    /**初始化操作
     * Before注解,在Test注解之前执行的方法,通常在该方法中进行一些初始化的操作**/
    @Before
    public void init(){
        System.out.println("******init*******");
        //创建hibernate配置对象            Configuration接口:负责配置并启动Hibernate
        Configuration configuration = new Configuration();
        //加载指定配置文件
        configuration.configure("hibernate.cfg.xml");
        //通过配置对象创建一个sessionFactory对象 创建结束之后configuration就与sessionFactory失去了联系
        sessionFactory =configuration.buildSessionFactory();
        //打开数据库连接
        session =sessionFactory.openSession();
        //开始事物
        transaction = session.beginTransaction();
    }
    /**销毁操作
     * After注解:在Test注解之后执行的方法,通常该方法中做一些释放,关闭的操作**/
    @After
    public void destroy(){
        System.out.println("****destroy*****");
        //提交本次事物
        transaction.commit();
        //关闭本次连接
        session.close();
    }
    /**数据库插入操作
     * Test注解:单元测试执行的方法**/
    @Test
    public void insert(){
        System.out.println("*****insert*****");
        //创建一个实体类的学生的对象
        Student student = new Student("张三","男",23);
        //将实体类对象保存到数据库中
        session.save(student);
    }

    @Test
    public void query(){
        //获得一个查询对象,等同于select * from Student
       // Query query = session.createQuery("from Student ");
        Query query = session.createQuery("from Student where sname=?");
        //条件查询语句中的占位符
       query.setString(0,"张三");//指定第一个问号所对应的值
        //返回查询的结果集
        List<Student> students = query.list();
        //遍历结果集
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void delect(){
        //先从数据库中查询要删除的对象,在进行删除
        Query query = session.createQuery("from  Student where sname=?");
        query.setString(0,"张三");//条件语句中的参数设置
        List<Student> students= query.list();
        if (students.size()>0){
            //如果能查询出张三用户,则进行删除第一个叫张三的用户
            session.delete(students.get(0));
        }
    }

    @Test
    public void update(){
        Query query = session.createQuery("from  Student where sname=?");
        query.setString(0,"张三");
        List<Student>students = query.list();
        if (students.size()>0){
            //取第一个叫张三的学生
            Student student = students.get(0);
            //重新修改该学生的基础信息
            student.setSname("李四");
            student.setGender("女");
            student.setAge(18);
            session.update(student);//更新学生对象

        }
    }

    @Test
    public void createCriteria(){
        //获得要进行查询的Student对应的Criteria接口对象,比sql更加高级的查询方式
        Criteria criteria = session.createCriteria(Student.class);
        //设置最对查询条数
        criteria.setMaxResults(2);
        //获取查询结果集
       List<Student> students = criteria.list();
        //遍历结果集
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void querySingle(){
        Query query = session.createQuery("from Student where id=2                                         ");
        //返回单个结果  uniqueResult
       Student student  =(Student) query.uniqueResult();
       System.out.println(student);
    }
}
