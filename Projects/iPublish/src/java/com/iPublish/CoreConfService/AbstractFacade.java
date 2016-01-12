/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iPublish.CoreConfService;

import com.iPublish.Entities.Coreconf_;
import com.sun.xml.rpc.processor.util.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
/**
 *
 * @author zipv5_000
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
    
    public List<T> search(String rank, String query) throws UnsupportedEncodingException {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root root = cq.from(entityClass);
        cq.select(root);
        
        Predicate predRank = null;
        if(!rank.isEmpty()){
            rank = URLDecoder.decode(rank, "UTF-8").toUpperCase();
            if(!rank.equals("ALL")){
                predRank = cb.equal(cb.upper(root.get(Coreconf_.rank)), rank);
            }            
        }
        
        query = URLDecoder.decode(query, "UTF-8").replaceAll("%", "").trim();
        if(!query.isEmpty() && !query.equals("*")){
            query = query.toUpperCase();
            Predicate predCond = cb.like(cb.upper(root.get(Coreconf_.acronym)), '%' + query + '%');
            String[] segments = query.split(" ");
            Predicate predTitle = null;
            for(String seg : segments){
                if(!seg.isEmpty()){
                    Predicate predSeg = cb.like(cb.upper(root.get(Coreconf_.confTitle)), '%' + seg + '%');
                    if(predTitle != null){
                        predTitle = cb.and(predTitle, predSeg);
                    }
                    else{
                        predTitle = predSeg;
                    }
                }
            }
            if(predTitle != null){
                predCond = cb.or(predCond, predTitle);
            }
            if(null == predRank){
                predRank = predCond;
            }
            else{
                predRank = cb.and(predRank, predCond);
            }
        }
        if(null != predRank)
            cq.where(predRank);
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public String findRank(){
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery(String.class);
        Root root = cq.from(entityClass);
        cq.select(root.get("rank")).distinct(true).orderBy(cb.asc(root.get("rank")));
        List<String> results = getEntityManager().createQuery(cq).getResultList();
        String rank = "";
        for(String res : results){
            if(!rank.isEmpty()){
                rank += ",";
            }
            rank += res;
        }
        return rank;
    }
    
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
