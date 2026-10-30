package com.devkka.reduxeconomy.economy;

import com.devkka.reduxeconomy.database.DatabaseManager;

import java.sql.SQLException;
import java.util.UUID;

public class WalletRepository {
    private DatabaseManager databaseManager;

    public WalletRepository (DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public long getBalance(UUID playerId) throws SQLException  {
        String query = "SELECT balance FROM wallets WHERE player_uuid = ?";
        try (var statement = databaseManager.getConnection().prepareStatement(query)) {
            statement.setString(1, playerId.toString());

            try (var result = statement.executeQuery()) {
                if (result.next()) {
                    return result.getLong("balance");
                }
                return 0;
            }
        }
    }

    public void createWallet(UUID playerId) throws SQLException {
        String query = "INSERT OR IGNORE INTO wallets (player_uuid) VALUES(?)";
        try (var statement = databaseManager.getConnection().prepareStatement(query)) {
            statement.setString(1, playerId.toString());
            statement.executeUpdate();
        }
    }

    public boolean deposit(UUID playerId, long amount) throws SQLException {
        String query = "UPDATE wallets SET balance = balance + ? WHERE player_uuid = ? ";
        try (var statement = databaseManager.getConnection().prepareStatement(query)) {
            statement.setLong(1, amount);
            statement.setString(2, playerId.toString());
            return statement.executeUpdate() > 0;
        }
    }

    public boolean withDraw(UUID playerId, long amount) throws SQLException {
        String query = "UPDATE wallets SET balance = balance - ? WHERE player_uuid = ? ";
        try (var statement = databaseManager.getConnection().prepareStatement(query)) {
            statement.setLong(1, amount);
            statement.setString(2, playerId.toString());
            return statement.executeUpdate() > 0;
        }
    }



}
