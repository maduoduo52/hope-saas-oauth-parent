package com.hope.saas.wms.api.config.mybatisplus.uitl;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Maduo
 * @date 2020/3/20 13:35
 */
public class EwGenJavaUtil {

    //数据库名称
    static final String DB_NAME = "hope_saas_api_wms";
    //数据库连接
    static final String DB_URL = "jdbc:mysql://52.82.11.94:8096/" + DB_NAME + "?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "root@123";

    //包名
    static final String packName = "com.hope.saas.wms.api";

    //文件生成路径
    static final String filePath = "F:\\genjava_inter\\";
    //zengliming
    static final String author = "Maduo";

    static String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public static void main(String[] args) throws Exception {
        List<EwGenJavaBD> genJavaBDS = init();
        System.out.println("=====>表数据=====================");
        System.out.println(JSON.toJSONString(genJavaBDS, true));
        System.out.println("=====>表数据=====================");
        System.out.println();
        for (EwGenJavaBD genJavaBD : genJavaBDS) {
            System.out.println("======开始创建" + genJavaBD.getName() + " final 静态类");
            String finalPack = genFinal(genJavaBD);
            System.out.println("=====开始创建" + genJavaBD.getName() + " entity");
            String entityPack = entity(genJavaBD, finalPack);
            System.out.println("=====开始创建" + genJavaBD.getName() + " dao");
            String daoPack = dao(genJavaBD, entityPack);
            System.out.println("=====开始创建" + genJavaBD.getName() + " service ");
            String service = service(genJavaBD, entityPack);
            System.out.println("=====开始创建" + genJavaBD.getName() + " service impl");
            String serviceImpl = serviceImpl(genJavaBD, entityPack, service, daoPack);
            System.out.println("=====开始创建" + genJavaBD.getName() + " service controller");
            controller(genJavaBD, entityPack, serviceImpl);
            System.out.println("=====开始创建" + genJavaBD.getName() + " api_dto");
            String dto = api_dto(genJavaBD);
            System.out.println("=====开始创建" + genJavaBD.getName() + " api_vo");
            String vo = api_vo(genJavaBD);
        }
    }


