package com.example.gen.jdbc;

//import cn.com.goldenwater.gen.core.Column;
//import cn.com.goldenwater.gen.util.StringUtil;

import com.example.gen.core.Column;
import com.example.gen.util.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zx on 14/10/24.
 */
public class OracleDao extends AbstractDaoSupport {
    @Override
    public List<String> queryAllTables() {
        return queryAllTables("select lower(tname) from tab where tabtype = 'TABLE' order by 1");

    }

    @Override
    public List<Column> queryColumns(String tableName) {
        List<Column> list = new ArrayList<Column>();
        try {
            checkDriver();
            Connection conn = getConn();
            String sql =
                    "select  lower(t1.column_name), lower(t1.data_type), t2.comments, t1.data_length, t1.data_precision, t1.data_scale " +
                            " from  user_col_comments  t2  left  join  user_tab_columns  t1 " +
                            " on  t1.table_name  =  t2.table_name  and  t1.column_name  =  t2.column_name " +
                            " where  t1.table_name  =  upper('"+tableName+"')";
            ResultSet rs = createQuary(conn, sql);
            while (rs.next()) {
                String columnName = rs.getString(1);
                String dataType = rs.getString(2);
                String comments = rs.getString(3);
                int dataLength = rs.getInt("data_length");
                String dataPrecision = rs.getString("data_precision");
                String dataScale = rs.getString("data_scale");
                String type = typesConvert(dataType, dataLength, dataPrecision, dataScale);
                String columnNameJava = StringUtil.javaStyle(columnName);
                list.add(new Column(type, columnName, columnNameJava, comments));
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;    }

    public String typesConvert(String oracleType, int dataLength, String dataPrecision, String dataScale) {
        if (oracleType.startsWith("varchar")) {
            return "String";
        } else if(oracleType.startsWith("char")) {
            if(dataLength == 1) {
                return "Boolean";
            } else {
                return "String";
            }
        } else if (oracleType.startsWith("long")) {
            return "Integer";
        } else if (oracleType.startsWith("number")) {
            if(dataScale != null && "".equals(dataScale) == false && Integer.parseInt(dataScale) == 0) {
                return "Long";
            } else {
                return "Double";
            }
        } else if (oracleType.startsWith("date")) {
            return "Date";
        }else if (oracleType.startsWith("timestamp(6)")) {
            return "Date";
        }else if (oracleType.startsWith("clob")) {
            return "String";
        }else if (oracleType.startsWith("nvarchar2")) {
            return "String";
        }
        return oracleType;
    }
        @Override
    public String typesConvert(String oracleType) {
        if (oracleType.startsWith("varchar") || oracleType.startsWith("char")) {
            return "String";
        } else if (oracleType.startsWith("long")) {
            return "Integer";
        } else if (oracleType.startsWith("number")) {
            return "Double";
        } else if (oracleType.startsWith("date")) {
            return "Date";
        }
        return oracleType;
    }
}
