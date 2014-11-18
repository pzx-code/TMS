package edu.njust.tms.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import edu.njust.tms.util.HibernateUtil;

import edu.njust.tms.dao.Teacher;

public class TeacherDAO {

    /*每页显示纪录数目*/
    private final int PAGE_SIZE = 10;

    /*保存查询后的总的页数*/
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

    /*添加teacher信息*/
    public void AddTeacher(Teacher teacher) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(teacher);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*查询teacher信息*/
    public ArrayList<Teacher> QueryTeacherInfo(String teacherNumber,String teacherName,String teacherBirthday,String teacherArriveDate,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher teacher where 1=1";
            if(!teacherNumber.equals("")) hql = hql + " and teacher.teacherNumber like '%" + teacherNumber + "%'";
            if(!teacherName.equals("")) hql = hql + " and teacher.teacherName like '%" + teacherName + "%'";
            if(!teacherBirthday.equals("")) hql = hql + " and teacher.teacherBirthday like '%" + teacherBirthday + "%'";
            if(!teacherArriveDate.equals("")) hql = hql + " and teacher.teacherArriveDate like '%" + teacherArriveDate + "%'";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始记录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List teacherList = q.list();
            return (ArrayList<Teacher>) teacherList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的teacher纪录*/
    public ArrayList<Teacher> QueryAllTeacherInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher";
            Query q = s.createQuery(hql);
            List teacherList = q.list();
            return (ArrayList<Teacher>) teacherList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和纪录数*/
    public void CalculateTotalPageAndRecordNumber(String teacherNumber,String teacherName,String teacherBirthday,String teacherArriveDate) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher teacher where 1=1";
            if(!teacherNumber.equals("")) hql = hql + " and teacher.teacherNumber like '%" + teacherNumber + "%'";
            if(!teacherName.equals("")) hql = hql + " and teacher.teacherName like '%" + teacherName + "%'";
            if(!teacherBirthday.equals("")) hql = hql + " and teacher.teacherBirthday like '%" + teacherBirthday + "%'";
            if(!teacherArriveDate.equals("")) hql = hql + " and teacher.teacherArriveDate like '%" + teacherArriveDate + "%'";
            Query q = s.createQuery(hql);
            List teacherList = q.list();
            recordNumber = teacherList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public Teacher GetTeacherByTeacherNumber(String teacherNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            Teacher teacher = (Teacher)s.get(Teacher.class, teacherNumber);
            return teacher;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新teacher信息*/
    public void UpdateTeacher(Teacher teacher) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(teacher);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除teacher信息*/
    public void DeleteTeacher (String teacherNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object teacher = s.get(Teacher.class, teacherNumber);
            s.delete(teacher);
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
