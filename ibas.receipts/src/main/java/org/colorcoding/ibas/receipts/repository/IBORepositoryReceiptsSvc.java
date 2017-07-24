package org.colorcoding.ibas.receipts.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.receipts.bo.receipt.*;

/**
* Receipts仓库服务
*/
public interface IBORepositoryReceiptsSvc extends IBORepositorySmartService {


    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-收款
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Receipt> fetchReceipt(ICriteria criteria, String token);

    /**
     * 保存-收款
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    OperationResult<Receipt> saveReceipt(Receipt bo, String token);

    //--------------------------------------------------------------------------------------------//

}
