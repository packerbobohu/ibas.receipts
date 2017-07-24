package org.colorcoding.ibas.receipts.service.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.cxf.WebServicePath;
import org.colorcoding.ibas.receipts.repository.*;
import org.colorcoding.ibas.receipts.bo.receipt.*;

/**
* Receipts 数据服务JSON
*/
@WebService
@WebServicePath("data")
public class DataService extends BORepositoryReceipts {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-收款
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Receipt> fetchReceipt(@WebParam(name = "criteria") Criteria criteria, @WebParam(name = "token") String token) {
        return super.fetchReceipt(criteria, token);
    }

    /**
     * 保存-收款
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @WebMethod
    public OperationResult<Receipt> saveReceipt(@WebParam(name = "bo") Receipt bo, @WebParam(name = "token") String token) {
        return super.saveReceipt(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
