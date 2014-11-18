package edu.njust.tms.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import edu.njust.tms.util.HibernateUtil;

import edu.njust.tms.dao.Student;
import edu.njust.tms.dao.CourseInfo;
import edu.njust.tms.dao.ScoreInfo;

public class ScoreInfoDAO {

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

    /*添加scoreinfo信息*/
    public void AddScoreInfo(ScoreInfo scoreInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(scoreInfo);
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
    public ArrayList<ScoreInfo> QueryScoreInfoInfo(Student studentNumber,CourseInfo courseNumber,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From ScoreInfo scoreInfo where 1=1";
            if(null != studentNumber && !studentNumber.getStudentNumber().equals("")) hql += " and scoreInfo.studentNumber.studentNumber='" + studentNumber.getStudentNumber() + "'";
            if(null != courseNumber && !courseNumber.getCourseNumber().equals("")) hql += " and scoreInfo.courseNumber.courseNumber='" + courseNumber.getCourseNumber() + "'";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始纪录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List scoreInfoList = q.list();
            return (ArrayList<ScoreInfo>) scoreInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的scoreinfo纪录*/
    public ArrayList<ScoreInfo> QueryAllScoreInfoInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From ScoreInfo";
            Query q = s.createQuery(hql);
            List scoreInfoList = q.list();
            return (ArrayList<ScoreInfo>) scoreInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和纪录数*/
    public void CalculateTotalPageAndRecordNumber(Student studentNumber,CourseInfo courseNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From ScoreInfo scoreInfo where 1=1";
            if(null != studentNumber && !studentNumber.getStudentNumber().equals("")) hql += " and scoreInfo.studentNumber.studentNumber='" + studentNumber.getStudentNumber() + "'";
            if(null != courseNumber && !courseNumber.getCourseNumber().equals("")) hql += " and scoreInfo.courseNumber.courseNumber='" + courseNumber.getCourseNumber() + "'";
            Query q = s.createQuery(hql);
            List scoreInfoList = q.list();
            recordNumber = scoreInfoList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public ScoreInfo GetScoreInfoByScoreId(int scoreId) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            ScoreInfo scoreInfo = (ScoreInfo)s.get(ScoreInfo.class, scoreId);
            return scoreInfo;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新scoreinfo信息*/
    public void UpdateScoreInfo(ScoreInfo scoreInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(scoreInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除scoreinfo信息*/
    public void DeleteScoreInfo (int scoreId) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object scoreInfo = s.get(ScoreInfo.class, scoreId);
            s.delete(scoreInfo);
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
