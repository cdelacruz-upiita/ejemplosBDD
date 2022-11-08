/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdd.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author CDLCS
 */
@Embeddable
public class SalesOrderDetailPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "SalesOrderID")
    private int salesOrderID;
    @Basic(optional = false)
    @Column(name = "SalesOrderDetailID")
    private int salesOrderDetailID;

    public SalesOrderDetailPK() {
    }

    public SalesOrderDetailPK(int salesOrderID, int salesOrderDetailID) {
        this.salesOrderID = salesOrderID;
        this.salesOrderDetailID = salesOrderDetailID;
    }

    public int getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(int salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public int getSalesOrderDetailID() {
        return salesOrderDetailID;
    }

    public void setSalesOrderDetailID(int salesOrderDetailID) {
        this.salesOrderDetailID = salesOrderDetailID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) salesOrderID;
        hash += (int) salesOrderDetailID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesOrderDetailPK)) {
            return false;
        }
        SalesOrderDetailPK other = (SalesOrderDetailPK) object;
        if (this.salesOrderID != other.salesOrderID) {
            return false;
        }
        if (this.salesOrderDetailID != other.salesOrderDetailID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bdd.model.SalesOrderDetailPK[ salesOrderID=" + salesOrderID + ", salesOrderDetailID=" + salesOrderDetailID + " ]";
    }
    
}
