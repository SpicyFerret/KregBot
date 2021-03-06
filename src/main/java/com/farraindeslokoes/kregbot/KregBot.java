package com.farraindeslokoes.kregbot;

import com.farraindeslokoes.kregbot.commands.Commands;
import com.farraindeslokoes.kregbot.constants.Constants;
import com.farraindeslokoes.kregbot.events.HelloEvent;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.security.auth.login.LoginException;

/** Classe principal. Ainda ha algumas coisas a fazer, como melhorar as Exceptions...
 *  Usa Spring para facilitar "compilacao" no host remoto, com uma port especifica.
 *  EventListeners sao auto-explicativos.
 * @author Nukeologist
 * @since 0.1
 */
@SpringBootApplication
public class KregBot {

    private static JDA bot;
    private static Commands commands;

    public static void main (String[] args) {
        SpringApplication.run(KregBot.class, args);
        commands = new Commands();
        Discord ();

    }

    private static void Discord(){
        try {

            bot = new JDABuilder(AccountType.BOT).setToken(Constants.discordToken).setGame(Game.playing("say !help")).build().awaitReady();
            //Commands commands = new Commands();
            bot.addEventListener(new HelloEvent());
            bot.addEventListener(commands);


        }catch (LoginException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static Commands getCommands(){
        return commands;
    }

}
