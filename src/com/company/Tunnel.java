package com.company;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    static Semaphore semaphore;
    public Tunnel(int length) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
    }
    static {
        semaphore = new Semaphore(Main.CARS_COUNT / 2);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
