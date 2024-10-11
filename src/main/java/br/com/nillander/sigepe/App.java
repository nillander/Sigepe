package br.com.nillander.sigepe;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import br.com.nillander.sigepe.autenticacao.view.Autenticacao;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.themes");

        if (WindowsThemeChecker.isWindowsDarkMode()) {
            FlatMacDarkLaf.setup();
        } else {
            FlatMacLightLaf.setup();
        }

        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));

        EventQueue.invokeLater(() -> Autenticacao.getInstance().setVisible(true));
    }
}
