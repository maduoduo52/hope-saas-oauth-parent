package com.hope.saas.wms.api.config.mybatisplus.service.impl;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hope.saas.api.util.exception.NewHopeException;
import com.hope.saas.common.constants.Constant;
import com.hope.saas.common.entity.util.BaseEntity;
import com.hope.saas.common.table.util.BaseTable;
import com.hope.saas.wms.api.config.mybatisplus.dao.DeleteMapper;
import com.hope.saas.wms.api.config.mybatisplus.service.BaseServerIService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Desc: M 持久层对象
 * T entity对象
 * @Author Maduo
 * @Create 2020/5/22 16:15
 */
@Slf4j
public abstract class BaseServerServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseServerIService<T> {

    @Autowired
    private DeleteMapper deleteMapper;

    /**
     * 静态区Map存储所有的bean对象class路径
     */
    public static Map<String, Class<?>> ENTITY_MAP = new ConcurrentHashMap<>();

    /**
     * 静态区map存储bean对象对于的属性字段字段名称
     */
    public static Map<String, Map<String, String>> ENTITY_CL = new ConcurrentHashMap<>();

    /**
     * 静态区map存储bean对象和baen对象对应的表名
     */
    public static Map<String, String> ENTITY_TABLE = new ConcurrentHashMap<>();

    /**
     * 构造方法获取数据
     */
    public BaseServerServiceImpl() {
        try {
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) type;
                //直接获取第2个泛型 第2个泛型为实体bean对象
                Type claz = pType.getActualTypeArguments()[1];
                //放进map中
                ENTITY_MAP.put(this.getClass().getName(), Class.forName(claz.getTypeName()));
                //获取class对象
                Class clas = Class.forName(claz.getTypeName());
                //开始获取类的注解
                Annotation allAnnos = clas.getDeclaredAnnotation(TableName.class);
                if (allAnnos != null) {
                    TableName tableName = (TableName) allAnnos;
                    String tname = tableName.value();
                    if (!StringUtils.isEmpty(tname)) {
                        ENTITY_TABLE.put(this.getClass().getName(), tname);
                    }
                } else {
                    throw new RuntimeException("错误的bean对象，bean对象应该加上@TableName 注解 并且标注名表名");
                }
                //获取所有的字段
                Field[] fields = FieldUtils.getAllFields(clas);
                Map<String, String> map = new HashMap<>();
                for (Field field : fields) {
                    Annotation an = field.getAnnotation(TableField.class);
                    if (an != null) {
                        //存在
                        TableField fieldL = (TableField) an;
                        map.put(field.getName(), fieldL.value());
                    } else {
                        throw new RuntimeException("错误的实体类属性字段(" + field.getName() + ")，为了规范 必须加上@TableField 注解");
                    }
                }
                ENTITY_CL.put(this.getClass().getName(), map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public List<T> selectList(Wrapper<T> wrapper) {
        if (wrapper == null) {
            wrapper = new EntityWrapper<>();
        }
        wrapper.eq(BaseTable.DELETE_FLAG, false);
        return super.selectList(wrapper);
    }

    /**
     * 物理删除
     *
     * @param id 数据库ID
     * @return
     */
    @Override
    public boolean physicsDeleteById(Serializable id) {
        if (StringUtils.isEmpty(id)) {
            throw new NewHopeException("id不能为空！");
        }
        String sql = "delete from `" + ENTITY_TABLE.get(this.getClass().getName()) + "` where `" + BaseTable.ID + "` = #{gw.idValue} ";
        Map map = new HashMap();
        map.put("sql", sql);
        map.put("idValue", id);
        int i = deleteMapper.physicsDelete(map);
        log.warn("{} -- {} 根据ID进行物理删除 {} ", Constant.get(), map);
        return i > 0;
    }

    /**
     * 根据数据库字段条件物理删除
     *
     * @param columnMap 数据字段 只能判断 = 其余无法判断
     * @return
     */
    @Override
    public boolean physicsDeleteByMap(Map<String, Object> columnMap) {
        if (columnMap == null || columnMap.isEmpty()) {
            throw new NewHopeException("columnMap不能为空！如果需要全部删除请自行写sql");
        }
        int i = deleteMapper.physicsDeleteByMap(ENTITY_TABLE.get(this.getClass().getName()), columnMap);
        log.warn("{} -- {} 根据map进行物理删除 {} ", Constant.get(), columnMap);
        return i > 0;
    }

    /**
     * 根据wrapper  数据库物理删除
     *
     * @param wrapper
     * @return
     */
    @Override
    public boolean physicsDelete(Wrapper wrapper) {
        if (wrapper == null || StringUtils.isEmpty(wrapper.getSqlSegment())) {
            throw new NewHopeException("wrapper不能为空！如果需要全部删除请自行写sql");
        }
        String sql = "delete from `" + ENTITY_TABLE.get(this.getClass().getName()) + "` ";
        Map map = getMap(wrapper, sql);
        int i = deleteMapper.physicsDelete(map);
        return i > 0;
    }

    private Map getMap(Wrapper wrapper, String sql) {
        sql += wrapper.getSqlSegment();
        sql = sql.replaceFirst("AND", "  where").replaceAll("ew.paramNameValuePairs", "gw.paramNameValuePairs");
        Map map = new HashMap<>();
        map.put("sql", sql);
        map.put("paramNameValuePairs", wrapper.getParamNameValuePairs());
        return map;
    }

    /**
     * 批量进行物理删除
     *
     * @param idList id集合 in
     * @return
     */
    @Override
    public boolean physicsDeleteBatchIds(List<? extends Serializable> idList) {
        if (idList == null || idList.isEmpty()) {
            throw new NewHopeException("idList不能为空！");
        }
        int i = deleteMapper.physicsDeleteBatchIds(ENTITY_TABLE.get(this.getClass().getName()), BaseTable.ID, idList);
        log.warn("{} -- {} 根据IDS进行物理删除 {} ", Constant.get(), idList);
        return i > 0;
    }

    /**
     * 根据wapper查询 返回map  key 为ID  value为T对象
     *
     * @param wrapper
     * @return
     * @
     */
    @Override
    public Map<Serializable, T> selectByWrapper(Wrapper<T> wrapper) {
        List<T> list = super.selectList(wrapper);
        return getSerializableTMap(list);
    }

    /**
     * 根据ID集合查询 返回map key 为ID  value为T对象
     *
     * @param idList
     * @return
     * @
     */
    @Override
    public Map<Serializable, T> selectByIdList(List<Serializable> idList) {
        List<T> list = super.selectBatchIds(idList);
        return getSerializableTMap(list);
    }

    private Map<Serializable, T> getSerializableTMap(List<T> list) {
        if (list != null) {
            if (list.isEmpty()) {
                return new HashMap<>();
            }
            Map<Serializable, T> map = new HashMap<>();
            for (T t : list) {
                BaseEntity baseEntity = (BaseEntity) t;
                map.put(baseEntity.getId(), t);
            }
            return map;
        }
        return null;
    }
}
