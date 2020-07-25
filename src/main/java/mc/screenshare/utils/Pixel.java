package mc.screenshare.utils;

import org.bukkit.Material;

import java.awt.*;

public class Pixel {
    public int x;
    public int y;
    public Material material = Material.BLACK_WOOL;
    public Color color;

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Pixel(int x, int z, Material material, Color color) {
        this.x = x;
        this.y = z;
        this.material = material;
        this.color = color;
    }

    public void setMaterialFromColor() {
        this.material = ColorUtil.fromRGB(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return y;
    }

    public Material getMaterial() {
        return material;
    }

    public Color getColor() {
        return color;
    }

}
