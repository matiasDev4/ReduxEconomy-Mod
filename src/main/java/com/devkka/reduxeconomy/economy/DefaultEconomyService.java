package com.devkka.reduxeconomy.economy;

import com.devkka.reduxeconomy.ReduxEconomy;
import com.devkka.reduxeconomy.api.EconomyService;

import java.util.UUID;

public class DefaultEconomyService implements EconomyService {

    @Override
    public long getBalance(UUID playerId) {
        WalletRepository repository = ReduxEconomy.getInstance().getWalletRepository();
        try {
            return repository.getBalance(playerId);
        } catch (Exception e) {
            System.out.println("Error");
            return 0;
        }
    }
    @Override
    public boolean hasBalance(UUID playerId, long amount) {
        return false;
    }
    @Override
    public boolean deposit(UUID playerId, long amount) {
        WalletRepository repository = ReduxEconomy.getInstance().getWalletRepository();
        try {
            return repository.deposit(playerId, amount);
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }
    @Override
    public boolean withDraw(UUID playerId, long amount) {
        WalletRepository repository = ReduxEconomy.getInstance().getWalletRepository();
        try {
            return repository.withDraw(playerId, amount);
        } catch (Exception e) {
            System.out.println("Error");
            return false;
        }
    }
}
