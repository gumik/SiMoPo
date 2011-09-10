
/** 2011-01-12
 * @author Krzysztof Mataj
 */
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;

public class MapCanvasEx extends Canvas implements LocationListener {

    public MapCanvasEx(int level, int x, int y) {

        this.level = level;
        this.tile_x = x;
        this.tile_y = y;
        tiles = new Image[2][2];
        x = 0;
        y = 0;
        width = this.getWidth();
        height = this.getHeight();
        step = Math.min(width, height) / 10;

        max = 1 << level;

        tiles[0][0] = getTile(level, tile_x, tile_y);
        tiles[1][0] = getTile(level, tile_x+1, tile_y);
        tiles[0][1] = getTile(level, tile_x, tile_y+1);
        tiles[1][1] = getTile(level, tile_x+1, tile_y+1);

        try {
            locationProvider = LocationProvider.getInstance(new Criteria());
        } catch (LocationException ex) {
            ex.printStackTrace();
        }

        locationProvider.setLocationListener(this, 1, -1, -1);
    }

    public void moveLeft() {
//        if (tile_x > 0) {
//            tile_x--;
//        }
        x-=step;
    }

    public void moveRight() {
//        if (tile_x < max - 1) {
//            tile_x++;
//        }
        x+=step;
    }

    public void moveUp() {
//        if (tile_y > 0) {
//            tile_y--;
//        }
        y-=step;
    }

    public void moveDown() {
//        if (tile_y < max - 1) {
//            tile_y++;
//        }
        y+=step;
    }

    public void levelDown() {
        if (level < 18) {
            level++;
            tile_x *= 2;
            tile_y *= 2;
            max *= 2;
        }
    }

    public void levelUp() {
        if (level > 0) {
            level--;
            tile_x /= 2;
            tile_y /= 2;
            max /= 2;
        }
    }

    protected void paint(Graphics g) {
        //g.drawImage(tile, 0, 0, Graphics.LEFT | Graphics.TOP);
        int width = tiles[0][0].getWidth();
        int height = tiles[0][0].getHeight();

        g.drawImage(tiles[0][0], -x, -y, Graphics.LEFT | Graphics.TOP);
        g.drawImage(tiles[0][1], -x, height-y, Graphics.LEFT | Graphics.TOP);
        g.drawImage(tiles[1][0], width-x, -y, Graphics.LEFT | Graphics.TOP);
        g.drawImage(tiles[1][1], width-x, height-y, Graphics.LEFT | Graphics.TOP);

        g.drawString("Lon: "+ longitude + "\n"
                + "Lat: " + latitude, 0, 0, Graphics.LEFT | Graphics.TOP);
//        g.drawString(level + "/" + tile_x + "/" + tile_y, 0, 0, Graphics.LEFT | Graphics.TOP);

    }

    protected void keyPressed(int keyCode) {
        if (keyCode == KEY_POUND) {
            levelDown();
        } else if (keyCode == KEY_STAR) {
            levelUp();
        }

        int key = this.getGameAction(keyCode);

        if (key == DOWN) {
            moveDown();
        } else if (key == UP) {
            moveUp();
        } else if (key == LEFT) {
            moveLeft();
        } else if (key == RIGHT) {
            moveRight();
        }

        tile = getTile(level, tile_x, tile_y);
        repaint();
    }



    private Image getTile(int level, int x, int y) {
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
            ex.printStackTrace();
        }

        return image;
    }

    public void locationUpdated(LocationProvider lp, Location lctn) {
            latitude = lctn.getQualifiedCoordinates().getLatitude();
            longitude = lctn.getQualifiedCoordinates().getLongitude();
        repaint();
    }

    public void providerStateChanged(LocationProvider lp, int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private LocationProvider locationProvider;
    private int tile_x, tile_y, level;
    private int max;
    private double latitude, longitude;
    private Image tile;
    private Image[][] tiles;
    private int x, y;
    private int width, height;
    private int step;
}