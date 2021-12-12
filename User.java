public class User {
    private int id;
    private String name;
    private String password;
    private String age;
    private String email;
    public User() {

    }

    public User(int id, String name, String password, String age, String email){
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

}
