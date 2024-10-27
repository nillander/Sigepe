package br.com.nillander.sigepe.compartilhado.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppLogger {

    private static FileHandler fileHandler;

    static {
        try {
            // Define o arquivo de log, com formato simples e append
            fileHandler = new FileHandler("logs/sigepe_log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            Logger.getLogger(AppLogger.class.getName()).log(Level.SEVERE, "Erro ao configurar o arquivo de log", e);
        }
    }

    private AppLogger() {
        // Evita instanciamento
    }

    public static void log(Class<?> clazz, Throwable throwable) {
        Logger.getLogger(clazz.getName()).log(Level.SEVERE, null, throwable);
    }
}
