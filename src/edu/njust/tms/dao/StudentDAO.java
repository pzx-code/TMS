package edu.njust.tms.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import edu.njust.tms.util.HibernateUtil;

import edu.njust.tms.dao.ClassInfo;
import edu.njust.tms.dao.Student;

public class StudentDAO {

    /*每页显示纪录数目*/
    private final int PAGE_SIZE = 10;

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总纪录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加student信息*/
    public void AddStudent(Student student) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(student);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*查询Student信息*/
    public ArrayList<Student> QueryStudentInfo(String studentNumber,String studentName,ClassInfo studentClassNumber,String studentBirthday,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Student student where 1=1";
            if(!studentNumber.equals("")) hql = hql + " and student.studentNumber like '%" + studentNumber + "%'";
            if(!studentName.equals("")) hql = hql + " and student.studentName like '%" + studentName + "%'";
            if(null != studentClassNumber && !studentClassNumber.getClassNumber().equals("")) hql += " and student.studentClassNumber.classNumber='" + studentClassNumber.getClassNumber() + "'";
            if(!studentBirthday.equals("")) hql = hql + " and student.studentBirthday like '%" + studentBirthday + "%'";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始纪录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List studentList = q.list();
            return (ArrayList<Student>) studentList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有student纪录*/
    public ArrayList<Student> QueryAllStudentInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Student";
            Query q = s.createQuery(hql);
            List studentList = q.list();
            return (ArrayList<Student>) studentList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和纪录数*/
    public void CalculateTotalPageAndRecordNumber(String studentNumber,String studentName,ClassInfo studentClassNumber,String studentBirthday) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From Student student where 1=1";
            if(!studentNumber.equals("")) hql = hql + " and student.studentNumber like '%" + studentNumber + "%'";
            if(!studentName.equals("")) hql = hql + " and student.studentName like '%" + studentName + "%'";
            if(null != studentClassNumber && !studentClassNumber.getClassNumber().equals("")) hql += " and student.studentClassNumber.classNumber='" + studentClassNumber.getClassNumber() + "'";
            if(!studentBirthday.equals("")) hql = hql + " and student.studentBirthday like '%" + studentBirthday + "%'";
            Query q = s.createQuery(hql);
            List studentList = q.list();
            recordNumber = studentList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public Student GetStudentByStudentNumber(String studentNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            Student student = (Student)s.get(Student.class, studentNumber);
            return student;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新student信息*/
    public void UpdateStudent(Student student) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(student);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除student信息*/
    public void DeleteStudent (String studentNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object student = s.get(Student.class, studentNumber);
            s.delete(student);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

}
