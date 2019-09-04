package infraestructure.batchs;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * This class will resize all the images in a given folder
 * @author pankaj
 *
 */
public class ImageResizer {


    
    /**
     * Procesa una imagen con los requerimientos especificados:
     *  - Redimensiona a 800x600
     *  - Añade texto en la parte inferior de la imagen.
     *  - Guarda resultado en PNG
     * @param sourcePath Ruta original de la imagen (ruta y nombre del archivo)
     * @param targetPath Ruta de destino de la imagen (ruta y nombre del archivo)
     * @param width Ancho de la imagen
     * @param height Alto de la imagen
     * @param keepAspectRatio Indica si mantiene el ratio de aspecto (true) o si debe forzar a que sea 800x600 (false)
     * @param text  Texto a añadir en la parte inferior.
     * @return Indica si se pudo procesar la imagen (true) o si ocurrió algún error (falase)
     */
    public boolean processImage(String sourcePath, String targetPath, int width, int height, boolean keepAspectRatio, String text){
        Image img = null; 
        BufferedImage tempPNG = null;        
        File newFilePNG = null;
        try{
            img = ImageIO.read(new File(sourcePath));
            double aspectRatio = 1;
            if (keepAspectRatio){
                aspectRatio = (double) img.getWidth(null)/(double) img.getHeight(null);
                tempPNG = resizeImage(img, width, (int) (width/aspectRatio));            
            }else{
                tempPNG = resizeImage(img, width, height);            
            }
            if (text != null) {
                Graphics g = tempPNG.getGraphics();
                g.setFont(g.getFont().deriveFont(22f));
                g.drawString(text, 10, tempPNG.getHeight() - 30);
                g.dispose();
            }
            newFilePNG = new File(targetPath+"_New.png");
            ImageIO.write(tempPNG, "png", newFilePNG);            
        }catch(IOException ex){
            System.out.println ("No se pudo convertir");
            return false;
        }
        return true;
    }
    
    /**
     * Redimensiona la imagen.
     * @param image Imagen a redimensionar
     * @param width Nuievo ancho
     * @param height Nueva altura
     * @return objeto BufferImage que puede ser guardado nuevamente.
     */
    public BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
       
}