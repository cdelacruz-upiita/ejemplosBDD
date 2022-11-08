package com.bdd.model;

import com.bdd.model.SalesOrderDetailPK;
import com.bdd.model.SalesOrderHeader;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-11-08T10:47:42", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(SalesOrderDetail.class)
public class SalesOrderDetail_ { 

    public static volatile SingularAttribute<SalesOrderDetail, BigDecimal> unitPrice;
    public static volatile SingularAttribute<SalesOrderDetail, BigDecimal> unitPriceDiscount;
    public static volatile SingularAttribute<SalesOrderDetail, BigDecimal> lineTotal;
    public static volatile SingularAttribute<SalesOrderDetail, Date> modifiedDate;
    public static volatile SingularAttribute<SalesOrderDetail, String> carrierTrackingNumber;
    public static volatile SingularAttribute<SalesOrderDetail, Short> orderQty;
    public static volatile SingularAttribute<SalesOrderDetail, SalesOrderDetailPK> salesOrderDetailPK;
    public static volatile SingularAttribute<SalesOrderDetail, SalesOrderHeader> salesOrderHeader;
    public static volatile SingularAttribute<SalesOrderDetail, String> rowguid;

}