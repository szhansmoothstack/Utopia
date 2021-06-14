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
}
