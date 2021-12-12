import java.sql.ResultSet;

public class MainApp {
    public static void main(String[] args) {
        User u = new User();
        DbService service = DbService.getInstance();
        // u.setName("ARMAND");
        // u.setPassword("password");
        // u.setAge("28");
        // u.setEmail("armand.camner@gmail.com");
        // String sql = "INSERT INTO User(name,password,age,email) VALUES(?,?,?,?)";
        // service.executeUpdate(sql, u);
        // String sql2 = "SELECT * FROM User";
        // try {
        //     Object obj = service.executeStatment(sql2);
        //     if(obj instanceof User) {
        //         User t = (User) obj;
        //         System.out.println(t.getId() + " " + t.getName() + " " + t.getPassword() + " " + t.getAge() + " " + t.getEmail());
        //     } else if(obj instanceof Shop) {
        //         Shop s = (Shop) obj;
        //         System.out.println(s.getId() + " " + s.getName() + " " + s.getPrice() + " " + s.getStock() + " " + s.getUser_id());
        //     }
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }
        String sql_insertUser = "INSERT INTO User(name,password,age,email) VALUES(?,?,?,?)";
        String sql_insertShop = "INSERT INTO Shop(name, price, stock, user_id) VALUES(?, ?, ?, ?)";
        User user;
        Shop shop;
        user = new User();
        user.setName("Test Case 1");
        user.setPassword("Parola123");
        user.setAge("30");
        user.setEmail("test_Case_1@gmail.com");
        service.executeUpdate(sql_insertUser, user);
        String sql_select_user = "SELECT * FROM User WHERE email = '" + user.getEmail() + "'";
        User tempUser = (User) service.executeStatment(sql_select_user);
        shop = new Shop();
        shop.setName("Produs1");
        shop.setPrice("33.3");
        shop.setStock("10");
        shop.setUser_id(tempUser.getId());              
        service.executeUpdate(sql_insertShop, shop);

        String sql_select_Shop = "SELECT * FROM Shop WHERE name = '" + shop.getName() + "'";

        System.out.println(tempUser.getId() + " " + tempUser.getName() + " " + tempUser.getPassword() + " " + tempUser.getAge() + " " + tempUser.getEmail());
        
        Shop tempShop = (Shop) service.executeStatment(sql_select_Shop);
        
        System.out.println(tempShop.getId() + " " + tempShop.getName() + " " + tempShop.getPrice() + " " + tempShop.getStock() + " " + tempShop.getUser_id());

    }
}
