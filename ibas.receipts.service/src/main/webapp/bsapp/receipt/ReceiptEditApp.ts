/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "../../borep/bo/index";
import { BORepositoryReceipts } from "../../borep/BORepositories";
import { BO_CODE_SUPPLIER, ISupplier} from "../../3rdparty/businesspartner/index";
import { emBusinessPartnerType } from "3rdparty/businesspartner/Datas";

/** 编辑应用-收款 */
export class ReceiptEditApp extends ibas.BOEditApplication<IReceiptEditView, bo.Receipt> {

    /** 应用标识 */
    static APPLICATION_ID: string = "1e3ec36b-931b-430a-b81a-781836e83d1c";
    /** 应用名称 */
    static APPLICATION_NAME: string = "receipts_app_receipt_edit";
    /** 业务对象编码 */
    static BUSINESS_OBJECT_CODE: string = bo.Receipt.BUSINESS_OBJECT_CODE;
    /** 构造函数 */
    constructor() {
        super();
        this.id = ReceiptEditApp.APPLICATION_ID;
        this.name = ReceiptEditApp.APPLICATION_NAME;
        this.boCode = ReceiptEditApp.BUSINESS_OBJECT_CODE;
        this.description = ibas.i18n.prop(this.name);
    }
    /** 注册视图 */
    protected registerView(): void {
        super.registerView();
        // 其他事件
        this.view.deleteDataEvent = this.deleteData;
        this.view.createDataEvent = this.createData;
        this.view.addReceiptItemEvent = this.addReceiptItem;
        this.view.removeReceiptItemEvent = this.removeReceiptItem;
        this.view.chooseReceiptPartnerEvent = this.chooseReceiptPartner;
    }
    /** 视图显示后 */
    protected viewShowed(): void {
        // 视图加载完成
        if (ibas.objects.isNull(this.editData)) {
            // 创建编辑对象实例
            this.editData = new bo.Receipt();
            this.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_created_new"));
        }
        this.view.showReceipt(this.editData);
        this.view.showReceiptItems(this.editData.receiptItems.filterDeleted());
    }
    /** 运行,覆盖原方法 */
    run(...args: any[]): void {
        let that: this = this;
        if (ibas.objects.instanceOf(arguments[0], bo.Receipt)) {
            // 尝试重新查询编辑对象
            let criteria: ibas.ICriteria = arguments[0].criteria();
            if (!ibas.objects.isNull(criteria) && criteria.conditions.length > 0) {
                // 有效的查询对象查询
                let boRepository: BORepositoryReceipts = new BORepositoryReceipts();
                boRepository.fetchReceipt({
                    criteria: criteria,
                    onCompleted(opRslt: ibas.IOperationResult<bo.Receipt>): void {
                        let data: bo.Receipt;
                        if (opRslt.resultCode === 0) {
                            data = opRslt.resultObjects.firstOrDefault();
                        }
                        if (ibas.objects.instanceOf(data, bo.Receipt)) {
                            // 查询到了有效数据
                            that.editData = data;
                            that.show();
                        } else {
                            // 数据重新检索无效
                            that.messages({
                                type: ibas.emMessageType.WARNING,
                                message: ibas.i18n.prop("shell_data_deleted_and_created"),
                                onCompleted(): void {
                                    that.show();
                                }
                            });
                        }
                    }
                });
                // 开始查询数据
                return;
            }
        }
        super.run();
    }
    /** 待编辑的数据 */
    protected editData: bo.Receipt;
    /** 保存数据 */
    protected saveData(): void {
        let that: this = this;
        let boRepository: BORepositoryReceipts = new BORepositoryReceipts();
        boRepository.saveReceipt({
            beSaved: this.editData,
            onCompleted(opRslt: ibas.IOperationResult<bo.Receipt>): void {
                try {
                    that.busy(false);
                    if (opRslt.resultCode !== 0) {
                        throw new Error(opRslt.message);
                    }
                    if (opRslt.resultObjects.length === 0) {
                        // 删除成功，释放当前对象
                        that.messages(ibas.emMessageType.SUCCESS,
                            ibas.i18n.prop("shell_data_delete") + ibas.i18n.prop("shell_sucessful"));
                        that.editData = undefined;
                    } else {
                        // 替换编辑对象
                        that.editData = opRslt.resultObjects.firstOrDefault();
                        that.messages(ibas.emMessageType.SUCCESS,
                            ibas.i18n.prop("shell_data_save") + ibas.i18n.prop("shell_sucessful"));
                    }
                    // 刷新当前视图
                    that.viewShowed();
                } catch (error) {
                    that.messages(error);
                }
            }
        });
        this.busy(true);
        this.proceeding(ibas.emMessageType.INFORMATION, ibas.i18n.prop("shell_saving_data"));
    }
    /** 删除数据 */
    protected deleteData(): void {
        let that: this = this;
        this.messages({
            type: ibas.emMessageType.QUESTION,
            title: ibas.i18n.prop(this.name),
            message: ibas.i18n.prop("sys_whether_to_delete"),
            actions: [ibas.emMessageAction.YES, ibas.emMessageAction.NO],
            onCompleted(action: ibas.emMessageAction): void {
                if (action === ibas.emMessageAction.YES) {
                    that.editData.delete();
                    that.saveData();
                }
            }
        });
    }
    /** 新建数据，参数1：是否克隆 */
    protected createData(clone: boolean): void {
        let that: this = this;
        let createData: Function = function (): void {
            if (clone) {
                // 克隆对象
                that.editData = that.editData.clone();
                that.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_cloned_new"));
                that.viewShowed();
            } else {
                // 新建对象
                that.editData = new bo.Receipt();
                that.proceeding(ibas.emMessageType.WARNING, ibas.i18n.prop("shell_data_created_new"));
                that.viewShowed();
            }
        };
        if (that.editData.isDirty) {
            this.messages({
                type: ibas.emMessageType.QUESTION,
                title: ibas.i18n.prop(this.name),
                message: ibas.i18n.prop("sys_data_not_saved_whether_to_continue"),
                actions: [ibas.emMessageAction.YES, ibas.emMessageAction.NO],
                onCompleted(action: ibas.emMessageAction): void {
                    if (action === ibas.emMessageAction.YES) {
                        createData();
                    }
                }
            });
        } else {
            createData();
        }
    }
    /** 添加收款-项目事件 */
    addReceiptItem(): void {
        this.editData.receiptItems.create();
        // 仅显示没有标记删除的
        this.view.showReceiptItems(this.editData.receiptItems.filterDeleted());
    }
    /** 删除收款-项目事件 */
    removeReceiptItem(items: bo.ReceiptItem[]): void {
        // 非数组，转为数组
        if (!(items instanceof Array)) {
            items = [items];
        }
        if (items.length === 0) {
            return;
        }
        // 移除项目
        for (let item of items) {
            if (this.editData.receiptItems.indexOf(item) >= 0) {
                if (item.isNew) {
                    // 新建的移除集合
                    this.editData.receiptItems.remove(item);
                } else {
                    // 非新建标记删除
                    item.delete();
                }
            }
        }
        // 仅显示没有标记删除的
        this.view.showReceiptItems(this.editData.receiptItems.filterDeleted());
    }

