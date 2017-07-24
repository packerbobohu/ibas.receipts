package org.colorcoding.ibas.receipts.service.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.receipts.repository.*;
import org.colorcoding.ibas.receipts.bo.receipt.*;

/**
* Receipts 数据服务JSON
*/
@Path("data")
public class DataService extends BORepositoryReceipts {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-收款
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("fetchReceipt")
    public OperationResult<Receipt> fetchReceipt(Criteria criteria, @QueryParam("token") String token) {
        return super.fetchReceipt(criteria, token);
    }

    /**
     * 保存-收款
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("saveReceipt")
    public OperationResult<Receipt> saveReceipt(Receipt bo, @QueryParam("token") String token) {
        return super.saveReceipt(bo, token);
    }

    //--------------------------------------------------------------------------------------------//

}
