/**
 * Project Name:regulus-account-provider
 * File Name:TService.java
 * Package Name:org.regulus.account.provider.service
 * Date:2018年1月26日上午11:30:25
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.common.plugin.service;

import org.regulus.common.plugin.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * ClassName: BaseService <br/>
 * Function: 基类service<br/>
 * date: 2018年1月26日 下午3:14:41 <br/>
 *
 * @author shijun@richinfo.cn
 * @version @param <T>
 * @version @param <PK>
 * @since V1.0
 */
public class BaseService<T , PK extends java.io.Serializable> {

    //spring中泛型注入必须要使用Autowired
    @Autowired
    protected BaseMapper<T> baseMapper;
    
    public BaseMapper<T> getBaseMapper(){
        return baseMapper;
    }
    
    public T findByPrimaryKey(PK id){
        return getBaseMapper().selectByPrimaryKey(id);
    }
    
    public T findOneByExample(Example example){
        List<T> list = selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    public List<T> selectByExample(Example example){
        return getBaseMapper().selectByExample(example);
    }
    
    public List<T> select(T t){
        return getBaseMapper().select(t);
    }
    
    public List<T> selectByIds(String ids){
         return getBaseMapper().selectByIds(ids);
    }
    
    public int countByExample(Example example){
        return getBaseMapper().selectCountByExample(example);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(T t){
        getBaseMapper().insertSelective(t);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateByPrimaryKeySelective(T t){
        getBaseMapper().updateByPrimaryKeySelective(t);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateByExampleSelective(T t,Example example){
        getBaseMapper().updateByExampleSelective(t, example);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteByExample(Example example){
        getBaseMapper().deleteByExample(example);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String ids){
        getBaseMapper().deleteByIds(ids);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(PK id){
        getBaseMapper().deleteByPrimaryKey(id);
    }
}

