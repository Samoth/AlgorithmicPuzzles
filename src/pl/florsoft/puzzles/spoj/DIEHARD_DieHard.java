package pl.florsoft.puzzles.spoj;

import pl.florsoft.puzzles.utils.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * task: http://www.spoj.com/problems/DIEHARD/
 */
public class DIEHARD_DieHard {

    public static void main(String[] args) throws IOException {
        List<Life> testCases = readTestCases();
        for (Life life : testCases) {
            findMaxSurviveTime(life);
        }
    }

    private static void findMaxSurviveTime(Life life) {
        int maxHealthSize = 1000 + 3 + 1;
        int maxArmorSize = 1000 + 58 * 7 + 2 + 1;
        int[][] air = new int[maxHealthSize][maxArmorSize];
        int[][] water = new int[maxHealthSize][maxArmorSize];
        int[][] fire = new int[maxHealthSize][maxArmorSize];
        int max = Math.max(findMaxSurviveTimeRec(life.health, life.armor, Place.AIR, air, water, fire),
                Math.max(findMaxSurviveTimeRec(life.health, life.armor, Place.WATER, air, water, fire), findMaxSurviveTimeRec(life.health, life.armor, Place.FIRE, air, water, fire)));
        System.out.println(max);
    }

    private static int findMaxSurviveTimeRec(int health, int armor, Place type, int[][] air, int[][] water, int[][] fire) {
        if (type == Place.AIR) {
            if (air[health][armor] == 0) {
                air[health][armor] = 1 + Math.max(findMaxSurviveTimeRec(health + 3, armor + 2, Place.WATER, air, water, fire),
                        findMaxSurviveTimeRec(health + 3, armor + 2, Place.FIRE, air, water, fire));
            }
            return air[health][armor];
        } else if (type == Place.WATER && health - 5 > 0 && armor - 10 > 0) {
            if (water[health][armor] == 0) {
                water[health][armor] = 1 + Math.max(findMaxSurviveTimeRec(health - 5, armor - 10, Place.AIR, air, water, fire),
                        findMaxSurviveTimeRec(health - 5, armor - 10, Place.FIRE, air, water, fire));
            }
            return water[health][armor];
        } else if (type == Place.FIRE && health - 20 > 0 && armor + 5 > 0) {
            if (fire[health][armor] == 0) {
                fire[health][armor] = 1 + Math.max(findMaxSurviveTimeRec(health - 20, armor + 5, Place.AIR, air, water, fire),
                        findMaxSurviveTimeRec(health - 20, armor + 5, Place.WATER, air, water, fire));
            }
            return fire[health][armor];
        }
        return 0;
    }

    private static List<Life> readTestCases() throws IOException {
        InputStreamReader reader = new InputStreamReader();
        int testCases = reader.nextInt();
        List<Life> caseList = new ArrayList<>(testCases);
        for (int i = 0; i < testCases; i++) {
            caseList.add(new Life(reader.nextInt(), reader.nextInt()));
        }
        return caseList;
    }

    static class Life {
        int health;
        int armor;

        Life(int health, int armor) {
            this.health = health;
            this.armor = armor;
        }
    }

    enum Place {
        AIR, WATER, FIRE
    }

}
