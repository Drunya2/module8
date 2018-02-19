package Module8;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class TaskLibrary {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("Enter the number of people who entered");
        Scanner sc = new Scanner(System.in);
        int peopleCount = sc.nextInt();

        System.out.println("Enter the maximum number of people who can access the library");
        Scanner sc2 = new Scanner(System.in);
        int maxAmount = sc2.nextInt();

        Semaphore semaphore = new Semaphore(peopleCount);

        semaphore.acquire();
        new Thread(() -> System.out.println("The man went to the library door")).start();
        semaphore.release();

        if (semaphore.availablePermits() ==0){

            System.out.println("A man is standing at the door");
        }
        else {
            semaphore.acquire();
            System.out.println("A person enters the library through the door inside");
            Thread.sleep(500);
            System.out.println("The man went through the door inside");
            semaphore.release();

        }

        for (int i = 0; i < peopleCount; i++) {
            final int x = i;
            semaphore.acquire();

            new Thread(() -> {

                System.out.println("The man №" + x + " visits the library");
            }).start();
            semaphore.release();
        }


        if(peopleCount < maxAmount) {
            new Thread(() -> System.out.println("A person comes into the library, because there is an empty seat")).start();
        } else {
            new Thread(() -> System.out.println("A person is standing at the door of the library, because the library is full")).start();
        }


        new Thread(() -> {

            Random r = new Random();
            int millisecondsToSleep = r.nextInt(4000) + 1000;
            try {
                semaphore.acquire();
                System.out.printf("A man reading a book " + millisecondsToSleep + " milliseconds\n");

                Thread.sleep(millisecondsToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } semaphore.release();
        }).start();


        new Thread(() -> System.out.println("The person left the library")).start();

    }
}