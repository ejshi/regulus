/**
 * Project Name:regulus-account-provider
 * Date:2018年1月26日上午11:30:25
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 */
package org.regulus.account.provider.common;

import org.regulus.account.api.common.BaseProvider;
import org.regulus.common.model.PageResultModel;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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
public abstract class BaseProviderImpl<T ,PK extends java.io.Serializable> implements BaseProvider<T, PK> {

//    @Autowired
//    private BaseService<T, PK> baseService ;
    
    public abstract BaseService<T, PK> getBaseService(); 
    
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

