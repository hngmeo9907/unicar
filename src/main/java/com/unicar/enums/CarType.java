package com.unicar.enums;

public enum CarType {
    COUNTY(1, "County"),
    FORD_TRANSIT(2, "Ford Transit"),
    FUSO(3, "Fuso"),
    SOLATI(4," Solati"),
    _24_BUONG_VIP(5, "24 Buong Vip"),
    _34_PHONG_VIP(6, "34 Phong Vip");

    int type;
    String name;

    CarType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
