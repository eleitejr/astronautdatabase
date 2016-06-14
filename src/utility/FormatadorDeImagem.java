package utility;

import modelo.Astronauta;
import modelo.Pais;

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
     *  PREPARACAO DAS IMAGENS (usar diretorio 'imagens')
     ***************************************************/
    public static void preparaImagens(ArrayList<Astronauta> astronautas) {
        BufferedImage imagem = null;
        for (Astronauta astronauta : astronautas) {

            try {
                File entrada = new File("./imagens/people/" + astronauta.getFoto());

                imagem = FormatadorDeImagem.formataImagem(ImageIO.read(entrada), 140, 210);

                ImageIO.write(imagem, "jpg", entrada);
                
                System.out.println("Formatando imagem " + entrada.toString());

            } catch (IOException ignored) {
            }
        }

        ArrayList<String> bandeiras = new ArrayList<>();

        bandeiras.add("AFG.jpg");
        bandeiras.add("ZAF.jpg");
        bandeiras.add("DEU.jpg");
        bandeiras.add("SAU.jpg");
        bandeiras.add("AUT.jpg");
        bandeiras.add("BEL.jpg");
        bandeiras.add("BRA.jpg");
        bandeiras.add("BGR.jpg");
        bandeiras.add("SVK.jpg");
        bandeiras.add("USA.jpg");
        bandeiras.add("FRA.jpg");
        bandeiras.add("CAN.jpg");
        bandeiras.add("KAZ.jpg");
        bandeiras.add("CHN.jpg");
        bandeiras.add("KOR.jpg");
        bandeiras.add("CUB.jpg");
        bandeiras.add("HUN.jpg");
        bandeiras.add("ITA.jpg");
        bandeiras.add("IND.jpg");
        bandeiras.add("ISR.jpg");
        bandeiras.add("JPN.jpg");
        bandeiras.add("MYS.jpg");
        bandeiras.add("MEX.jpg");
        bandeiras.add("MNG.jpg");
        bandeiras.add("POL.jpg");
        bandeiras.add("CZE.jpg");
        bandeiras.add("GBR.jpg");
        bandeiras.add("ROU.jpg");
        bandeiras.add("RUS.jpg");
        bandeiras.add("SYR.jpg");
        bandeiras.add("SWE.jpg");
        bandeiras.add("CHE.jpg");
        bandeiras.add("UKR.jpg");
        bandeiras.add("VNM.jpg");

        imagem = null;
        for (String bandeira : bandeiras) {

            try {
                File entrada = new File("./imagens/flag_jpg/" + bandeira);

                imagem = FormatadorDeImagem.formataImagem(ImageIO.read(entrada), 200, 133);

                ImageIO.write(imagem, "jpg", entrada);

                System.out.println("Formatando imagem " + entrada.toString());

            } catch (IOException ignored) {
            }
        }

        System.out.println();
        System.out.println(astronautas.size() + " imagens formatadas no padrao 140px X 210px.");
        System.out.println(bandeiras.size() + " imagens formatadas no padrao 200px X 133px.");
    }
    
    public static void preparaBandeiras(ArrayList<Pais> paises) {
        BufferedImage imagem = null;
        for (Pais pais : paises) {

            try {
                File entrada = new File("./imagens/flags_big/" + pais.getId() + ".png");

                imagem = FormatadorDeImagem.formataImagem(ImageIO.read(entrada), 128, 86);

                ImageIO.write(imagem, "png", entrada);
                
                System.out.println("Formatando imagem " + entrada.toString());

            } catch (IOException ignored) {
            }
        }

        System.out.println();
        System.out.println(paises.size() + " imagens formatadas no padrao 128px X 86px.");
    }


}

