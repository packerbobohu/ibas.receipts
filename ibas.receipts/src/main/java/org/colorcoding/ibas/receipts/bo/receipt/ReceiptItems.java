package org.colorcoding.ibas.receipts.bo.receipt;

import java.beans.PropertyChangeEvent;
import javax.xml.bind.annotation.*;
import org.colorcoding.ibas.bobas.common.*;
import org.colorcoding.ibas.bobas.bo.*;
import org.colorcoding.ibas.receipts.MyConfiguration;

/**
* 收款-项目 集合
*/
@XmlType(name = ReceiptItems.BUSINESS_OBJECT_NAME, namespace = MyConfiguration.NAMESPACE_BO)
@XmlSeeAlso({ ReceiptItem.class })
public class ReceiptItems extends BusinessObjects<IReceiptItem, IReceipt> implements IReceiptItems {

    /**
    * 业务对象名称
    */
    public static final String BUSINESS_OBJECT_NAME = "ReceiptItems";

    /**
     * 序列化版本标记
     */
    private static final long serialVersionUID = -2436807999692235876L;

    /**
     * 构造方法
     */
    public ReceiptItems() {
        super();
    }

    /**
     * 构造方法
     * @param parent 父项对象
     */
    public ReceiptItems(IReceipt parent) {
        super(parent);
    }

    /**
     * 元素类型
     */
    public Class<?> getElementType() {
        return ReceiptItem.class;
    }

    /**
    * 创建收款-项目
    * 
    * @return 收款-项目
    */
    public IReceiptItem create() {
        IReceiptItem item = new ReceiptItem();
        if (this.add(item)) {
            return item;
        }
        return null;
    }

    @Override
    protected void afterAddItem(IReceiptItem item) {
        super.afterAddItem(item);
        // TODO 设置关联值
        if (this.getParent().getBusinessPartnerCode()!=null&&item.getBusinessPartnerCode()==null){
            item.setBusinessPartnerCode(this.getParent().getBusinessPartnerCode());
        }
    }

    @Override
    public ICriteria getElementCriteria() {
        ICriteria criteria = super.getElementCriteria();
        // TODO 添加关联查询条件
        return criteria;
    }

    @Override
    public void onParentPropertyChanged(PropertyChangeEvent evt) {
        super.onParentPropertyChanged(evt);
        // TODO 设置关联值
        if(evt.getPropertyName()==Receipt.PROPERTY_BUSINESSPARTNERCODE.getName()){
            for(IReceiptItem item : this){
                item.setBusinessPartnerCode(this.getParent().getBusinessPartnerCode());
            }
        }
    }
}
