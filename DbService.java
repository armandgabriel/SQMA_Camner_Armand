
import java.sql.*;

public class DbService {

    private String HOST;
    private String PORT;
    private String USERNAME;
    private String PASSWORD;
    private String DATABASENAME;
    private String DBConnectionString;
    
    private Connection connector;
    private Statement stmt;
    private PreparedStatement pStmt;

    private static DbService instance;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch ( Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private DbService() {
        HOST = "jdbc:mysql://127.0.0.1";
        PORT = "3306";
        USERNAME = "root";
        PASSWORD = "root";
        DATABASENAME = "sqma";
        HOST = HOST + ":" + PORT + "/" + DATABASENAME;
    }

    public static DbService getInstance() {
        if(instance == null) {
            instance = new DbService();
        }
        return instance;
    }

    public String getHOST() {
        return HOST;
    }

    public String getPORT() {
        return PORT;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getDATABASENAME() {
        return DATABASENAME;
    }

    public String getDBConnectionString() {
        return DBConnectionString;
    }

    private void openConnection() {
        try {
            connector = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println(" ERROR - openConnection");
            System.out.println(e.getMessage());
        }
    }

    private void closeConnection() {
        try {
            connector.close();
        } catch (Exception e) {
            System.out.println(" ERORR - closeConnection");
            System.out.println(e.getMessage());
        }
    }

    public Object executeStatment(String sql) {
        try {
            openConnection();
            stmt = connector.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            User objU = null;
            Shop objS = null;
            if(rs.next()) {
                if(sql.contains("User")) {
                    objU = new User();
                    objU.setId(rs.getInt(1));
                    objU.setName(rs.getString(2));
                    objU.setPassword(rs.getString(3));
                    objU.setAge(rs.getString(4));
                    objU.setEmail(rs.getString(5));
                    return objU;
                    
                } else if(sql.contains("Shop")) {
                    objS = new Shop();
                    objS.setId(rs.getInt(1));
                    objS.setName(rs.getString(2));
                    objS.setPrice(rs.getString(3));
                    objS.setStock(rs.getString(4));
                    objS.setUser_id(rs.getInt(5));
                    return objS;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(" ERORR - executeStatment");
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public int executeDelete(String sql) {
        try {
            openConnection();
            stmt = connector.createStatement();
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(" ERORR - executeDelete");
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return -2;
    } 

    public int updateStock(String sql, Shop s) {
        try {
            openConnection();
            pStmt = connector.prepareStatement(sql);
            pStmt.setString(1, s.getStock());
            return pStmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(" ERORR - updateStock");
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return -2;
    }

    public int executeUpdate(String sql, Object obj) {
        if(obj instanceof User) {
            try {
                openConnection();
                User u = (User) obj;
                pStmt = connector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                //pStmt.setInt(1, u.getId()
                pStmt.setString(1, u.getName());
                pStmt.setString(2, u.getPassword());
                pStmt.setString(3, u.getAge());
                pStmt.setString(4, u.getEmail());
                return pStmt.executeUpdate();
            } catch (Exception e) {
                System.out.println(" ERORR - executeUpdate - User");
                System.out.println(e.getMessage());
            } finally {
                closeConnection();
            }
        } else if(obj instanceof Shop) {
            try {
                openConnection();
                Shop s = (Shop) obj;
                pStmt = connector.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pStmt.setString(1, s.getName());
                pStmt.setString(2, s.getPrice());
                pStmt.setString(3, s.getStock());
                pStmt.setInt(4, s.getUser_id());
                return pStmt.executeUpdate();
            } catch (Exception e) { 
                System.out.println(" ERORR - executeUpdate - Shop");
                System.out.println(e.getMessage());
            } finally {
                closeConnection();
            }
        }
        return -2;
    }

}