    /**
     * api_vo
     *
     * @param genJavaBD
     * @return
     * @throws Exception
     */
    public static String api_vo(EwGenJavaBD genJavaBD) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }
        String plo = "";
        // 雷名大写
        className = entityName;
        String str = "package " + packName + ".vo;\n" +
                "import com.hope.saas.common.vo.util.BaseVo;\n" +
                "import lombok.Data;\n" +
                "STR\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "@Data\n" +
                "public class " + className + "Vo extends BaseVo{\n";
        for (Map<String, String> m : genJavaBD.getFileds()) {
            str += "  /**\n" +
                    "   * " + m.get("columnComment") + "\n" +
                    "   */\n";
            String dataType = m.get("dataType");
            String columnName = m.get("columnName");
            if (dataType.startsWith("tinyint(1)")) {
                str += "  private Boolean " + columnName + ";\n";
            } else if (dataType.startsWith("tinyint")) {
                str += "  private Integer " + columnName + ";\n";
            } else if (dataType.startsWith("datetime") || dataType.startsWith("date")) {
                if (!plo.contains("java.util.Date")) {
                    plo += "import java.util.Date;\n";
                }
                str += "  private Date " + columnName + ";\n";
            } else if (dataType.startsWith("bigint")) {
                str += "  private Long " + columnName + ";\n";
            } else if (dataType.startsWith("float")) {
                str += "  private Float " + columnName + ";\n";
            } else if (dataType.startsWith("double")) {
                str += "  private Double " + columnName + ";\n";
            } else if (dataType.startsWith("int")) {
                str += "  private Integer " + columnName + ";\n";
            } else if (dataType.startsWith("decimal")) {
                if (!plo.contains("java.math.BigDecimal")) {
                    plo += "import java.math.BigDecimal;\n";
                }
                str += "  private BigDecimal " + columnName + ";\n";
            } else {
                str += "  private String " + columnName + ";\n";
            }
        }
        str += "}\n";
        str = str.replaceAll("STR", plo);
        File file = new File(filePath + "\\vo\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Vo.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".vo." + className + "Vo";
    }

    /**
     * api_dto
     *
     * @param genJavaBD
     * @return
     * @throws Exception
     */
    public static String api_dto(EwGenJavaBD genJavaBD) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }
        String plo = "";
        // 雷名大写
        className = entityName;
        String str = "package " + packName + ".dto;\n" +
                "import com.hope.saas.oauth.dto.BaseDto;\n" +
                "import lombok.Data;\n" +
                "STR\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "@Data\n" +
                "public class " + className + "Dto extends BaseDto{\n";
        for (Map<String, String> m : genJavaBD.getFileds()) {
            str += "  /**\n" +
                    "   * " + m.get("columnComment") + "\n" +
                    "   */\n";
            String dataType = m.get("dataType");
            String columnName = m.get("columnName");
            if (dataType.startsWith("tinyint(1)")) {
                str += "  private Boolean " + columnName + ";\n";
            } else if (dataType.startsWith("tinyint")) {
                str += "  private Integer " + columnName + ";\n";
            } else if (dataType.startsWith("datetime") || dataType.startsWith("date")) {
                if (!plo.contains("java.util.Date")) {
                    plo += "import java.util.Date;\n";
                }
                str += "  private Date " + columnName + ";\n";
            } else if (dataType.startsWith("bigint")) {
                str += "  private Long " + columnName + ";\n";
            } else if (dataType.startsWith("float")) {
                str += "  private Float " + columnName + ";\n";
            } else if (dataType.startsWith("double")) {
                str += "  private Double " + columnName + ";\n";
            } else if (dataType.startsWith("int")) {
                str += "  private Integer " + columnName + ";\n";
            } else if (dataType.startsWith("decimal")) {
                if (!plo.contains("java.math.BigDecimal")) {
                    plo += "import java.math.BigDecimal;\n";
                }
                str += "  private BigDecimal " + columnName + ";\n";
            } else {
                str += "  private String " + columnName + ";\n";
            }
        }
        str += "}\n";
        str = str.replaceAll("STR", plo);
        File file = new File(filePath + "\\dto\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Dto.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".dto." + className + "Dto";
    }


    /**
     * 生成静态类
     *
     * @param genJavaBD
     * @return
     * @throws Exception
     */
    public static String genFinal(EwGenJavaBD genJavaBD) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }

        // 雷名大写
        className = entityName;
        String str = "package " + packName + ".table;\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "public class " + className + "Table {\n";
        for (Map<String, String> m : genJavaBD.getFileds()) {
            str += "  /**\n" +
                    "   * " + m.get("columnComment") + "\n" +
                    "   */\n";
            str += "  public static final String " + m.get("column").toUpperCase() + " = " + "\"" + m.get("column") + "\";\n";
        }
        str += "}\n";
        File file = new File(filePath + "table\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Table.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".table." + className + "Table";
    }

    /**
     * 生成entity
     *
     * @param genJavaBD
     * @param finalPack
     * @return
     * @throws Exception
     */
    public static String entity(EwGenJavaBD genJavaBD, String finalPack) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }
        String plo = "";
        // 雷名大写
        className = entityName;
        String str = "package " + packName + ".entity;\n" +
                "import com.baomidou.mybatisplus.annotations.TableName;\n" +
                "import com.baomidou.mybatisplus.annotations.TableField;\n" +
                "import com.hope.saas.common.entity.util.BaseEntity;\n" +
                "import lombok.Data;\n" +
                "import " + finalPack + ";\n" +
                "STR\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "@Data\n" +
                "@TableName(\"" + genJavaBD.getName() + "\")\n" +
                "public class " + className + "Entity extends BaseEntity{\n";
        for (Map<String, String> m : genJavaBD.getFileds()) {
            str += "  /**\n" +
                    "   * " + m.get("columnComment") + "\n" +
                    "   */\n";
            str += "  @TableField(" + className + "Table." + m.get("column").toUpperCase() + ")\n";
            String dataType = m.get("dataType");
            String columnName = m.get("columnName");
            if (dataType.startsWith("tinyint(1)")) {
                str += "  private Boolean " + columnName + ";\n";
            } else if (dataType.startsWith("tinyint")) {
                str += "  private Integer " + columnName + ";\n";
            } else if (dataType.startsWith("datetime") || dataType.startsWith("date")) {
                if (!plo.contains("java.util.Date")) {
                    plo += "import java.util.Date;\n";
                }
                str += "  private Date " + columnName + ";\n";
            } else if (dataType.startsWith("bigint")) {
                str += "  private Long " + columnName + ";\n";
            } else if (dataType.startsWith("float")) {
                str += "  private Float " + columnName + ";\n";
            } else if (dataType.startsWith("double")) {
                str += "  private Double " + columnName + ";\n";
            } else if (dataType.startsWith("int")) {
                str += "  private Integer " + columnName + ";\n";
            } else if (dataType.startsWith("decimal")) {
                if (!plo.contains("java.math.BigDecimal")) {
                    plo += "import java.math.BigDecimal;\n";
                }
                str += "  private BigDecimal " + columnName + ";\n";
            } else {
                str += "  private String " + columnName + ";\n";
            }
        }
        str += "}\n";
        str = str.replaceAll("STR", plo);
        File file = new File(filePath + "entity\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Entity.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".entity." + className + "Entity";
    }


    /**
     * 生成dao
     *
     * @param genJavaBD
     * @param entityPack
     * @return
     * @throws Exception
     */
    public static String dao(EwGenJavaBD genJavaBD, String entityPack) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }

        // 雷名大写
        className = entityName;

        String str = "package " + packName + ".dao;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.mapper.BaseMapper;\n" +
                "import " + entityPack + ";\n" +
                "import org.apache.ibatis.annotations.Mapper;\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "@Mapper\n" +
                "public interface " + className + "Dao extends BaseMapper<" + className + "Entity> {\n" +
                "}";
        File file = new File(filePath + "dao\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Dao.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".dao." + className + "Dao";
    }

    /**
     * 生成service
     *
     * @param genJavaBD
     * @param entityPack
     * @return
     * @throws Exception
     */
    public static String service(EwGenJavaBD genJavaBD, String entityPack) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }

        // 雷名大写
        className = entityName;

        String str = "package " + packName + ".service;\n" +
                "\n" +
                "import com.baomidou.mybatisplus.service.IService;\n" +
                "import " + entityPack + ";\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "public interface " + className + "Service extends IService<" + className + "Entity> {\n" +
                "}";
        File file = new File(filePath + "service\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Service.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".service." + className + "Service";
    }

    /**
     * 生成 service impl
     *
     * @param genJavaBD
     * @param entityPack
     * @param servicePack
     * @param daoPack
     * @return
     * @throws Exception
     */
    public static String serviceImpl(EwGenJavaBD genJavaBD, String entityPack, String servicePack, String daoPack) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }

        // 雷名大写
        className = entityName;

        String str = "package " + packName + ".service.impl;\n" +
                "\n" +
                "import " + daoPack + ";\n" +
                "import " + entityPack + ";\n" +
                "import " + servicePack + ";\n" +
                "import com.baomidou.mybatisplus.service.impl.ServiceImpl;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import org.springframework.transaction.annotation.Transactional;\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "@Service\n" +
                "@Transactional(rollbackFor = Exception.class)\n" +
                "public class " + className + "ServiceImpl extends ServiceImpl<" + className + "Dao," + className + "Entity> implements " + className + "Service{\n" +
                "}";
        File file = new File(filePath + "service\\impl\\");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "ServiceImpl.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".service.impl." + className + "ServiceImpl";
    }

    /**
     * 生成controller
     *
     * @param genJavaBD
     * @param entityPack
     * @param servicePack
     * @return
     * @throws Exception
     */
    public static String controller(EwGenJavaBD genJavaBD, String entityPack, String servicePack) throws Exception {
        String className = genJavaBD.getName();
        String strs[] = className.toLowerCase().split("_|-");
        String entityName = "";
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            cs[0] -= 32;
            entityName += String.valueOf(cs);
        }
        // 雷名大写
        className = entityName;

        String str = "package " + packName + ".controller;\n" +
                "\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "\n" +
                "/**\n" +
                " * " + genJavaBD.getMark() + "\n" +
                " * @author " + author + "\n" +
                " * @date " + date + "\n" +
                " */\n" +
                "@RestController\n" +
                "@RequestMapping(value = \"" + className.substring(0, 1).toLowerCase() + className.substring(1) + "\")\n" +
                "public class " + className + "Controller {\n" +
                "}";
        File file = new File(filePath + "controller");
        file.mkdirs();
        File f2 = new File(file.getPath() + "\\" + className + "Controller.java");
        FileOutputStream out = new FileOutputStream(f2);
        out.write(str.getBytes());
        out.close();
        return packName + ".controller" + className + "Controller";
    }

    /**
     * 初始化加载
     *
     * @return
     */
    public static List<EwGenJavaBD> init() throws Exception {
        List<EwGenJavaBD> list = new ArrayList<>();
        System.out.println("初始化数据:==================================");
        //step 1 ：查询表列表
        System.out.println("查询表数据 表名以及备注 :==================================1");
        Map<String, String> tableNote = tableNote();
        System.out.println(JSON.toJSONString(tableNote, true));
        //表集合
        Set<String> table = tableNote.keySet();
        for (String s : table) {
            EwGenJavaBD ewGenJavaBD = new EwGenJavaBD();
            ewGenJavaBD.setName(s);
            ewGenJavaBD.setMark(tableNote.get(s));
            System.out.println("===========查询表（" + s + "）字段详情===================");
            List<Map<String, String>> fileds = filed(s);
            ewGenJavaBD.setFileds(fileds);
            list.add(ewGenJavaBD);
        }

        return list;
    }


    /**
     * 获取表的注释
     *
     * @return
     */
    public static Map<String, String> tableNote() throws Exception {
        Map<String, String> map = new HashMap<>();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        //首先查询表
        ResultSet rs = stmt.executeQuery("show table status");
        while (rs.next()) {
            String tableName = rs.getString("Name");
            String columnName = rs.getString("Comment");
            map.put(tableName, columnName);
        }
        rs.close();
        stmt.close();
        conn.close();
        return map;
    }


    /**
     * 查询字段详情
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> filed(String tableName) throws Exception {
        List<Map<String, String>> list = new ArrayList<>();
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        //首先查询表
        ResultSet rs = stmt.executeQuery("select * from information_schema.columns where table_schema = '" + DB_NAME + "' and table_name = " + "'" + tableName + "';");
        while (rs.next()) {
            Map<String, String> map = new HashMap<>();
            String columnName = rs.getString("COLUMN_NAME");
            if (!"id,add_time,update_time,version,delete_flag,add_emp_id,update_emp_id".contains(columnName)) {
                String dataType = rs.getString("DATA_TYPE");
                String columnComment = rs.getString("COLUMN_COMMENT");
                map.put("column", columnName);
                String[] str = columnName.toLowerCase().split("_");
                String entityName = str[0];
                for (int i = 1; i < str.length; i++) {
                    char[] cs = str[i].toCharArray();
                    cs[0] -= 32;
                    entityName += String.valueOf(cs);
                }
                map.put("columnName", entityName);
                map.put("dataType", dataType);
                map.put("columnComment", columnComment);
                list.add(map);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
}
