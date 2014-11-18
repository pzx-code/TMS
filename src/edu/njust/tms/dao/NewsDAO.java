package edu.njust.tms.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import edu.njust.tms.util.HibernateUtil;

import edu.njust.tms.dao.News;

public class NewsDAO {

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

    /*添加news信息*/
    public void AddNews(News news) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(news);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*查询news信息*/
    public ArrayList<News> QueryNewsInfo(String newsTitle,String newsDate,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From News news where 1=1";
            if(!newsTitle.equals("")) hql = hql + " and news.newsTitle like '%" + newsTitle + "%'";
            if(!newsDate.equals("")) hql = hql + " and news.newsDate like '%" + newsDate + "%'";
            Query q = s.createQuery(hql);
            /*计算当前显示页码的开始纪录*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List newsList = q.list();
            return (ArrayList<News>) newsList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*函数功能：查询所有的news纪录*/
    public ArrayList<News> QueryAllNewsInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From News";
            Query q = s.createQuery(hql);
            List newsList = q.list();
            return (ArrayList<News>) newsList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*计算总的页数和纪录数*/
    public void CalculateTotalPageAndRecordNumber(String newsTitle,String newsDate) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From News news where 1=1";
            if(!newsTitle.equals("")) hql = hql + " and news.newsTitle like '%" + newsTitle + "%'";
            if(!newsDate.equals("")) hql = hql + " and news.newsDate like '%" + newsDate + "%'";
            Query q = s.createQuery(hql);
            List newsList = q.list();
            recordNumber = newsList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*根据主键获取对象*/
    public News GetNewsByNewsId(int newsId) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            News news = (News)s.get(News.class, newsId);
            return news;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*更新news信息*/
    public void UpdateNews(News news) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(news);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*删除news信息*/
    public void DeleteNews (int newsId) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object news = s.get(News.class, newsId);
            s.delete(news);
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
