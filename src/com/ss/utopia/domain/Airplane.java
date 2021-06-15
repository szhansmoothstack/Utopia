package com.ss.utopia.domain;

public class Airplane {
    private int id;
    private int typeID;

    public Airplane(int id, int typeID) {
        this.id = id;
        this.typeID = typeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Airplane other = (Airplane) o;
        return other.getId() == (this.getId()) && other.getTypeID() == (this.getTypeID());
    }

}
