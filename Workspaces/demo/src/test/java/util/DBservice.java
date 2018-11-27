package util;


import java.sql.*;

/**
 * Created by yp-tc-2646 on 17/1/23.
 */
public class DBservice extends LogInit {

    public Connection dbConnection(String dbURL, String uname, String password, String dbType) {

        Connection connection = null;
        Driver driver=null;
        try {
            if (dbType.equals("mysql")) {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } else if (dbType.equals("oracle")) {
                Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            } else if (dbType.equals("db2")) {

              driver= (Driver)Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            }
           DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(dbURL, uname, password);
            log.info(" Database connection id established ");


//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from tbl_user where 1=1");
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//
//            preparedStatement.close();
//            connection.close();
        } catch (Exception e) {

            e.printStackTrace();
            log.info("can't connect to database server");


        }
        return connection;
    }
    /**
     * close DB
     *
     * @param conn
     * @throws Exception
     */
    public void closeDBDriver(Connection conn) throws Exception {
        try {
            conn.close();
            log.info("Database connection terminated");
        } catch (Exception e) { /* ignore close errors */
            e.printStackTrace();
            log.error(e.toString());
        }
    }

    /**
     * get ResultSet
     *
     * @param conn
     * @param sql
     * @return
     * @throws Exception
     */
    private ResultSet getResultSet(Connection conn, String sql)
            throws Exception {
        ResultSet resultSet = null;
        try {
            // PreparedStatement pstmt;
            // ResultSet rset;
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            // pstmt = conn.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return resultSet;
    }

    /**
     * get ColumnCount
     *
     * @param resultSet
     * @return
     * @throws Exception
     */
    private int getColumnCount(ResultSet resultSet) throws Exception {
        int columnCount = 0;
        try {
            // ResultSet resultSet = this.getResultSet(conn, sql);
            columnCount = resultSet.getMetaData().getColumnCount();
            if (columnCount == 0) {
                log.info("sql error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return columnCount;
    }

    /**
     * get ColumnCount
     *
     * @param conn
     * @param sql
     * @return
     * @throws Exception
     */
    public int getColumnCount(Connection conn, String sql) throws Exception {
        int columnCount = 0;
        try {
            // ResultSet resultSet = this.getResultSet(conn, sql);
            columnCount = getResultSet(conn, sql).getMetaData()
                    .getColumnCount();
            if (columnCount == 0) {
                log.info("sql error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return columnCount;
    }

    /**
     * get RowCount
     *
     * @param conn
     * @param sql
     * @return
     * @throws Exception
     */
    public int getRowCount(Connection conn, String sql) throws Exception {
        int rowCount = 0;
        try {
            ResultSet resultSet = getResultSet(conn, sql);
            resultSet.last();
            rowCount = resultSet.getRow();
            if (rowCount == 0) {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return rowCount;
    }

    /**
     * get RowCount
     *
     * @param resultSet
     * @return
     * @throws Exception
     */
    private int getRowCount(ResultSet resultSet) throws Exception {
        int rowCount = 0;
        try {
            resultSet.last();
            rowCount = resultSet.getRow();
            if (rowCount == 0) {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return rowCount;
    }

    /**
     * get data by row index and col index
     *
     * @param conn
     * @param sql
     * @param row
     * @param col
     * @return
     * @throws Exception
     */
    public String getData(Connection conn, String sql, int row, int col)
            throws Exception {
        String data = null;
        int rownum = 0;
        int rowcount = 0;
        int colcount = 0;
        try {
            ResultSet resultSet = getResultSet(conn, sql);
            colcount = getColumnCount(resultSet);
            rowcount = getRowCount(resultSet);
            resultSet.beforeFirst();
            if (rowcount > 0) {
                if (row <= 0 || row > rowcount) {
                    log.error("error row index!");
                } else {
                    if (col <= 0 || col > colcount) {
                        log.error("error col index!");
                    } else {
                        while (resultSet.next()) {
                            rownum++;
                            if (rownum == row) {
                                data = resultSet.getString(col);
                                break;
                            }
                        }
                    }
                }
            } else {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return data;
    }

    /**
     * get data by row index and col index
     *
     * @param conn
     * @param sql
     * @param row
     * @param field
     * @return
     * @throws Exception
     */
    public String getData(Connection conn, String sql, int row, String field)
            throws Exception {
        String data = null;
        int rownum = 0;
        int rowcount = 0;
        // int colcount = 0;
        try {
            ResultSet resultSet = getResultSet(conn, sql);
            // colcount = getColumnCount(resultSet);
            rowcount = getRowCount(resultSet);
            resultSet.beforeFirst();
            if (rowcount > 0) {
                if (row <= 0 || row > rowcount) {
                    log.error("error row index!");
                } else {
                    while (resultSet.next()) {
                        rownum++;
                        if (rownum == row) {
                            data = resultSet.getString(field);
                            break;
                        }
                    }
                }
            } else {
                log.info("sql query no data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
        return data;
    }

}
