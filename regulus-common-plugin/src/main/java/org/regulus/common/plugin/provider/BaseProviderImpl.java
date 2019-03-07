/**
 * Project Name:regulus-account-provider
 * Date:2018年1月26日上午11:30:25
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */
package org.regulus.common.plugin.provider;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.regulus.common.plugin.service.BaseService;
import org.regulus.common.plugin.model.PageResultModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: BaseProviderImpl <br/>
 * Function: 基类provider<br/>
 * date: 2018年1月26日 下午3:14:41 <br/>
 *
 * @author shijun@richinfo.cn
 * @version @param <T>
 * @version @param <PK>
 * @since V1.0
 */
public class BaseProviderImpl<T ,PK extends java.io.Serializable> implements BaseProvider<T, PK> {

    @Autowired
    private BaseService<T, PK> baseService ;

    public BaseService<T, PK> getBaseService(){
        return baseService;
    }
    
    @Override
    public T findByPrimaryKey(PK pk) {
        
        return getBaseService().findByPrimaryKey(pk);
    }

    @Override
    public PageResultModel<T> selectWithPage(T t, int pageNum, int pageSize) {
        PageInfo<T> pageInfo =
                PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> getBaseService().select(t));
        return new PageResultModel<T>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void insertSelective(T t) {
        
        getBaseService().insertSelective(t);        
    }

    @Override
    public void updateByPrimaryKeySelective(T t) {
        
        getBaseService().updateByPrimaryKeySelective(t);
    }

    @Override
    public void deleteByPrimaryKey(PK pk) {
        
        getBaseService().deleteByPrimaryKey(pk);
    }
}

