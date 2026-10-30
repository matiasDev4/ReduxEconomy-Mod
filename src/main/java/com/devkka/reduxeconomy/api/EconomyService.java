package com.devkka.reduxeconomy.api;

import com.devkka.reduxeconomy.ReduxEconomy;

import java.util.UUID;

public interface EconomyService {

    static EconomyService instance() {
        return ReduxEconomy.getInstance().getEconomyService();
    }
    long getBalance(UUID playerId);
    boolean deposit(UUID playerId, long amount);
    boolean hasBalance(UUID playerId, long amount);
    boolean withDraw(UUID playerId, long amount);
}


