package net.ubudog.openinvaders.entity;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Explosion {

        int x;
        int y;

        Image img;

        public static boolean visible;

        public Explosion() {
                ImageIcon i = new ImageIcon("res/drawable/explosion.gif");
                img = i.getImage();

                visible = true;
        }

        public Rectangle getBounds() {
                return new Rectangle(x, y, 32, 32);
        }

        public int getX() {
                return x;
        }

        public int getY() {
                return y;
        }

        public Boolean getVisible() {
                return visible;
        }

        public Image getExplosion() {
                return img;
        }
}