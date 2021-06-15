package com.ss.utopia.domain;

public class AirplaneType {
    private int id;
    private int maxCapacity;
    private String name;

    public AirplaneType(int id, int maxCapacity, String name) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "AirplaneType{" +
                "id=" + id +
                ", maxCapacity=" + maxCapacity +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final AirplaneType other = (AirplaneType) o;
        return other.getId() == (this.getId());
    }
}
