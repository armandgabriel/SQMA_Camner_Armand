import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestCase1 {

    static DbService service;
    static User user;
    static Shop shop;
    static String sql_insertUser = "INSERT INTO User(name,password,age,email) VALUES(?,?,?,?)";
    static String sql_insertShop = "INSERT INTO Shop(name, price, stock, user_id) VALUES(?, ?, ?, ?)";

    @BeforeClass
    public static void setUp() {
        System.out.println("-- START TESTS --");
        service = DbService.getInstance();
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
    }

    @Test
    public void testCase1_CheckUser_Name() {
        String userName = user.getName();
        String sql_select_user = "SELECT * FROM User WHERE name = '" + user.getName() + "'";
        User tempUser = (User) service.executeStatment(sql_select_user);
        assertEquals(tempUser.getName(), userName);
    }

    @Test
    public void testCase2_CheckUser_Email() {
        String userName = user.getName();
        String email = user.getEmail();
        String sql_select_user = "SELECT * FROM User WHERE name = '" + user.getName() + "'";
        User tempUser = (User) service.executeStatment(sql_select_user);
        assertEquals(tempUser.getEmail(), email);
    }

    @Test
    public void testCase3_CheckShop_Stock() {
        int stock = 9;
        String sql_select_Shop = "SELECT * FROM Shop WHERE name = '" + shop.getName() + "'";
        Shop tempShop = (Shop) service.executeStatment(sql_select_Shop);
        assertEquals(stock, tempShop.getStock());
    }

    @AfterClass
    public static void teardown() {
        System.out.println("-- END TESTS --");
    }


}
