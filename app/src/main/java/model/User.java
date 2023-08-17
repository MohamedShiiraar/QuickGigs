package model;

public class User {
    private long id;
    private String emailaddress;
    private String firstName;
    private String surname;
    private String username;
    private String password;

    public User () {

    }

    public User(String emailaddress,String firstName,String surname,String username,String password) {
        this.emailaddress=emailaddress;
        this.firstName=firstName;
        this.surname=surname;
        this.username=username;
        this.password=password;

    }

    public User(long id,String emailaddress,String firstName,String surname,String username,String password) {
        this.id=id;
        this.emailaddress=emailaddress;
        this.firstName=firstName;
        this.surname=surname;
        this.username=username;
        this.password=password;

    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailaddress='" + emailaddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
