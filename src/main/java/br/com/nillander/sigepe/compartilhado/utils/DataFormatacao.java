package br.com.nillander.sigepe.compartilhado.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataFormatacao {

    // Formato de data e hora brasileiro
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Método para formatar LocalDateTime em uma string legível
    public static String format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return ""; // Retorna vazio se a data for nula
        }
        return dateTime.format(dateTimeFormatter);
    }

    public static String apenasData(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (dateTime == null) {
            return ""; // Retorna vazio se a data for nula
        }
        return dateTime.format(dateTimeFormatter);
    }

    public static String apenasDataExtensa(LocalDateTime dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        if (dateTime == null) {
            return ""; // Retorna vazio se a data for nula
        }
        return dateTime.format(dateTimeFormatter);
    }
}
