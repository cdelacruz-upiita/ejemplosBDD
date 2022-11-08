/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdd.controller;

import com.bdd.controller.exceptions.NonexistentEntityException;
import com.bdd.controller.exceptions.PreexistingEntityException;
import com.bdd.model.SalesOrderDetail;
import com.bdd.model.SalesOrderDetailPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bdd.model.SalesOrderHeader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author CDLCS
 */
public class SalesOrderDetailJpaController implements Serializable {

    public SalesOrderDetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalesOrderDetail salesOrderDetail) throws PreexistingEntityException, Exception {
        if (salesOrderDetail.getSalesOrderDetailPK() == null) {
            salesOrderDetail.setSalesOrderDetailPK(new SalesOrderDetailPK());
        }
        salesOrderDetail.getSalesOrderDetailPK().setSalesOrderID(salesOrderDetail.getSalesOrderHeader().getSalesOrderID());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalesOrderHeader salesOrderHeader = salesOrderDetail.getSalesOrderHeader();
            if (salesOrderHeader != null) {
                salesOrderHeader = em.getReference(salesOrderHeader.getClass(), salesOrderHeader.getSalesOrderID());
                salesOrderDetail.setSalesOrderHeader(salesOrderHeader);
            }
            em.persist(salesOrderDetail);
            if (salesOrderHeader != null) {
                salesOrderHeader.getSalesOrderDetailCollection().add(salesOrderDetail);
                salesOrderHeader = em.merge(salesOrderHeader);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSalesOrderDetail(salesOrderDetail.getSalesOrderDetailPK()) != null) {
                throw new PreexistingEntityException("SalesOrderDetail " + salesOrderDetail + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalesOrderDetail salesOrderDetail) throws NonexistentEntityException, Exception {
        salesOrderDetail.getSalesOrderDetailPK().setSalesOrderID(salesOrderDetail.getSalesOrderHeader().getSalesOrderID());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalesOrderDetail persistentSalesOrderDetail = em.find(SalesOrderDetail.class, salesOrderDetail.getSalesOrderDetailPK());
            SalesOrderHeader salesOrderHeaderOld = persistentSalesOrderDetail.getSalesOrderHeader();
            SalesOrderHeader salesOrderHeaderNew = salesOrderDetail.getSalesOrderHeader();
            if (salesOrderHeaderNew != null) {
                salesOrderHeaderNew = em.getReference(salesOrderHeaderNew.getClass(), salesOrderHeaderNew.getSalesOrderID());
                salesOrderDetail.setSalesOrderHeader(salesOrderHeaderNew);
            }
            salesOrderDetail = em.merge(salesOrderDetail);
            if (salesOrderHeaderOld != null && !salesOrderHeaderOld.equals(salesOrderHeaderNew)) {
                salesOrderHeaderOld.getSalesOrderDetailCollection().remove(salesOrderDetail);
                salesOrderHeaderOld = em.merge(salesOrderHeaderOld);
            }
            if (salesOrderHeaderNew != null && !salesOrderHeaderNew.equals(salesOrderHeaderOld)) {
                salesOrderHeaderNew.getSalesOrderDetailCollection().add(salesOrderDetail);
                salesOrderHeaderNew = em.merge(salesOrderHeaderNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SalesOrderDetailPK id = salesOrderDetail.getSalesOrderDetailPK();
                if (findSalesOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The salesOrderDetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SalesOrderDetailPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalesOrderDetail salesOrderDetail;
            try {
                salesOrderDetail = em.getReference(SalesOrderDetail.class, id);
                salesOrderDetail.getSalesOrderDetailPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salesOrderDetail with id " + id + " no longer exists.", enfe);
            }
            SalesOrderHeader salesOrderHeader = salesOrderDetail.getSalesOrderHeader();
            if (salesOrderHeader != null) {
                salesOrderHeader.getSalesOrderDetailCollection().remove(salesOrderDetail);
                salesOrderHeader = em.merge(salesOrderHeader);
            }
            em.remove(salesOrderDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SalesOrderDetail> findSalesOrderDetailEntities() {
        return findSalesOrderDetailEntities(true, -1, -1);
    }

    public List<SalesOrderDetail> findSalesOrderDetailEntities(int maxResults, int firstResult) {
        return findSalesOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<SalesOrderDetail> findSalesOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalesOrderDetail.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
   
    public SalesOrderDetail findSalesOrderDetail(SalesOrderDetailPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalesOrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalesOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalesOrderDetail> rt = cq.from(SalesOrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
