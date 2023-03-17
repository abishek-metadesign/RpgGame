package uk.co.metadesignsolutions;

import java.util.Objects;

public enum WeaponType {
    SWORD('S'),
    BOW('B'),

    FIREBALL('F'),
    HEALING_POTION('H');

    private char shortName;

    WeaponType(char shortName) {
        this.shortName = shortName;
    }


    public static WeaponType valueOf(char shortName){
        WeaponType[] values = WeaponType.values();
        for (WeaponType value: values){
            if (Objects.equals(shortName,value.shortName)){
                return value;
            }
        }
        throw new RuntimeException("Enum with short Name doesn't exist");
    }


}
