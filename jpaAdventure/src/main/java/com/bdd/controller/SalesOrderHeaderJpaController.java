/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdd.controller;

import com.bdd.controller.exceptions.IllegalOrphanException;
import com.bdd.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.bdd.model.SalesOrderDetail;
import com.bdd.model.SalesOrderHeader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author CDLCS
 */
public class SalesOrderHeaderJpaController implements Serializable {

    public SalesOrderHeaderJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalesOrderHeader salesOrderHeader) {
        if (salesOrderHeader.getSalesOrderDetailCollection() == null) {
            salesOrderHeader.setSalesOrderDetailCollection(new ArrayList<SalesOrderDetail>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SalesOrderDetail> attachedSalesOrderDetailCollection = new ArrayList<SalesOrderDetail>();
            for (SalesOrderDetail salesOrderDetailCollectionSalesOrderDetailToAttach : salesOrderHeader.getSalesOrderDetailCollection()) {
                salesOrderDetailCollectionSalesOrderDetailToAttach = em.getReference(salesOrderDetailCollectionSalesOrderDetailToAttach.getClass(), salesOrderDetailCollectionSalesOrderDetailToAttach.getSalesOrderDetailPK());
                attachedSalesOrderDetailCollection.add(salesOrderDetailCollectionSalesOrderDetailToAttach);
            }
            salesOrderHeader.setSalesOrderDetailCollection(attachedSalesOrderDetailCollection);
            em.persist(salesOrderHeader);
            for (SalesOrderDetail salesOrderDetailCollectionSalesOrderDetail : salesOrderHeader.getSalesOrderDetailCollection()) {
                SalesOrderHeader oldSalesOrderHeaderOfSalesOrderDetailCollectionSalesOrderDetail = salesOrderDetailCollectionSalesOrderDetail.getSalesOrderHeader();
                salesOrderDetailCollectionSalesOrderDetail.setSalesOrderHeader(salesOrderHeader);
                salesOrderDetailCollectionSalesOrderDetail = em.merge(salesOrderDetailCollectionSalesOrderDetail);
                if (oldSalesOrderHeaderOfSalesOrderDetailCollectionSalesOrderDetail != null) {
                    oldSalesOrderHeaderOfSalesOrderDetailCollectionSalesOrderDetail.getSalesOrderDetailCollection().remove(salesOrderDetailCollectionSalesOrderDetail);
                    oldSalesOrderHeaderOfSalesOrderDetailCollectionSalesOrderDetail = em.merge(oldSalesOrderHeaderOfSalesOrderDetailCollectionSalesOrderDetail);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalesOrderHeader salesOrderHeader) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalesOrderHeader persistentSalesOrderHeader = em.find(SalesOrderHeader.class, salesOrderHeader.getSalesOrderID());
            Collection<SalesOrderDetail> salesOrderDetailCollectionOld = persistentSalesOrderHeader.getSalesOrderDetailCollection();
            Collection<SalesOrderDetail> salesOrderDetailCollectionNew = salesOrderHeader.getSalesOrderDetailCollection();
            List<String> illegalOrphanMessages = null;
            for (SalesOrderDetail salesOrderDetailCollectionOldSalesOrderDetail : salesOrderDetailCollectionOld) {
                if (!salesOrderDetailCollectionNew.contains(salesOrderDetailCollectionOldSalesOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SalesOrderDetail " + salesOrderDetailCollectionOldSalesOrderDetail + " since its salesOrderHeader field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<SalesOrderDetail> attachedSalesOrderDetailCollectionNew = new ArrayList<SalesOrderDetail>();
            for (SalesOrderDetail salesOrderDetailCollectionNewSalesOrderDetailToAttach : salesOrderDetailCollectionNew) {
                salesOrderDetailCollectionNewSalesOrderDetailToAttach = em.getReference(salesOrderDetailCollectionNewSalesOrderDetailToAttach.getClass(), salesOrderDetailCollectionNewSalesOrderDetailToAttach.getSalesOrderDetailPK());
                attachedSalesOrderDetailCollectionNew.add(salesOrderDetailCollectionNewSalesOrderDetailToAttach);
            }
            salesOrderDetailCollectionNew = attachedSalesOrderDetailCollectionNew;
            salesOrderHeader.setSalesOrderDetailCollection(salesOrderDetailCollectionNew);
            salesOrderHeader = em.merge(salesOrderHeader);
            for (SalesOrderDetail salesOrderDetailCollectionNewSalesOrderDetail : salesOrderDetailCollectionNew) {
                if (!salesOrderDetailCollectionOld.contains(salesOrderDetailCollectionNewSalesOrderDetail)) {
                    SalesOrderHeader oldSalesOrderHeaderOfSalesOrderDetailCollectionNewSalesOrderDetail = salesOrderDetailCollectionNewSalesOrderDetail.getSalesOrderHeader();
                    salesOrderDetailCollectionNewSalesOrderDetail.setSalesOrderHeader(salesOrderHeader);
                    salesOrderDetailCollectionNewSalesOrderDetail = em.merge(salesOrderDetailCollectionNewSalesOrderDetail);
                    if (oldSalesOrderHeaderOfSalesOrderDetailCollectionNewSalesOrderDetail != null && !oldSalesOrderHeaderOfSalesOrderDetailCollectionNewSalesOrderDetail.equals(salesOrderHeader)) {
                        oldSalesOrderHeaderOfSalesOrderDetailCollectionNewSalesOrderDetail.getSalesOrderDetailCollection().remove(salesOrderDetailCollectionNewSalesOrderDetail);
                        oldSalesOrderHeaderOfSalesOrderDetailCollectionNewSalesOrderDetail = em.merge(oldSalesOrderHeaderOfSalesOrderDetailCollectionNewSalesOrderDetail);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salesOrderHeader.getSalesOrderID();
                if (findSalesOrderHeader(id) == null) {
                    throw new NonexistentEntityException("The salesOrderHeader with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SalesOrderHeader salesOrderHeader;
            try {
                salesOrderHeader = em.getReference(SalesOrderHeader.class, id);
                salesOrderHeader.getSalesOrderID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salesOrderHeader with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SalesOrderDetail> salesOrderDetailCollectionOrphanCheck = salesOrderHeader.getSalesOrderDetailCollection();
            for (SalesOrderDetail salesOrderDetailCollectionOrphanCheckSalesOrderDetail : salesOrderDetailCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SalesOrderHeader (" + salesOrderHeader + ") cannot be destroyed since the SalesOrderDetail " + salesOrderDetailCollectionOrphanCheckSalesOrderDetail + " in its salesOrderDetailCollection field has a non-nullable salesOrderHeader field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(salesOrderHeader);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SalesOrderHeader> findSalesOrderHeaderEntities() {
        return findSalesOrderHeaderEntities(true, -1, -1);
    }

    public List<SalesOrderHeader> findSalesOrderHeaderEntities(int maxResults, int firstResult) {
        return findSalesOrderHeaderEntities(false, maxResults, firstResult);
    }

    private List<SalesOrderHeader> findSalesOrderHeaderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalesOrderHeader.class));
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

    public SalesOrderHeader findSalesOrderHeader(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalesOrderHeader.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalesOrderHeaderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalesOrderHeader> rt = cq.from(SalesOrderHeader.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
