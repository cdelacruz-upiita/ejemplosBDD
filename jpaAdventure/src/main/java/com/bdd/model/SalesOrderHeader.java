/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bdd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author CDLCS
 */
@Entity
@Table(name = "SalesOrderHeader", schema="sales")
public class SalesOrderHeader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SalesOrderID")
    private Integer salesOrderID;
    @Basic(optional = false)
    @Column(name = "RevisionNumber")
    private short revisionNumber;
    @Basic(optional = false)
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Basic(optional = false)
    @Column(name = "DueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Column(name = "ShipDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipDate;
    @Basic(optional = false)
    @Column(name = "Status")
    private short status;
    @Basic(optional = false)
    @Column(name = "OnlineOrderFlag")
    private boolean onlineOrderFlag;
    @Basic(optional = false)
    @Column(name = "SalesOrderNumber")
    private String salesOrderNumber;
    @Column(name = "PurchaseOrderNumber")
    private String purchaseOrderNumber;
    @Column(name = "AccountNumber")
    private String accountNumber;
    @Basic(optional = false)
    @Column(name = "BillToAddressID")
    private int billToAddressID;
    @Basic(optional = false)
    @Column(name = "ShipToAddressID")
    private int shipToAddressID;
    @Basic(optional = false)
    @Column(name = "ShipMethodID")
    private int shipMethodID;
    @Column(name = "CreditCardApprovalCode")
    private String creditCardApprovalCode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "SubTotal")
    private BigDecimal subTotal;
    @Basic(optional = false)
    @Column(name = "TaxAmt")
    private BigDecimal taxAmt;
    @Basic(optional = false)
    @Column(name = "Freight")
    private BigDecimal freight;
    @Basic(optional = false)
    @Column(name = "TotalDue")
    private BigDecimal totalDue;
    @Column(name = "Comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "rowguid")
    private String rowguid;
    @Basic(optional = false)
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesOrderHeader")
    private Collection<SalesOrderDetail> salesOrderDetailCollection;

    public SalesOrderHeader() {
    }

    public SalesOrderHeader(Integer salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public SalesOrderHeader(Integer salesOrderID, short revisionNumber, Date orderDate, Date dueDate, short status, boolean onlineOrderFlag, String salesOrderNumber, int billToAddressID, int shipToAddressID, int shipMethodID, BigDecimal subTotal, BigDecimal taxAmt, BigDecimal freight, BigDecimal totalDue, String rowguid, Date modifiedDate) {
        this.salesOrderID = salesOrderID;
        this.revisionNumber = revisionNumber;
        this.orderDate = orderDate;
        this.dueDate = dueDate;
        this.status = status;
        this.onlineOrderFlag = onlineOrderFlag;
        this.salesOrderNumber = salesOrderNumber;
        this.billToAddressID = billToAddressID;
        this.shipToAddressID = shipToAddressID;
        this.shipMethodID = shipMethodID;
        this.subTotal = subTotal;
        this.taxAmt = taxAmt;
        this.freight = freight;
        this.totalDue = totalDue;
        this.rowguid = rowguid;
        this.modifiedDate = modifiedDate;
    }

    public Integer getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(Integer salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public short getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(short revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public boolean getOnlineOrderFlag() {
        return onlineOrderFlag;
    }

    public void setOnlineOrderFlag(boolean onlineOrderFlag) {
        this.onlineOrderFlag = onlineOrderFlag;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBillToAddressID() {
        return billToAddressID;
    }

    public void setBillToAddressID(int billToAddressID) {
        this.billToAddressID = billToAddressID;
    }

    public int getShipToAddressID() {
        return shipToAddressID;
    }

    public void setShipToAddressID(int shipToAddressID) {
        this.shipToAddressID = shipToAddressID;
    }

    public int getShipMethodID() {
        return shipMethodID;
    }

    public void setShipMethodID(int shipMethodID) {
        this.shipMethodID = shipMethodID;
    }

    public String getCreditCardApprovalCode() {
        return creditCardApprovalCode;
    }

    public void setCreditCardApprovalCode(String creditCardApprovalCode) {
        this.creditCardApprovalCode = creditCardApprovalCode;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRowguid() {
        return rowguid;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Collection<SalesOrderDetail> getSalesOrderDetailCollection() {
        return salesOrderDetailCollection;
    }

    public void setSalesOrderDetailCollection(Collection<SalesOrderDetail> salesOrderDetailCollection) {
        this.salesOrderDetailCollection = salesOrderDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salesOrderID != null ? salesOrderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesOrderHeader)) {
            return false;
        }
        SalesOrderHeader other = (SalesOrderHeader) object;
        if ((this.salesOrderID == null && other.salesOrderID != null) || (this.salesOrderID != null && !this.salesOrderID.equals(other.salesOrderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bdd.model.SalesOrderHeader[ salesOrderID=" + salesOrderID + " ]";
    }
    
}
