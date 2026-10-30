package com.devkka.reduxeconomy.commands;

import com.devkka.reduxeconomy.ReduxEconomy;
import com.devkka.reduxeconomy.economy.WalletRepository;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.HashMap;
import java.util.List;

public class PlayerCommands {
    private static int BalanceCommand(CommandContext<CommandSourceStack> context) {
        ServerPlayer player;

        try {
            player = context.getSource().getPlayerOrException();
        } catch (CommandSyntaxException e) {
            context.getSource().sendSystemMessage(
                    Component.literal("Solo puede ser ejecutado por un usuario")
            );
            return 0;
        }

        WalletRepository repository = ReduxEconomy.getInstance().getWalletRepository();
        try {
            long balance = repository.getBalance(player.getUUID());
            context.getSource().sendSystemMessage(Component.literal("Dinero actual: $" + balance));

        } catch (Exception e) {
            context.getSource().sendSystemMessage(
                    Component.literal("Ocurrio un error inesperado " + e)
            );
        }
        return 1;
    }

    private static int setBalance(CommandContext<CommandSourceStack> context) {
        ServerPlayer player;

        try {
            player = EntityArgument.getPlayer(context, "player");
        } catch (CommandSyntaxException e) {
            context.getSource().sendSystemMessage(
                    Component.literal("Usuario no encontrado")
            );
            return 0;
        }

        long amount = LongArgumentType.getLong(context, "amount");

        WalletRepository repository = ReduxEconomy.getInstance().getWalletRepository();

        try {
            boolean isDeposit = repository.deposit(player.getUUID(), amount);
            if (isDeposit) {
                context.getSource().sendSystemMessage(Component.literal("$%s fueron añadidos a tu cuenta!".formatted(amount)));
            }


        } catch (Exception e) {
            context.getSource().sendSystemMessage(
                    Component.literal("Ocurrio un error inesperado " + e)
            );
        }
        return 1;
    }
    public static void register() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    Commands.literal("balance")
                            .executes(PlayerCommands::BalanceCommand)
            );
            dispatcher.register(Commands.literal("economy")
                    .then(Commands.literal("set")
                            .requires(commandSourceStack -> commandSourceStack.hasPermission(3))
                            .then(Commands.argument("player", EntityArgument.player())
                                    .then(Commands.argument("amount", LongArgumentType.longArg(0))
                                            .executes(PlayerCommands::setBalance)
                                    )
                            )
                    ));
        }));
    }

}
