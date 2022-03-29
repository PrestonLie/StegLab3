import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Point;


public class PixelPlayground {
    //Remove all blue from ever pixel
    public static Picture zeroBlue(Picture p)
    {
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for(int r = 0; r < pixels.length ; r++)
        {
            for(int c = 0; c < pixels[0].length; c++)
            {
                pixels[r][c].setBlue(0);
            }
        }
        return newPic;
    }
    public static Picture onlyBlue(Picture p)
    {
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for(int r = 0; r < pixels.length ; r++)
        {
            for(int c = 0; c < pixels[0].length; c++)
            {
                pixels[r][c].setRed(0);
                pixels[r][c].setGreen(0);   
            }
        }
        return newPic;
    }
    public static Picture negate(Picture p)
    {
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for(int r = 0; r < pixels.length ; r++)
        {
            for(int c = 0; c < pixels[0].length; c++)
            {
                int numRed = pixels[r][c].getRed();
                pixels[r][c].setRed(255 - numRed);
                pixels[r][c].setGreen(255 - (pixels[r][c].getGreen()));   
                pixels[r][c].setBlue(255 - (pixels[r][c].getBlue())); 
            }
        }
        return newPic;
    }
    public static Picture grayScale(Picture p)
    {
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for(int r = 0; r < pixels.length ; r++)
        {
            for(int c = 0; c < pixels[0].length; c++)
            {
                int numRed = pixels[r][c].getRed();

                int totalNum = (pixels[r][c].getGreen()) + (pixels[r][c].getBlue()) + (pixels[r][c].getRed());
                int average = totalNum / 3;
                pixels[r][c].setRed(average);
                pixels[r][c].setGreen(average);   
                pixels[r][c].setBlue(average); 
            }
        }
        return newPic;
    }
    public static Picture fixUnderwater(Picture p)
    {
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        for(int r = 0; r < pixels.length ; r++)
        {
            for(int c = 0; c < pixels[0].length; c++)
            {
                int numRed = pixels[r][c].getRed();

            }
        }
        return newPic;
    }
    /** Mirrors left part of image to the right side
     * 
     * @param p original image
     * @return new image with mirroring from left to right
     */
    public static Picture mirrorImageHor(Picture p){
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        Pixel left = null;
        Pixel right = null;
        for(int r = 0; r < pixels.length ; r++)
        {
            for(int c = 0; c < pixels[0].length / 2; c++)
            {
                left = pixels[r][c];
                right = pixels[r][pixels[0].length-1-c];
                right.setColor(left.getColor());

            }
        }
        return newPic;
    }

