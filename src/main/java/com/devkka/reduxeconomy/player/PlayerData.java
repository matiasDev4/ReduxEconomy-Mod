package com.devkka.reduxeconomy.player;

import java.util.UUID;

public record PlayerData (
    UUID UUID,
    String username,
    long balance
){}
