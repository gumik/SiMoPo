/** 2011-09-11
 * Krzysztof Mataj
 */
package pl.edu.uj.ii;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;

public class ImageCache {
    
    public ImageCache() {
        imageMap = new Hashtable();
    }
    
    public Image getImage(int level, int x, int y) {
        TileInfo tileInfo = new TileInfo(level, x, y);
        Image image = (Image) imageMap.get(tileInfo);
        
        if (image == null) {
            System.out.println("image == null");
            image = getImageFromServer(level, x, y);
            imageMap.put(tileInfo, image);
        } 
        
        return image;
    }
    
    private Image getImageFromServer(int level, int x, int y) {
        System.out.println("getImageFromServer(" + level + ", " + x + ", " + y + ")");
        HttpConnection connection;
        Image image = null;

        try {
            connection = (HttpConnection) Connector.open(
                    "http://tile.openstreetmap.org/"
                    + level + "/" + x + "/" + y + ".png");

            InputStream is = connection.openInputStream();
            image = Image.createImage(is);
            connection.close();
        } catch (IOException ex) {
            // TODO: show some message
            ex.printStackTrace();
        }

        return image;
    }
    
    private Hashtable imageMap;
}

class TileInfo {
    
    private int level;
    private int x;
    private int y;
    
    public TileInfo(int level, int x, int y) {
        this.level = level;
        this.x = x;
        this.y = y;
    }

    public int getLevel() {
        return level;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int hashCode() {
        return x + y << 14 + level << 18;
    }
    
    public boolean equals(Object obj) {
        if (!(obj instanceof TileInfo)) {
            return false;
        }
        
        TileInfo t = (TileInfo) obj;
        return t.level == level && t.x == x && t.y == y;
    }
}
