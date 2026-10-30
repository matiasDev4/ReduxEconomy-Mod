package com.devkka.reduxeconomy;

import com.devkka.reduxeconomy.api.EconomyService;
import com.devkka.reduxeconomy.commands.PlayerCommands;
import com.devkka.reduxeconomy.config.ConfigManager;
import com.devkka.reduxeconomy.database.DatabaseExecutor;
import com.devkka.reduxeconomy.database.DatabaseManager;
import com.devkka.reduxeconomy.economy.DefaultEconomyService;
import com.devkka.reduxeconomy.economy.WalletRepository;
import com.devkka.reduxeconomy.events.PlayerEvents;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReduxEconomy implements ModInitializer {
	public static final String MOD_ID = "reduxeconomy";
	private static ReduxEconomy instance;
	private final EconomyService economyService = new DefaultEconomyService();
	private final DatabaseManager databaseManager;
	private final WalletRepository walletRepository;
	private final ConfigManager configManager;
	private final DatabaseExecutor databaseExecutor;

	public ReduxEconomy() {
		this.databaseManager = new DatabaseManager();
		this.walletRepository = new WalletRepository(databaseManager);
		this.configManager = new ConfigManager(databaseManager);
		this.databaseExecutor = new DatabaseExecutor();
	}

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
		instance = this;
		PlayerEvents playerEvents = new PlayerEvents(databaseExecutor);
		PlayerCommands.register();
		playerEvents.register();
		configManager.loadConfig();

		LOGGER.info("ReduxEconomy inicializado");
	}

	public static ReduxEconomy getInstance() {
		return instance;
	}

	public WalletRepository getWalletRepository() {
		return walletRepository;
	}

	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	}
	public EconomyService getEconomyService() {
		return economyService;
	}
	public ConfigManager getConfigManager() {
		return configManager;
	}
	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
