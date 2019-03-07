/**
 * Project Name:regulus-account-provider
 * Date:2018年1月26日上午11:30:25
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */
package org.regulus.common.plugin.provider;

import org.regulus.common.plugin.model.PageResultModel;

/**
 * ClassName: BaseProvider <br/>
 * Function: 基类provider<br/>
 * date: 2018年1月26日 下午3:14:41 <br/>
 *
 * @author shijun@richinfo.cn
 * @version @param <T>
 * @version @param <PK>
 * @since V1.0
 */
public interface BaseProvider<T, PK extends java.io.Serializable> {
    
    public T findByPrimaryKey(PK pk);
    
    public PageResultModel<T> selectWithPage(T t, int pageNum, int pageSize);
    
    public void insertSelective(T t);
    
    public void updateByPrimaryKeySelective(T t);
    
    public void deleteByPrimaryKey(PK pk);
}

