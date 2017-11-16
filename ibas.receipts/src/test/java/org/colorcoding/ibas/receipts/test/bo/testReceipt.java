package org.colorcoding.ibas.receipts.test.bo;

import org.colorcoding.ibas.bobas.common.ICriteria;
import org.colorcoding.ibas.bobas.common.IOperationResult;
import org.colorcoding.ibas.businesspartner.data.emBusinessPartnerType;
import org.colorcoding.ibas.receipts.bo.receipt.IReceiptItem;
import org.colorcoding.ibas.receipts.bo.receipt.Receipt;
import org.colorcoding.ibas.receipts.repository.BORepositoryReceipts;
import org.colorcoding.ibas.receipts.repository.IBORepositoryReceiptsApp;

import junit.framework.TestCase;

/**
 * 收款 测试
 * 
 */
public class testReceipt extends TestCase {
	/**
	 * 获取连接口令
	 */
	String getToken() {
		return "68fc6bac014d06ad94c5734116487cff";
	}

	/**
	 * 基本项目测试
	 * 
	 * @throws Exception
	 */
	public void testBasicItems() throws Exception {
		// 创建收款对象
		Receipt bo = new Receipt();
		// 测试属性赋值
		bo.setBusinessPartnerCode("C0002");
		bo.setBusinessPartnerName("奥维思博");
		bo.setBusinessPartnerType(emBusinessPartnerType.SUPPLIER);
		// 测试收款-项目
		IReceiptItem receiptitem = bo.getReceiptItems().create();
		// 测试属性赋值
		receiptitem.setAmount(500);

		// 测试对象的保存和查询
		IOperationResult<?> operationResult = null;
		ICriteria criteria = null;
		IBORepositoryReceiptsApp boRepository = new BORepositoryReceipts();

		// 设置用户口令
		boRepository.setUserToken(this.getToken());

		// 测试保存
		operationResult = boRepository.saveReceipt(bo);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);
		Receipt boSaved = (Receipt) operationResult.getResultObjects().firstOrDefault();

		// 测试查询
		criteria = boSaved.getCriteria();
		criteria.setResultCount(10);
		operationResult = boRepository.fetchReceipt(criteria);
		assertEquals(operationResult.getMessage(), operationResult.getResultCode(), 0);

	}

}
