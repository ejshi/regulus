package org.regulus.common.plugin.model;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果数据
 * date: 2018年2月1日 下午2:46:13 <br/>
 * @author shijun@richinfo.cn
 * @version @param <T>
 * @since V1.0
 */
public class PageResultModel<T> implements Serializable {
    
    private static final long serialVersionUID = 3138850211858853007L;
    
    private Long total;
    
    private List<T> dataList;

    public PageResultModel(Long total, List<T> dataList) {
        this.total = total;
        this.dataList = dataList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
