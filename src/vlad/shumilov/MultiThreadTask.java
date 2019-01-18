package vlad.shumilov;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadTask {

    private static int mCount = 0;
    private ArrayList<Future> futures = new ArrayList<>();
    private ReentrantLock locker = new ReentrantLock();

    public void countPeopleInBuilding() {
        long startTime = System.nanoTime();

        mCount = 0;

        for (int x = 0; x < 15; x++) {
            int y = 0;

            while (y < 100) {
                y++;

                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                mCount++;
            }
        }

        System.out.println("total brute force : " + mCount + "\n");

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println("total brute duration: " + duration + "\n");
    }

    public void countPeopleInBuildingBySimpleThreads() {
        mCount = 0;

        for (int x = 0; x < 10; x++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int y = 0; y < 100; y++) {
                        mCount++;

                        System.out.println(mCount + " " + Thread.currentThread().getName() + "\n");

                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        System.out.println(mCount + " total\n");
    }

    public void countPeopleInBuildingByExecutors() {
        long startTime = System.nanoTime();

        mCount = 0;
        futures = new ArrayList<>();

        int floors = 15;

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int x = 0; x < floors; x++) {
            Future<Integer> future = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int y = 0;

                    while (y < 100) {
                        y++;

                        try {
                            TimeUnit.MILLISECONDS.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    locker.lock();

                    mCount += y;

                    locker.unlock();

                    return y;
                }
            });

            futures.add(future);
        }

        while (!isDoneAllFutures()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {

            }
        }

        System.out.println("executors total: " + mCount + "\n");

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        System.out.println("executors duration: " + duration + "\n");
    }

    protected boolean isDoneAllFutures() {
        if (futures.isEmpty()) {
            return false;
        }

        for (Future<Integer> future : futures) {
            if (!future.isDone()) {
                return false;
            }
        }

        return true;
    }
}
