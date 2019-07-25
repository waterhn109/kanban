package com.cy.kanban;


    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

public class getUlrs {

        // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://192.168.1.2:3306/app";

        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
        //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


        // 数据库的用户名与密码，需要根据自己的设置
        static final String USER = "root";
        static final String PASS = "ibixin@123";

        public static List<String> getulrs(String mac) {
            List<String> ulrs = new ArrayList<>();
            Connection conn = null;
            Statement stmt = null;
            String content ="";
            try{
                // 注册 JDBC 驱动
                Class.forName(JDBC_DRIVER);

                // 打开链接
              //  System.out.println("连接数据库...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                // 执行查询
           //     System.out.println(" 实例化Statement对象...");
                stmt = conn.createStatement();
                String sql;
                sql = "select content from sop_msg where mac = '"+mac+"' ";
                ResultSet rs = stmt.executeQuery(sql);

                // 展开结果集数据库
                while(rs.next()){
                    // 通过字段检索
                    content = rs.getString("content");
                    ulrs = Str2Json(content);
                }
                // 完成后关闭
                rs.close();
                stmt.close();
                conn.close();
                //content 转换为list<String>

            }catch(SQLException se){
                // 处理 JDBC 错误
                se.printStackTrace();
            }catch(Exception e){
                // 处理 Class.forName 错误
                e.printStackTrace();
            }finally{
                // 关闭资源
                try{
                    if(stmt!=null) stmt.close();
                }catch(SQLException se2){
                }// 什么都不做
                try{
                    if(conn!=null) conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }
            }

            return  ulrs;
        }

    public static List<String> Str2Json(String content) throws JSONException {
        String jsonString="{\n" +
                "\t\"urlarr\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"url\":\"http://192.168.100.133:18888/WebReport/decision/view/form?viewlet=ZMT/SFC/F-KB-SFC-4.frm&plant=KYN&gongduan=LSP6BZ\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        JSONObject json= new JSONObject(content);
        JSONArray jsonArray=json.getJSONArray("urlarr");
        List<String> ulrs = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject user=(JSONObject) jsonArray.get(i);
            String url=(String) user.get("url");
            ulrs.add(url);
                    }
        return ulrs;
    }
    }
