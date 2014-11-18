package edu.njust.tms.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import edu.njust.tms.util.HibernateUtil;

import edu.njust.tms.dao.Teacher;
import edu.njust.tms.dao.CourseInfo;

public class CourseInfoDAO {

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

    /*添加courseinfo信息*/
    public void AddCourseInfo(CourseInfo courseInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(courseInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*查询courseinfo信息*/
    public ArrayList<CourseInfo> QueryCourseInfoInfo(String courseNumber,String courseName,Teacher courseTeacher,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo courseInfo where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            /*??????????????????????????*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List courseInfoList = q.list();
            return (ArrayList<CourseInfo>) courseInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的courseinfo纪录*/
    public ArrayList<CourseInfo> QueryAllCourseInfoInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo";
            Query q = s.createQuery(hql);
            List courseInfoList = q.list();
            return (ArrayList<CourseInfo>) courseInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和纪录数*/
    public void CalculateTotalPageAndRecordNumber(String courseNumber,String courseName,Teacher courseTeacher) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo courseInfo where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            List courseInfoList = q.list();
            recordNumber = courseInfoList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public CourseInfo GetCourseInfoByCourseNumber(String courseNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            CourseInfo courseInfo = (CourseInfo)s.get(CourseInfo.class, courseNumber);
            return courseInfo;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新courseinfo信息*/
    public void UpdateCourseInfo(CourseInfo courseInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(courseInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除courseinfo信息*/
    public void DeleteCourseInfo (String courseNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object courseInfo = s.get(CourseInfo.class, courseNumber);
            s.delete(courseInfo);
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
