package com.ss.utopia.domain;

public class User {
    private int id;
    private int roleId;
    private String givenName;
    private String familyName;
    private String username;
    private String email;
    private String password;
    private String phone;

    public User(int id, int roleId, String givenName, String familyName, String username, String email, String password, String phone) {
        this.id = id;
        this.roleId = roleId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final User other = (User) o;
        return other.getId()==(this.getId());
    }
}
