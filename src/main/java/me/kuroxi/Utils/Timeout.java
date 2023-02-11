package me.kuroxi.Utils;

public class Timeout {
    public Timeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }
}
