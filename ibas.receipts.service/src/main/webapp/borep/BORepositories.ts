/**
 * @license
 * Copyright color-coding studio. All Rights Reserved.
 *
 * Use of this source code is governed by an Apache License, Version 2.0
 * that can be found in the LICENSE file at http://www.apache.org/licenses/LICENSE-2.0
 */

import * as ibas from "ibas/index";
import * as bo from "./bo/index";
import { IBORepositoryReceipts, BO_REPOSITORY_RECEIPTS } from "../api/index";
import { DataConverter4rp } from "./DataConverters";

/** 业务对象仓库 */
export class BORepositoryReceipts extends ibas.BORepositoryApplication implements IBORepositoryReceipts {

    /** 创建此模块的后端与前端数据的转换者 */
    protected createConverter(): ibas.IDataConverter {
        return new DataConverter4rp;
    }

    /**
     * 查询 收款
     * @param fetcher 查询者
     */
    fetchReceipt(fetcher: ibas.FetchCaller<bo.Receipt>): void {
        super.fetch(bo.Receipt.name, fetcher);
    }
    /**
     * 保存 收款
     * @param saver 保存者
     */
    saveReceipt(saver: ibas.SaveCaller<bo.Receipt>): void {
        super.save(bo.Receipt.name, saver);
    }

}
// 注册业务对象仓库到工厂
ibas.boFactory.register(BO_REPOSITORY_RECEIPTS, BORepositoryReceipts);
