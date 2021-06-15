package com.ss.utopia.domain;

public class UserRole {
    private int id;
    private String name;

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final UserRole other = (UserRole) o;
        return other.getId()==(this.getId())
                && other.getName().equals(this.getName());
    }
}
