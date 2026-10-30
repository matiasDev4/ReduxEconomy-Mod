package com.devkka.reduxeconomy.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseExecutor {

    private final ExecutorService executor =
            Executors.newSingleThreadExecutor(task -> {
                Thread thread = new Thread(
                        task,
                        "ReduxEconomy-Database"
                );

                thread.setDaemon(true);
                return thread;
            });

    public void execute(Runnable task) {
        executor.submit(task);
    }

    public void shutdown() {
        executor.shutdown();
    }
}