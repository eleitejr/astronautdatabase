package utility;

import modelo.Astronauta;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by erasmo.leite on 24/05/2016.
 */
public class FormatadorDeImagem {

    /******************************************************
     TRATAMENTO DA IMAGEM DO VIAJANTE ESPACIAL
     ******************************************************/

    // recebe uma imagem e retorna essa imagem no padrão LARGURA = larguraJanela, ALTURA = alturaJanela
    public static BufferedImage formataImagem(BufferedImage bi, int largura, int altura) throws HeadlessException {

        BufferedImage aux = new BufferedImage(largura, altura, bi.getType());
        Graphics2D gg = aux.createGraphics();
        AffineTransform at = AffineTransform.getScaleInstance((double) largura /bi.getWidth(), (double) altura /bi.getHeight());
        gg.drawRenderedImage(bi, at);
        return aux;
    }



    /***************************************************
     *  PREPARAÇÃO DAS IMAGENS (usar diretório 'imagens')
     ***************************************************/
    public static void preparaImagens(ArrayList<Astronauta> astronautas) {
        BufferedImage imagem = null;
        for (Astronauta astronauta : astronautas) {

            try {
                File entrada = new File("./imagens/people/" + astronauta.getFoto());

                imagem = FormatadorDeImagem.formataImagem(ImageIO.read(entrada), 140, 210);

                ImageIO.write(imagem, "jpg", entrada);

            } catch (IOException ignored) {
            }
        }
    }


}

