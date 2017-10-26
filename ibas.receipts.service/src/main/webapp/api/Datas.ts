/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

// 共享的数据
import {
} from "ibas/index";

/** 业务仓库名称 */
export const BO_REPOSITORY_RECEIPTS: string = "BORepositoryReceipts";
/** 业务对象编码-收款 */
export const BO_CODE_RECEIPT: string = "${Company}_RP_RECEIPT";
/** 业务伙伴类型 */
export enum emBusinessPartnerType {

    /** 客户 */
    CUSTOMER,
    /** 供应商 */
    SUPPLIER,
}
