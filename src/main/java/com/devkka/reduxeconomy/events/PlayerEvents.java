package com.devkka.reduxeconomy.events;
import com.devkka.reduxeconomy.ReduxEconomy;
import com.devkka.reduxeconomy.database.DatabaseExecutor;
import com.devkka.reduxeconomy.economy.WalletRepository;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;

import java.sql.SQLException;

public class PlayerEvents {

    private final DatabaseExecutor databaseExecutor;


    public PlayerEvents(
            DatabaseExecutor databaseExecutor
    ) {
        this.databaseExecutor = databaseExecutor;

    }

    public void register() {
        ServerPlayConnectionEvents.JOIN.register(
                (handler, sender, server) -> {
                    ServerPlayer player = handler.getPlayer();

                    ReduxEconomy.LOGGER.info(
                            "El jugador {} ha entrado al servidor",
                            player.getName().getString()
                    );

                    databaseExecutor.execute(() -> {
                        try {
                            WalletRepository walletRepository = ReduxEconomy.getInstance().getWalletRepository();
                            walletRepository.createWallet(player.getUUID());
                        } catch (SQLException e) {
                            ReduxEconomy.LOGGER.error(
                                    "Error al crear la cuenta económica",
                                    e
                            );
                        }
                    });
                }
        );
    }
}

