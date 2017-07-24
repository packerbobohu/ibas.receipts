package org.colorcoding.ibas.receipts.repository;

import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.ownership.*;
import org.colorcoding.ibas.bobas.repository.*;
import org.colorcoding.ibas.receipts.bo.receipt.*;

/**
* Receipts仓库
*/
@PermissionGroup("Receipts")
public class BORepositoryReceipts extends BORepositoryServiceApplication implements IBORepositoryReceiptsSvc, IBORepositoryReceiptsApp {

    //--------------------------------------------------------------------------------------------//
    /**
     * 查询-收款
     * @param criteria 查询
     * @param token 口令
     * @return 操作结果
     */
    public OperationResult<Receipt> fetchReceipt(ICriteria criteria, String token) {
        return super.fetch(criteria, token, Receipt.class);
    }

    /**
     * 查询-收款（提前设置用户口令）
     * @param criteria 查询
     * @return 操作结果
     */
    public IOperationResult<IReceipt> fetchReceipt(ICriteria criteria) {
        return new OperationResult<IReceipt>(this.fetchReceipt(criteria, this.getUserToken()));
    }

    /**
     * 保存-收款
     * @param bo 对象实例
     * @param token 口令
     * @return 操作结果
     */
    public OperationResult<Receipt> saveReceipt(Receipt bo, String token) {
        return super.save(bo, token);
    }

    /**
     * 保存-收款（提前设置用户口令）
     * @param bo 对象实例
     * @return 操作结果
     */
    public IOperationResult<IReceipt> saveReceipt(IReceipt bo) {
        return new OperationResult<IReceipt>(this.saveReceipt((Receipt) bo, this.getUserToken()));
    }

    //--------------------------------------------------------------------------------------------//

}
