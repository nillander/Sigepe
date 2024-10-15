package br.com.nillander.sigepe.compartilhado.services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class ImageService {

    private static ImageService instance;

    public static ImageService getInstance() {
        if (instance == null) {
            instance = new ImageService();
        }
        return instance;
    }

    /**
     * Redimensiona a imagem de um ImageIcon para o tamanho especificado.
     *
     * @param icon   O ImageIcon a ser redimensionado.
     * @param square A largura e altura desejadas.
     * @return Um novo ImageIcon redimensionado.
     */
    public ImageIcon resizeImageIcon(ImageIcon icon, int square) {
        return resizeImageIcon(icon, square, square);
    }

    /**
     * Redimensiona a imagem de um ImageIcon para o tamanho especificado.
     *
     * @param icon   O ImageIcon a ser redimensionado.
     * @param width  A largura desejada.
     * @param height A altura desejada.
     * @return Um novo ImageIcon redimensionado.
     */
    public ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        try {
            if (icon != null) {
                Image image = icon.getImage(); // Obtém a imagem original
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona
                return new ImageIcon(scaledImage); // Retorna a nova imagem redimensionada
            } else {
                // Retorna uma imagem temporária com a mensagem "Imagem não encontrada"
                return createPlaceholderImage(width, height, "Imagem não encontrada");
            }
        } catch (Exception e) {
            // Em caso de erro, cria uma imagem temporária indicando erro
            return createPlaceholderImage(width, height, "Erro ao carregar imagem");
        }
    }

    /**
     * Cria uma imagem temporária em memória para indicar que a imagem original não
     * foi encontrada.
     *
     * @param width   A largura da imagem.
     * @param height  A altura da imagem.
     * @param message A mensagem a ser exibida na imagem.
     * @return Um ImageIcon com a imagem gerada.
     */
    private ImageIcon createPlaceholderImage(int width, int height, String message) {
        // Cria um BufferedImage em memória
        BufferedImage placeholder = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Obtém o contexto gráfico 2D da imagem
        Graphics2D g2d = placeholder.createGraphics();

        // Define uma cor de fundo e preenche a imagem
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, width, height);

        // Define a cor e a fonte para a mensagem
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));

        // Centraliza a mensagem na imagem
        int textWidth = g2d.getFontMetrics().stringWidth(message);
        int x = (width - textWidth) / 2;
        int y = height / 2;

        g2d.drawString(message, x, y);

        // Libera os recursos gráficos
        g2d.dispose();

        // Retorna a imagem como um ImageIcon
        return new ImageIcon(placeholder);
    }
}
