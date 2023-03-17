package uk.co.metadesignsolutions;

import java.util.Objects;

public enum EnemyType {
    FROZEN('F'),
    HEALTHY('H'),
    DAMAGED('D'),
    TRAPPED('T'),
    DEFEATED('X');
    private char shortName;
    EnemyType(char shortName) {
        this.shortName = shortName;
    }
    public static EnemyType valueOf(char shortName){
        EnemyType[] values = EnemyType.values();
        for (EnemyType value: values){
            if (Objects.equals(shortName,value.shortName)){
                return value;
            }
        }
        throw new RuntimeException("Enum with short Name doesn't exist");
    }

    public char getShortName() {
        return shortName;
    }
}
