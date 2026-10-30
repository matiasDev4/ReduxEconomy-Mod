package com.devkka.reduxeconomy.config;

import com.devkka.reduxeconomy.database.DatabaseManager;
import com.devkka.reduxeconomy.economy.WalletRepository;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class ConfigManager {
    private DatabaseManager databaseManager;

    public ConfigManager (DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void loadConfig() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            try {
                databaseManager.initialize();
                System.out.println("[ReduxEconomy] Base de datos inicializada");
            } catch (Exception e) {
                System.out.println("[ReduxEconomy] No se pudo inicializar " + e);
            }
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            try {
                databaseManager.close();
            } catch (Exception e) {
                System.out.println("[ReduxEconomy] No se pudo cerrar la DB");
            }
        });
    }

}
