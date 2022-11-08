/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.bdd.jpaadventure;

import com.bdd.controller.SalesOrderDetailJpaController;
import com.bdd.controller.SalesOrderHeaderJpaController;
import com.bdd.model.SalesOrderDetail;
import com.bdd.model.SalesOrderHeader;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author CDLCS
 */
public class JpaAdventure {

    public static void main(String[] args) {
       SalesOrderHeader soh = null;
       List<SalesOrderDetail> sodProducts = null;
              
       EntityManagerFactory emf;
       
       try {
           emf = Persistence.createEntityManagerFactory("jpaAdventure");
       }catch(Throwable ex){
           throw new RuntimeException("Error al crear instancia a la Unidad de Persistencia"+ex.toString());
       }
       
       EntityManager em = emf.createEntityManager();
       
       EntityTransaction tx = em.getTransaction();

       tx.begin();
       
       SalesOrderHeaderJpaController order = new SalesOrderHeaderJpaController(emf);
       SalesOrderDetailJpaController sod = new SalesOrderDetailJpaController(emf);
       
       soh = order.findSalesOrderHeader(43659);
       System.out.println("************************************");
       System.out.println("Order ID Header ... " + soh.getSalesOrderID());
       System.out.println(soh.getOrderDate());
       System.out.println(soh.getSubTotal());
    
       
       sodProducts = sod.findSalesOrderDetailEntities();
       
       System.out.println("Entro ....");  
       
       if (sodProducts != null) {
        sodProducts.forEach(so -> {
           if (so.getSalesOrderDetailPK().getSalesOrderID() == 43659 ){
             System.out.println("*******************************");  
             System.out.println("Order ID ..." + so.getSalesOrderHeader().getSalesOrderID());
             System.out.println("Order Qty ... " + so.getOrderQty());
           }
        });
       }
       
       
       tx.commit();
       em.close();
       
       
    }
}
