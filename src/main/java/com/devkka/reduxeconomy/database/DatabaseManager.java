package com.devkka.reduxeconomy.database;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private Connection connection;

    public void initialize() throws Exception {

        Path directory = FabricLoader.getInstance().getConfigDir()
                .resolve("redux-economy");

        Files.createDirectories(directory);

        Path databasePath = directory.resolve("reduxEconomy.db");

        String url = "jdbc:sqlite:" + databasePath.toAbsolutePath();

        connection = DriverManager.getConnection(url);

        try (Statement statement = connection.createStatement()) {

          statement.execute("""
            CREATE TABLE IF NOT EXISTS wallets (
                player_uuid TEXT PRIMARY KEY,
                balance INTEGER NOT NULL DEFAULT 0
                    CHECK(balance >= 0)
            )
            """);
        }

    }
    public synchronized Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException(
                    "La base de datos no está inicializada"
            );
        }

        return connection;
    }

    public synchronized void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        connection = null;
    }
}
