package org.colorcoding.ibas.receipts.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.receipts.bo.receipt.*;

/**
* Receipts仓库应用
*/
public interface IBORepositoryReceiptsApp extends IBORepositoryApplication {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-收款
     * @param criteria 查询
     * @return 操作结果
     */
    IOperationResult<IReceipt> fetchReceipt(ICriteria criteria);

    /**
     * 保存-收款
     * @param bo 对象实例
     * @return 操作结果
     */
    IOperationResult<IReceipt> saveReceipt(IReceipt bo);

    //--------------------------------------------------------------------------------------------//

}
