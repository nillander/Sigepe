package br.com.nillander.sigepe;

import com.formdev.flatlaf.FlatLaf;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.nillander.sigepe.autenticacao.view.Autenticacao;
import br.com.nillander.sigepe.compartilhado.utils.WindowsThemeChecker;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class App {

    private static App instance;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    private static ApplicationContext context;

    public ApplicationContext getContext() {
        return context;
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes");

        if (WindowsThemeChecker.isWindowsDarkMode()) {
            FlatMacDarkLaf.setup();
        } else {
            FlatMacLightLaf.setup();
        }

        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        context = SpringApplication.run(App.class, args);

        EventQueue.invokeLater(() -> Autenticacao.getInstance().setVisible(true));
    }
}
