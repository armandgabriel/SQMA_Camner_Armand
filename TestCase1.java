

public class TestCase1 {

    static DbService service;
    static User user;
    
    static String sql_insertUser = "INSERT INTO User(name,password,age,email) VALUES(?,?,?,?)";
    

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

    @AfterClass
    public static void teardown() {
        System.out.println("-- END TESTS --");
    }


}
