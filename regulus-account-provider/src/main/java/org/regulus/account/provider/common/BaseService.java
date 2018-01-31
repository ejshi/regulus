/**
 * Project Name:regulus-account-provider
 * File Name:TService.java
 * Package Name:org.regulus.account.provider.service
 * Date:2018年1月26日上午11:30:25
 * Copyright (c) 2018, http://www.richinfo.cn All Rights Reserved.
 *
*/

package org.regulus.account.provider.common;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import tk.mybatis.mapper.entity.Example;

/**
 * 
 * ClassName: BaseService <br/>
 * Function: 基类service<br/>
 * date: 2018年1月26日 下午3:14:41 <br/>
 *
 * @author shijun@richinfo.cn
 * @version @param <T>
 * @version @param <PK>
 * @since V1.0
 */
public class BaseService<T, PK extends java.io.Serializable> {
    
    @Resource
    private BaseMapper<T> baseMapper;
    
    public T findByPrimaryKey(PK id){
        return baseMapper.selectByPrimaryKey(id);
    }
    
    public T findOneByExample(Example example){
        List<T> list = selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    public List<T> selectByExample(Example example){
        return baseMapper.selectByExample(example);
    }
    public List<T> selectByIds(String ids){
         return baseMapper.selectByIds(ids);
    }
    
    public int countByExample(Example example){
        return baseMapper.selectCountByExample(example);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void insertSelective(T t){
        baseMapper.insertSelective(t);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateByPrimaryKeySelective(T t){
        baseMapper.updateByPrimaryKeySelective(t);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void updateByExampleSelective(T t,Example example){
        baseMapper.updateByExampleSelective(t, example);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteByExample(Example example){
        baseMapper.deleteByExample(example);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String ids){
        baseMapper.deleteByIds(ids);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPrimaryKey(PK id){
        baseMapper.deleteByPrimaryKey(id);
    }
}

