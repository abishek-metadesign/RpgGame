package uk.co.metadesignsolutions;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {

    public String solve(String enemies, String weapons) {
        List<EnemyType> enemyList = process(enemies)
                .stream()
                .map(i -> i.charAt(0))
                .map(EnemyType::valueOf)
                .collect(Collectors.toList());

        List<WeaponType> weaponList = process(weapons)
                .stream()
                .map(i -> i.charAt(0))
                .map(WeaponType::valueOf)
                .collect(Collectors.toList());

        Map<EnemyType,Integer> enemyMap = new HashMap<>();
        for (int i =0; i< enemyList.size(); i++) {
            process(enemyList,i,weaponList,enemyMap);
        }
       return  constructOutput(enemyMap);
    }

    private String constructOutput(Map<EnemyType, Integer> enemyMap) {
        return convertToString("F", EnemyType.FROZEN, enemyMap) +
                "," +
                convertToString("H", EnemyType.HEALTHY, enemyMap) +
                "," +
                convertToString("D", EnemyType.DAMAGED, enemyMap) +
                "," +
                convertToString("T", EnemyType.TRAPPED, enemyMap) +
                "," +
                convertToString("X", EnemyType.DEFEATED, enemyMap);
    }

    private  String convertToString(String shortName,EnemyType enemyType, Map<EnemyType,Integer> enemyMap){
        return shortName+":"+enemyMap.getOrDefault(enemyType,0);
    }


    private void process(List<EnemyType> enemies, int curIndex, List<WeaponType> weaponTypes , Map<EnemyType,Integer> enemyMap) {
        EnemyType currentType = enemies.get(curIndex);
        for (int i = 0; i < weaponTypes.size(); i++) {
            WeaponType weaponType = weaponTypes.get(i);
            if (weaponType.equals(WeaponType.SWORD)) {
                if (currentType.equals(EnemyType.HEALTHY)) {
                    currentType = EnemyType.DAMAGED;
                }
            }
            if (weaponType.equals(WeaponType.BOW)) {
                if (currentType.equals(EnemyType.TRAPPED)) {
                    currentType = EnemyType.DAMAGED;
                }
            }
            if (weaponType.equals(WeaponType.FIREBALL)) {
                if (!(i - 1 < 0) && weaponTypes.get(i - 1).equals(WeaponType.HEALING_POTION)) {
                    continue;
                }

                if (currentType.equals(EnemyType.FROZEN) || currentType.equals(EnemyType.HEALTHY)) {
                    currentType = EnemyType.DEFEATED;
                }else if (currentType.equals(EnemyType.DEFEATED)) {
                    currentType = EnemyType.HEALTHY;
                }
            }
            if (weaponType.equals(WeaponType.HEALING_POTION)) {
                if (i + 1 < weaponTypes.size() && weaponTypes.get(i + 1).equals(WeaponType.FIREBALL)) {
                    currentType = EnemyType.DEFEATED;
                    continue;
                }
                if (currentType.equals(EnemyType.DAMAGED)) {
                    currentType = EnemyType.HEALTHY;
                }
            }
        }
        enemyMap.put(currentType,enemyMap.getOrDefault(currentType,0)+1);
    }

    private List<String> process(String data) {
        return Arrays.asList(data.split(","));
    }

}
