package com.ss.utopia.domain;

import org.jetbrains.annotations.NotNull;

public class Route {
    private Integer id;
    private String origin;
    private String destination;

    public Route(Integer id, String origin, String destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public boolean equals (Object o){
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Route other = (Route) o;
        return other.getDestination().equals(this.destination) && other.getOrigin().equals(this.origin);
    }
}
