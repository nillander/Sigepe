package br.com.nillander.sigepe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WindowsThemeChecker {

    public static boolean isWindowsDarkMode() {
        try {
            Process process = Runtime.getRuntime().exec(
                    "reg query HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize /v AppsUseLightTheme");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("0x0")) {
                    return true; // O valor 0x0 indica modo escuro
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Se houver exceção, assumimos que NÃO está no modo escuro
        return false;
    }

}
