package org.colorcoding.ibas.receipts.repository;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.bobas.common.OperationResult;
import org.colorcoding.ibas.bobas.repository.BORepositoryServiceApplication;
import org.colorcoding.ibas.receipts.bo.receipt.IReceipt;
import org.colorcoding.ibas.receipts.bo.receipt.Receipt;

/**
 * Receipts仓库
 */
public class BORepositoryReceipts extends BORepositoryServiceApplication
		implements IBORepositoryReceiptsSvc, IBORepositoryReceiptsApp {

	// --------------------------------------------------------------------------------------------//
	/**
	 * 查询-收款
	 * 
	 * @param criteria
	 *            查询
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<Receipt> fetchReceipt(ICriteria criteria, String token) {
		return super.fetch(criteria, token, Receipt.class);
	}

	/**
	 * 查询-收款（提前设置用户口令）
	 * 
	 * @param criteria
	 *            查询
	 * @return 操作结果
	 */
	public IOperationResult<IReceipt> fetchReceipt(ICriteria criteria) {
		return new OperationResult<IReceipt>(this.fetchReceipt(criteria, this.getUserToken()));
	}

	/**
	 * 保存-收款
	 * 
	 * @param bo
	 *            对象实例
	 * @param token
	 *            口令
	 * @return 操作结果
	 */
	public OperationResult<Receipt> saveReceipt(Receipt bo, String token) {
		return super.save(bo, token);
	}

	/**
	 * 保存-收款（提前设置用户口令）
	 * 
	 * @param bo
	 *            对象实例
	 * @return 操作结果
	 */
	public IOperationResult<IReceipt> saveReceipt(IReceipt bo) {
		return new OperationResult<IReceipt>(this.saveReceipt((Receipt) bo, this.getUserToken()));
	}

	// --------------------------------------------------------------------------------------------//

}