    /** 选择收款供应商事件 */
    private chooseReceiptPartner(): void {
        let that: this = this;
        ibas.servicesManager.runChooseService<ISupplier>({
            boCode: BO_CODE_SUPPLIER,
            criteria: [
                new ibas.Condition(BO_CODE_SUPPLIER,
                    ibas.emConditionOperation.NOT_EQUAL, ibas.strings.valueOf(this.editData.businessPartnerCode)),
            ],
            onCompleted(selecteds: ibas.List<ISupplier>): void {
                that.editData.businessPartnerCode = selecteds.firstOrDefault().code;
                that.editData.businessPartnerName = selecteds.firstOrDefault().name;
                that.editData.businessPartnerType = emBusinessPartnerType.SUPPLIER;
            }
        });
    }
}
/** 视图-收款 */
export interface IReceiptEditView extends ibas.IBOEditView {
    /** 显示数据 */
    showReceipt(data: bo.Receipt): void;
    /** 删除数据事件 */
    deleteDataEvent: Function;
    /** 新建数据事件，参数1：是否克隆 */
    createDataEvent: Function;
    /** 添加收款-项目事件 */
    addReceiptItemEvent: Function;
    /** 删除收款-项目事件 */
    removeReceiptItemEvent: Function;
    /** 选择收款客户事件 */
    chooseReceiptPartnerEvent: Function;
    /** 显示数据 */
    showReceiptItems(datas: bo.ReceiptItem[]): void;
}
