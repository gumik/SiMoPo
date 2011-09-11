package pl.edu.uj.ii;


/** 2011-01-12
 * @author Krzysztof Mataj
 */
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationListener;
import javax.microedition.location.LocationProvider;

public class MapCanvasEx extends Canvas {

    public MapCanvasEx(int level, int x, int y) {

        this.imageCache = new ImageCache();
        this.level = level;
        this.tile_x = x;
        this.tile_y = y;
        
        Image tile = imageCache.getImage(level, x, y);
        this.tileWidth = tile.getWidth();
        this.tileHeight = tile.getHeight();
        
        this.x = 0;
        this.y = 0;
        
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();

        this.center_x = screenWidth / 2;
        this.center_y = screenHeight / 2;
        
        step = Math.min(screenWidth, screenHeight) / 10;

        try {
            locationProvider = LocationProvider.getInstance(new Criteria());
        } catch (LocationException ex) {
            ex.printStackTrace();
        }

        locationProvider.setLocationListener(new LocationListener() {

            public void locationUpdated(LocationProvider lp, Location lctn) {
                locationUpdated2(lp, lctn);
            }

            public void providerStateChanged(LocationProvider lp, int i) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }, 1, -1, -1);
    }

    public void moveLeft() {
        x -= step;
        
        if (x < -tileWidth / 2) {
            if (tile_x > 0) {
                x = tileWidth + x;
                tile_x--;
            } else {
                x = -tileWidth / 2;
            }
        }
    }

    public void moveRight() {
        x += step;
        
        if (x > tileWidth / 2) {
            if (tile_x < (1 << level) - 1) {
                x = -tileWidth + x;
                tile_x++;
            } else {
                x = tileWidth / 2;
            }
        }
    }

    public void moveDown() {
        y += step;        
        
        if (y > tileHeight / 2) {
            if (tile_y < (1 << level) - 1) {
                y = -tileHeight + y;
                tile_y++;
            } else {
                y = tileHeight / 2;
            }
        }
    }

    public void moveUp() {
        y -= step;
        
        if (y < -tileHeight / 2) {
            if (tile_y > 0) {
                y = tileHeight + y;
                tile_y--;
            } else {
                y = -tileHeight / 2;
            }
        }
    }

    public void levelDown() {
        if (level < 18) {
            level++;
            tile_x *= 2;
            tile_y *= 2;
            
            x *= 2;
            y *= 2;
            
            if (x >= 0) {
                ++tile_x;
                x -= tileWidth / 2;
            } else {
                x += tileWidth / 2;
            }
            
            if (y >= 0) {
                ++tile_y;
                y -= tileHeight / 2;
            } else {
                y += tileHeight / 2;
            }
        }
    }

    public void levelUp() {
        if (level > 0) {
            level--;
                    
            int prev_tile_x = tile_x;
            int prev_tile_y = tile_y;
            
            tile_x /= 2;
            tile_y /= 2;
            
            if (prev_tile_x % 2 == 1) {
                x += tileWidth / 2;
            } else {
                x -= tileWidth / 2;
            }
            
            if (prev_tile_y % 2 == 1) {
                y += tileHeight / 2;
            } else {
                y -= tileHeight / 2;
            }
                        
            x /= 2;
            y /= 2;
        }
    }

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, screenWidth, screenHeight);        
        g.drawImage(imageCache.getImage(level, tile_x, tile_y), center_x - x, 
                center_y - y, Graphics.VCENTER | Graphics.HCENTER);
                
        boolean isTileLeft = tile_x == 0;
        boolean isTileTop = tile_y == 0;
        boolean isTileRight = tile_x == (1 << level) - 1;
        boolean isTileBottom = tile_y == (1 << level) - 1;
        boolean isLeftVisible = center_x - x - tileWidth / 2 > 0;
        boolean isRightVisible = center_x - x + tileWidth / 2 < screenWidth;
        boolean isTopVisible = center_y - y - tileHeight / 2 > 0;
        boolean isBottomVisible = center_y - y + tileHeight / 2 < screenHeight;
      
        if (!isTileLeft && isLeftVisible) {
            g.drawImage(imageCache.getImage(level, tile_x - 1, tile_y), 
                    center_x - x - tileWidth,
                    center_y - y, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileTop && isTopVisible) {
            g.drawImage(imageCache.getImage(level, tile_x, tile_y - 1), 
                    center_x - x,
                    center_y - y - tileHeight, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileLeft && !isTileTop && isLeftVisible && isTopVisible) {
            g.drawImage(imageCache.getImage(level, tile_x - 1, tile_y - 1), 
                    center_x - x - tileWidth, 
                    center_y - y - tileHeight, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileRight && isRightVisible) {
            g.drawImage(imageCache.getImage(level, tile_x + 1, tile_y), 
                    center_x - x + tileWidth,
                    center_y - y, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileBottom && isBottomVisible) {
            g.drawImage(imageCache.getImage(level, tile_x, tile_y + 1), 
                    center_x - x,
                    center_y - y + tileHeight, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileRight && !isTileBottom && isRightVisible && isBottomVisible) {
            g.drawImage(imageCache.getImage(level, tile_x + 1, tile_y + 1), 
                    center_x - x + tileWidth, 
                    center_y - y + tileHeight, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileBottom && !isTileLeft && isBottomVisible && isLeftVisible) {
            g.drawImage(imageCache.getImage(level, tile_x - 1, tile_y + 1), 
                    center_x - x - tileWidth,
                    center_y - y + tileHeight, Graphics.HCENTER | Graphics.VCENTER);
        }
        
        if (!isTileRight && !isTileTop && isRightVisible && isTopVisible) {
            g.drawImage(imageCache.getImage(level, tile_x + 1, tile_y - 1), 
                    center_x - x + tileWidth, 
                    center_y - y - tileHeight, Graphics.HCENTER | Graphics.VCENTER);
        }
                
        g.setColor(0, 0, 0);     
        g.drawLine(center_x - 5, center_y, center_x + 5, center_y);
        g.drawLine(center_x, center_y - 5, center_x, center_y + 5);

        g.setColor(0, 0, 0);
        g.drawString(
//                "Lon: "+ longitude + "\n"
//                + "Lat: " + latitude + "\n"
                "level: " + level + "\n" 
                + "tile: " + tile_x + ", " + tile_y + "\n"
                + "x y: " + x + ", " + y,
                0, 0, Graphics.LEFT | Graphics.TOP);
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

        repaint();
    }

    private void locationUpdated2(LocationProvider lp, Location lctn) {
            latitude = lctn.getQualifiedCoordinates().getLatitude();
            longitude = lctn.getQualifiedCoordinates().getLongitude();
        repaint();
    }
    
    private LocationProvider locationProvider;
    private int tile_x, tile_y, level;
    private int center_x, center_y;
    private double latitude, longitude;
    private int x, y;
    private int screenWidth, screenHeight;
    private int tileWidth, tileHeight;
    private int step;
    private ImageCache imageCache;
}