    //top to bottom
    public static Picture mirrorImageVertical(Picture p){
        Picture newPic = new Picture(p); //Copy p so as not to destroy persistent data
        //To be implemented
        Pixel[][] pixels = newPic.getPixels2D();
        Pixel top = null;
        Pixel bot = null;
        for(int r = 0; r < pixels.length / 2; r++)
        {
            for(int c = 0; c < pixels[0].length; c++)
            {
                top = pixels[r][c];
                bot = pixels[pixels.length -1 - r][c];
                bot.setColor(top.getColor());
            }
        }
        return newPic;
    }
    public static boolean canHide(Picture p, Picture h)
    {

        Pixel[][] picPixels = p.getPixels2D();
        Pixel[][] hiddenPixels = h.getPixels2D();
        /** 
* Determines whether secret can be hidden in source, which is true if source and secret are the same dimensions. 
* @param source is not null 
* @param secret is not null 
* @return true if secret can be hidden in source, false otherwise. 
*/

        if (picPixels.length >= hiddenPixels.length && picPixels[0].length >= hiddenPixels[0].length)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public static Color setLow(Pixel pix, Color color){

        int red = pix.getRed()/64;
        int green = pix.getGreen()/64;
        int blue = pix.getBlue()/64;

        pix.setRed(pix.getRed()/4*4 + red);
        pix.setGreen(pix.getGreen()/4*4 + green);
        pix.setBlue(pix.getBlue()/4*4 + blue);

        return pix.getColor();
    }

    public static Picture hidePicture(Picture p, Picture h, int x, int y)
    {
        Picture pic = new Picture(p);
        Picture hidden = new Picture(h);

        Pixel[][] picPixels = pic.getPixels2D();
        Pixel[][] hiddenPixels = hidden.getPixels2D();

        for(int row = y, row2 = 0;  row < picPixels.length && row2 < hiddenPixels.length; row++, row2++){
            for(int col = x, col2 = 0; col < picPixels[row].length && col2 < hiddenPixels[row].length; col++, col2++){
               System.out.println(col + " " + row + " " + col2 + " " + row2);
                picPixels[row][col].setColor(setLow(picPixels[row][col], hiddenPixels[row2][col2].getColor()));
            }
        }

        return pic;
        /** 
        * Creates a new Picture with data from secret hidden in data from source * @param source is not null 
        * @param secret is not null 
        * @return combined Picture with secret hidden in source 
        * precondition: source is same width and height as secret 
        */
    }

    public static boolean isSame(Picture p, Picture h)
    {
        Picture pic1 = new Picture(p);
        Picture pic2 = new Picture(h);

        Pixel[][] pic1Pixels = pic1.getPixels2D();
        Pixel[][] pic2Pixels = pic2.getPixels2D();

        for(int row = 0; row < pic1Pixels.length; row++){
            for(int col = 0; col < pic1Pixels[row].length; col++){
                if(!pic1Pixels[row][col].getColor().equals(pic2Pixels[row][col].getColor())){
                    return false;
                } 
            }
        }

        return true;
        /** 
        * Creates a new Picture with data from secret hidden in data from source * @param source is not null 
        * @param secret is not null 
        * @return combined Picture with secret hidden in source 
        * precondition: source is same width and height as secret 
        */
    }

    public static ArrayList<Point> findDifferences(Picture p1, Picture p2)
    {
        
        Pixel[][] p1Pixels = p1.getPixels2D();
        Pixel[][] p2Pixels = p2.getPixels2D();

        ArrayList<Point> pointList = new ArrayList<Point>();

        if (p1Pixels.length == p2Pixels.length && p1Pixels[0].length == p2Pixels[0].length){
            System.out.println("test");
            for(int r = 0; r < p1Pixels.length; r++){
                for(int c = 0; c < p1Pixels[0].length; c++){
                    if(!p1Pixels[r][c].getColor().equals(p2Pixels[r][c].getColor())){
                        Point temp = new Point(p1Pixels[r][c].getX(),p1Pixels[r][c].getY());
                        pointList.add(temp);
                    }
                }
            }
        }
        /** 
        * Creates a new Picture with data from secret hidden in data from source * @param source is not null 
        * @param secret is not null 
        * @return combined Picture with secret hidden in source 
        * precondition: source is same width and height as secret 
        */
        return pointList;
    }

    public static Picture showDifferenceArea(ArrayList<Point> list, Picture comPic){

        Pixel[][] pixels = comPic.getPixels2D();
        for(int i = 0; i < list.size(); i ++){
            pixels[(int)list.get(i).getY()][(int)list.get(i).getX()].setColor(Color.RED);
        }

        return comPic;
    }

    public static Picture revealPicture(Picture hidden){

        Picture copy = new Picture(hidden);
        Pixel[][] pixels = copy.getPixels2D();
        Pixel[][] source = copy.getPixels2D();

        for(int r = 0; r < pixels.length; r++){
            for(int c = 0; c < pixels[0].length; c++){
                Color col = source[r][c].getColor();
                pixels[r][c].setRed(col.getRed()%4*64);
                pixels[r][c].setBlue(col.getBlue()%4*64);
                pixels[r][c].setGreen(col.getGreen()%4*64);
            }
        }

        return copy;

    }

    public static void main(String[] args)
    {/*
        Picture beach = new Picture("beach.jpg");
        Picture snowman = new Picture("snowman.jpg");

        System.out.println(snowman.getHeight());
        System.out.println(snowman.getWidth());
        System.out.println(beach.getHeight());
        System.out.println(beach.getWidth());
        if(canHide(beach, snowman)){
            showDifferenceArea(findDifferences(beach,hidePicture(beach, snowman, 0, 0)), hidePicture(beach, snowman, 100, 50)).explore();
        }*/

        Picture beach = new Picture("beach.jpg"); 
Picture robot = new Picture("robot.jpg"); 
Picture flower1 = new Picture("flower1.jpg");
beach.explore(); 

// these lines hide 2 pictures
Picture hidden1 = hidePicture(beach, robot, 65, 208);
Picture hidden2 = hidePicture(hidden1, flower1, 280, 110);
hidden2.explore(); 
Picture unhidden = revealPicture(hidden2);
unhidden.explore(); 

    }
}
