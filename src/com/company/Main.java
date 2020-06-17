package com.company;

import java.util.Random;

public class Main {
    public static int counter=0;
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static int bossDamage2 = bossDamage/5;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 250, 270, 200, 150, 185, 220, 230};
    public static int[] heroesDamage = {20, 15, 25, 0, 10,15,10,25};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic", "Medic", "Golem", "Lucky","Berserk", "Thor"};

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void changeDefence() {
        Random random = new Random();
        int randomNumber = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomNumber];
        System.out.println("Boss got " + bossDefenceType);
    }

    public static void round() {
        changeDefence();
        heroesHit();
        if (bossHealth > 0) {
            bossHits();
            healingHero();
        }
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0 && heroesHealth[3] <= 0 &&
                heroesHealth[4] <= 0 && heroesHealth[5] <= 0 &&
                heroesHealth[6] <= 0 && heroesHealth[7] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }
    public static void dodge(){
        Random L = new Random();
        int num5 = L.nextInt(1);

    }

    public static void printStatistics() {
        System.out.println("________________");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("Golem health: " + heroesHealth[4]);
        System.out.println("Lucky health: " + heroesHealth[5]);
        System.out.println("Berserk health: " + heroesHealth[6]);
        System.out.println("Thor health: " + heroesHealth[7]);
        System.out.println("Раунд "+counter++);
        System.out.println("________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0 ) {
                    if (heroesHealth[i] == heroesHealth[4] && heroesHealth[4] > 0){
                        heroesHealth[4] = - bossDamage2;
                    }
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void healingHero(){
        Random num = new Random();
        Random num2 = new Random();
        int x = num.nextInt(heroesDamage.length);
        int z = num2.nextInt(30)+5;
        if (heroesHealth[x] < 100 && heroesHealth[3] > 0){
        heroesHealth[x] = heroesHealth[x] + z;
        }
        System.out.println("Медик выбрал " + x);
    }




    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coef = random.nextInt(8) + 2; // 2,3,4,5,6
                    System.out.println("Critical damage "
                            + heroesDamage[i] * coef);
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }
}
