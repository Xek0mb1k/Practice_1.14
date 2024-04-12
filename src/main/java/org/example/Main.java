package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        System.out.println("Akopyan Oganes RIBO-03-22 Variant 1");

        Thread t1 = new Thread(new MyRunnable("Thread-0"));
        Thread t2 = new Thread(new MyRunnable("Thread-1"));
        Thread t3 = new Thread(new MyRunnable("Thread-2"));

        t1.start();
        t2.start();
        t3.start();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Press Enter to exit a program ");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Exception");
        }

        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
    }

    static class MyRunnable implements Runnable {
        private final String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    synchronized (this) {
                        System.out.println(name);
                        this.wait(1000);
                        this.notify();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
