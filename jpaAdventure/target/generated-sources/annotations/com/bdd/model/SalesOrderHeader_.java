package com.bdd.model;

import com.bdd.model.SalesOrderDetail;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-11-08T10:47:42", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(SalesOrderHeader.class)
public class SalesOrderHeader_ { 

    public static volatile SingularAttribute<SalesOrderHeader, Integer> salesOrderID;
    public static volatile SingularAttribute<SalesOrderHeader, Boolean> onlineOrderFlag;
    public static volatile CollectionAttribute<SalesOrderHeader, SalesOrderDetail> salesOrderDetailCollection;
    public static volatile SingularAttribute<SalesOrderHeader, Short> revisionNumber;
    public static volatile SingularAttribute<SalesOrderHeader, Date> dueDate;
    public static volatile SingularAttribute<SalesOrderHeader, BigDecimal> freight;
    public static volatile SingularAttribute<SalesOrderHeader, BigDecimal> totalDue;
    public static volatile SingularAttribute<SalesOrderHeader, Integer> billToAddressID;
    public static volatile SingularAttribute<SalesOrderHeader, BigDecimal> subTotal;
    public static volatile SingularAttribute<SalesOrderHeader, Date> shipDate;
    public static volatile SingularAttribute<SalesOrderHeader, String> accountNumber;
    public static volatile SingularAttribute<SalesOrderHeader, Integer> shipToAddressID;
    public static volatile SingularAttribute<SalesOrderHeader, Integer> shipMethodID;
    public static volatile SingularAttribute<SalesOrderHeader, String> rowguid;
    public static volatile SingularAttribute<SalesOrderHeader, String> creditCardApprovalCode;
    public static volatile SingularAttribute<SalesOrderHeader, String> purchaseOrderNumber;
    public static volatile SingularAttribute<SalesOrderHeader, Date> modifiedDate;
    public static volatile SingularAttribute<SalesOrderHeader, String> comment;
    public static volatile SingularAttribute<SalesOrderHeader, String> salesOrderNumber;
    public static volatile SingularAttribute<SalesOrderHeader, Date> orderDate;
    public static volatile SingularAttribute<SalesOrderHeader, BigDecimal> taxAmt;
    public static volatile SingularAttribute<SalesOrderHeader, Short> status;

}