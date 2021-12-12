import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;



public class TestCase2 {
    static DbService service;
    static Shop shop;
    static String sql_insertShop = "INSERT INTO Shop(name, price, stock, user_id) VALUES(?, ?, ?, ?)";

    @BeforeClass
    public static void setUp() {
        System.out.println("-- START TESTS --");
        service = DbService.getInstance();
        String sql_select_user = "SELECT * FROM User WHERE email = 'test_Case_1@gmail.com'";
        User tempUser = (User) service.executeStatment(sql_select_user);
        shop = new Shop();
        shop.setName("Produs1");
        shop.setPrice("33.3");
        shop.setStock("10");
        shop.setUser_id(tempUser.getId());             
        service.executeUpdate(sql_insertShop, shop);
    }

    @Test
    public void testCase3_CheckShop_Stock() {
        int stock = 10;
        String sql_select_Shop = "SELECT * FROM Shop WHERE name = '" + shop.getName() + "'";
        Shop tempShop = (Shop) service.executeStatment(sql_select_Shop);
        assertEquals(stock, Integer.parseInt(tempShop.getStock()));
    }

    @AfterClass
    public static void teardown() {
        System.out.println("-- END TESTS --");
    }



}
