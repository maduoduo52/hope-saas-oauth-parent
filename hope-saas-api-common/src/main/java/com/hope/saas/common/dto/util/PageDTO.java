package com.hope.saas.common.dto.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * @author Maduo
 * @date 2020/3/27 11:19
 */
@Data
@JsonIgnoreProperties({"sqlParam", "param"})
public class PageDTO extends BaseDTO implements Serializable {


    private List rows = new ArrayList();
    private List footer = new ArrayList();
    private Integer total;

    private Integer pageNum;
    private Integer pageSize;
    private String sort;
    private String order;
    private String keyWord;
    private boolean usePage = true;//是否使用分页，默认使用(在使用分页查询函数时调用，为true时分页查询进行分页，为false时分页查询不进行分页)

    private Map<String, String> param;

    public PageDTO addParam(String key, Object value) {
        if (value != null) {
            Map<String, Object> param = this.sqlParam;
            if (param == null) {
                param = new HashMap<>();
            }
            param.put(key, value);
            setSqlParam(param);
        }
        return this;
    }

    private Map<String, Object> sqlParam;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        if (rows != null && rows.size() == 1 && rows.get(0) instanceof String) {
            String pagesizes = (String) rows.get(0);
            this.pageSize = Integer.parseInt(pagesizes);
        } else {
            this.rows = rows;
        }
    }

    public List getFooter() {
        return footer;
    }

    public void setFooter(List footer) {
        this.footer = footer;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        if (total != null && pageNum != null && pageSize != null) {
            int mod = total % pageSize;
            int _page = total / pageSize;
            if (mod != 0) {
                _page += 1;
            }
            if (pageNum > _page) {
                pageNum = _page;
            }
        }
    }

    /**
     * 得到查询参数map
     *
     * @return
     */
    public Map thedecoatQueryMap() {
        return decoatQueryMap(param);
    }

    /**
     * 去掉map中的空值
     *
     * @param parm
     * @return
     */
    private static Map decoatQueryMap(Map parm) {
        if (parm == null) {
            return null;
        }
        Map newparm = new HashMap();
        Iterator<Map.Entry> it = parm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry en = it.next();
            if (en.getValue() instanceof String) {
                if (en.getValue() != null && !en.getValue().equals("")) {
                    newparm.put(en.getKey(), en.getValue());
                }
            } else {
                if (en.getValue() != null) {
                    newparm.put(en.getKey(), en.getValue());
                }
            }
        }
        return newparm;
    }
}
