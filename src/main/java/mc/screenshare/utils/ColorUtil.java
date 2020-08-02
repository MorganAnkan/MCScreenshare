package mc.screenshare.utils;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ColorUtil {
    private static Map<Material, ColorSet<Integer, Integer, Integer>> colorMap = new HashMap<>();

    static {//currently average value (color) //disabled some tile blocks cuase of errors replacing them (i think all are tiles but it doesnt really matter)
        colorMap.put(Material.ACACIA_LOG, new ColorSet<>(151, 89, 55));
        colorMap.put(Material.ACACIA_PLANKS, new ColorSet<>(168, 90, 50));
        colorMap.put(Material.ANDESITE, new ColorSet<>(136, 136, 137));
        //colorMap.put(Material.BARREL, new ColorSet<>(135, 101, 58));
        //colorMap.put(Material.BEACON, new ColorSet<>(118, 221, 215));
        colorMap.put(Material.BEDROCK, new ColorSet<>(85, 85, 85));
        //colorMap.put(Material.BEE_NEST, new ColorSet<>(202, 160, 75));
        colorMap.put(Material.BIRCH_LOG, new ColorSet<>(193, 179, 135));
        colorMap.put(Material.BIRCH_PLANKS, new ColorSet<>(192, 175, 121));
        colorMap.put(Material.BLACK_CONCRETE, new ColorSet<>(8, 10, 15));
        colorMap.put(Material.BLACK_CONCRETE_POWDER, new ColorSet<>(25, 27, 32));
        colorMap.put(Material.BLACK_GLAZED_TERRACOTTA, new ColorSet<>(68, 30, 32));
        //colorMap.put(Material.BLACK_SHULKER_BOX, new ColorSet<>(25, 25, 30));
        colorMap.put(Material.BLACK_TERRACOTTA, new ColorSet<>(37, 23, 16));
        colorMap.put(Material.BLACK_WOOL, new ColorSet<>(21, 21, 26));
        //colorMap.put(Material.BLAST_FURNACE, new ColorSet<>(81, 80, 81));
        colorMap.put(Material.BLUE_CONCRETE_POWDER, new ColorSet<>(70, 73, 167));
        colorMap.put(Material.BLUE_CONCRETE, new ColorSet<>(45, 47, 143));
        colorMap.put(Material.BLUE_GLAZED_TERRACOTTA, new ColorSet<>(47, 65, 139));
        colorMap.put(Material.BLUE_ICE, new ColorSet<>(116, 168, 253));
        //colorMap.put(Material.BLUE_SHULKER_BOX, new ColorSet<>(44, 46, 140));
        colorMap.put(Material.BLUE_TERRACOTTA, new ColorSet<>(74, 60, 91));
        colorMap.put(Material.BLUE_WOOL, new ColorSet<>(53, 57, 157));
        colorMap.put(Material.BONE_BLOCK, new ColorSet<>(210, 206, 179));
        colorMap.put(Material.BOOKSHELF, new ColorSet<>(117, 95, 60));
        colorMap.put(Material.BRICKS, new ColorSet<>(151, 98, 83));
        colorMap.put(Material.BROWN_CONCRETE, new ColorSet<>(96, 60, 32));
        colorMap.put(Material.BROWN_CONCRETE_POWDER, new ColorSet<>(126, 85, 54));
        colorMap.put(Material.BROWN_GLAZED_TERRACOTTA, new ColorSet<>(120, 106, 86));
        colorMap.put(Material.BROWN_MUSHROOM_BLOCK, new ColorSet<>(149, 112, 81));
        //colorMap.put(Material.BROWN_SHULKER_BOX, new ColorSet<>(106, 66, 36));
        colorMap.put(Material.BROWN_TERRACOTTA, new ColorSet<>(77, 51, 36));
        colorMap.put(Material.BROWN_WOOL, new ColorSet<>(114, 72, 41));
        //colorMap.put(Material.CARTOGRAPHY_TABLE, new ColorSet<>(104, 87, 67));
        colorMap.put(Material.CHISELED_QUARTZ_BLOCK, new ColorSet<>(232, 227, 217));
        colorMap.put(Material.CHISELED_RED_SANDSTONE, new ColorSet<>(183, 97, 28));
        colorMap.put(Material.CHISELED_SANDSTONE, new ColorSet<>(216, 203, 155));
        colorMap.put(Material.CHISELED_STONE_BRICKS, new ColorSet<>(120, 119, 120));
        colorMap.put(Material.COAL_BLOCK, new ColorSet<>(16, 16, 16));
        colorMap.put(Material.COAL_ORE, new ColorSet<>(116, 116, 116));
        colorMap.put(Material.COARSE_DIRT, new ColorSet<>(119, 86, 59));
        colorMap.put(Material.COBBLESTONE, new ColorSet<>(128, 127, 128));
        //colorMap.put(Material.COBWEB, new ColorSet<>(227, 232, 234));
        //colorMap.put(Material.CONDUIT, new ColorSet<>(160, 140, 113));
        colorMap.put(Material.CRACKED_STONE_BRICKS, new ColorSet<>(118, 118, 118));
        //colorMap.put(Material.CRAFTING_TABLE, new ColorSet<>(120, 73, 42));
        colorMap.put(Material.CUT_RED_SANDSTONE, new ColorSet<>(189, 102, 32));
        colorMap.put(Material.CYAN_CONCRETE, new ColorSet<>(21, 119, 136));
        colorMap.put(Material.CUT_SANDSTONE, new ColorSet<>(218, 206, 160));
        colorMap.put(Material.CYAN_CONCRETE_POWDER, new ColorSet<>(37, 148, 157));
        colorMap.put(Material.CYAN_GLAZED_TERRACOTTA, new ColorSet<>(52, 119, 125));
        //colorMap.put(Material.CYAN_SHULKER_BOX, new ColorSet<>(20, 121, 135));
        colorMap.put(Material.CYAN_TERRACOTTA, new ColorSet<>(87, 91, 91));
        colorMap.put(Material.CYAN_WOOL, new ColorSet<>(21, 138, 145));
        colorMap.put(Material.DARK_OAK_LOG, new ColorSet<>(65, 43, 21));
        colorMap.put(Material.DARK_PRISMARINE, new ColorSet<>(52, 92, 76));
        colorMap.put(Material.DIAMOND_BLOCK, new ColorSet<>(98, 237, 228));
        colorMap.put(Material.DIAMOND_ORE, new ColorSet<>(125, 143, 141));
        colorMap.put(Material.DIORITE, new ColorSet<>(189, 188, 189));
        colorMap.put(Material.DIRT, new ColorSet<>(134, 96, 67));
        //colorMap.put(Material.DRAGON_EGG, new ColorSet<>(13, 9, 16));
        colorMap.put(Material.EMERALD_BLOCK, new ColorSet<>(42, 203, 88));
        colorMap.put(Material.EMERALD_ORE, new ColorSet<>(117, 137, 124));
        //colorMap.put(Material.ENCHANTING_TABLE, new ColorSet<>(129, 75, 85));
        //colorMap.put(Material.END_PORTAL_FRAME, new ColorSet<>(91, 121, 97));
        colorMap.put(Material.END_STONE, new ColorSet<>(220, 223, 158));
        colorMap.put(Material.END_STONE_BRICKS, new ColorSet<>(218, 224, 162));
        //colorMap.put(Material.FLETCHING_TABLE, new ColorSet<>(197, 180, 133));
        //colorMap.put(Material.FURNACE, new ColorSet<>(110, 110, 110));
        colorMap.put(Material.GLOWSTONE, new ColorSet<>(172, 131, 84));
        colorMap.put(Material.GOLD_BLOCK, new ColorSet<>(246, 208, 62));
        colorMap.put(Material.GOLD_ORE, new ColorSet<>(144, 140, 125));
        colorMap.put(Material.GRANITE, new ColorSet<>(149, 103, 86));
        colorMap.put(Material.GRASS_PATH, new ColorSet<>(148, 122, 65));
        colorMap.put(Material.GRAVEL, new ColorSet<>(132, 127, 127));
        colorMap.put(Material.GRAY_CONCRETE, new ColorSet<>(55, 58, 62));
        colorMap.put(Material.GRAY_CONCRETE_POWDER, new ColorSet<>(77, 81, 85));
        colorMap.put(Material.GRAY_GLAZED_TERRACOTTA, new ColorSet<>(83, 90, 94));
        colorMap.put(Material.GRAY_TERRACOTTA, new ColorSet<>(58, 42, 36));
        //colorMap.put(Material.GRAY_SHULKER_BOX, new ColorSet<>(55, 59, 62));
        colorMap.put(Material.GRAY_WOOL, new ColorSet<>(63, 68, 72));
        colorMap.put(Material.GREEN_CONCRETE, new ColorSet<>(73, 91, 36));
        colorMap.put(Material.GREEN_GLAZED_TERRACOTTA, new ColorSet<>(117, 142, 67));
        colorMap.put(Material.GREEN_CONCRETE_POWDER, new ColorSet<>(97, 119, 45));
        //colorMap.put(Material.GREEN_SHULKER_BOX, new ColorSet<>(79, 101, 32));
        colorMap.put(Material.GREEN_TERRACOTTA, new ColorSet<>(76, 83, 42));
        colorMap.put(Material.GREEN_WOOL, new ColorSet<>(85, 110, 28));
        colorMap.put(Material.HAY_BLOCK, new ColorSet<>(166, 139, 12));
        colorMap.put(Material.HONEYCOMB_BLOCK, new ColorSet<>(229, 148, 30));
        colorMap.put(Material.HONEY_BLOCK, new ColorSet<>(251, 185, 53));
        colorMap.put(Material.ICE, new ColorSet<>(146, 184, 254));
        colorMap.put(Material.IRON_BLOCK, new ColorSet<>(220, 220, 220));
        colorMap.put(Material.IRON_ORE, new ColorSet<>(136, 131, 127));
        colorMap.put(Material.JACK_O_LANTERN, new ColorSet<>(215, 152, 53));
        //colorMap.put(Material.JIGSAW, new ColorSet<>(80, 70, 81));
        //colorMap.put(Material.JUKEBOX, new ColorSet<>(94, 64, 47));
        colorMap.put(Material.JUNGLE_LOG, new ColorSet<>(150, 109, 71));
        colorMap.put(Material.JUNGLE_PLANKS, new ColorSet<>(160, 115, 81));
        colorMap.put(Material.LAPIS_BLOCK, new ColorSet<>(31, 67, 140));
        colorMap.put(Material.LECTERN, new ColorSet<>(174, 138, 83));
        colorMap.put(Material.LIGHT_BLUE_CONCRETE, new ColorSet<>(36, 137, 199));
        colorMap.put(Material.LIGHT_BLUE_CONCRETE_POWDER, new ColorSet<>(74, 181, 213));
        colorMap.put(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, new ColorSet<>(95, 165, 209));
        //colorMap.put(Material.LIGHT_BLUE_SHULKER_BOX, new ColorSet<>(49, 164, 212));
        colorMap.put(Material.LAPIS_ORE, new ColorSet<>(99, 111, 133));
        colorMap.put(Material.LIGHT_BLUE_TERRACOTTA, new ColorSet<>(113, 109, 138));
        colorMap.put(Material.LIGHT_BLUE_WOOL, new ColorSet<>(58, 175, 217));
        colorMap.put(Material.LIGHT_GRAY_CONCRETE, new ColorSet<>(125, 125, 115));
        colorMap.put(Material.LIGHT_GRAY_CONCRETE_POWDER, new ColorSet<>(155, 155, 148));
        colorMap.put(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, new ColorSet<>(144, 166, 168));
        //colorMap.put(Material.LIGHT_GRAY_SHULKER_BOX, new ColorSet<>(124, 124, 115));
        colorMap.put(Material.LIGHT_GRAY_TERRACOTTA, new ColorSet<>(135, 107, 98));
        colorMap.put(Material.LIGHT_GRAY_WOOL, new ColorSet<>(142, 142, 135));
        colorMap.put(Material.LIME_CONCRETE, new ColorSet<>(94, 169, 25));
        colorMap.put(Material.LIME_CONCRETE_POWDER, new ColorSet<>(125, 189, 42));
        colorMap.put(Material.LIME_GLAZED_TERRACOTTA, new ColorSet<>(163, 198, 55));
        //colorMap.put(Material.LIME_SHULKER_BOX, new ColorSet<>(100, 173, 23));
        colorMap.put(Material.LIME_TERRACOTTA, new ColorSet<>(104, 118, 53));
        colorMap.put(Material.LIME_WOOL, new ColorSet<>(112, 185, 26));
        //colorMap.put(Material.LOOM, new ColorSet<>(142, 119, 92));
        colorMap.put(Material.MAGENTA_CONCRETE, new ColorSet<>(169, 48, 159));
        colorMap.put(Material.MAGENTA_CONCRETE_POWDER, new ColorSet<>(193, 84, 185));
        colorMap.put(Material.MAGENTA_GLAZED_TERRACOTTA, new ColorSet<>(208, 100, 192));
        //colorMap.put(Material.MAGENTA_SHULKER_BOX, new ColorSet<>(174, 54, 164));
        colorMap.put(Material.MAGENTA_TERRACOTTA, new ColorSet<>(150, 88, 109));
        colorMap.put(Material.MAGENTA_WOOL, new ColorSet<>(190, 69, 180));
        colorMap.put(Material.MELON, new ColorSet<>(111, 145, 31));
        colorMap.put(Material.MOSSY_COBBLESTONE, new ColorSet<>(110, 119, 95));
        colorMap.put(Material.MOSSY_STONE_BRICKS, new ColorSet<>(115, 121, 105));
        colorMap.put(Material.MUSHROOM_STEM, new ColorSet<>(203, 197, 186));
        colorMap.put(Material.MYCELIUM, new ColorSet<>(111, 99, 101));
        colorMap.put(Material.NETHERRACK, new ColorSet<>(98, 38, 38));
        colorMap.put(Material.NETHER_BRICKS, new ColorSet<>(44, 22, 26));
        colorMap.put(Material.NETHER_QUARTZ_ORE, new ColorSet<>(118, 66, 62));
        colorMap.put(Material.NETHER_WART_BLOCK, new ColorSet<>(115, 3, 3));
        //colorMap.put(Material.NOTE_BLOCK, new ColorSet<>(89, 59, 41));
        colorMap.put(Material.OAK_LOG, new ColorSet<>(151, 122, 73));
        //colorMap.put(Material.OBSERVER, new ColorSet<>(98, 98, 98));
        colorMap.put(Material.OAK_PLANKS, new ColorSet<>(162, 131, 79));
        colorMap.put(Material.OBSIDIAN, new ColorSet<>(15, 11, 25));
        colorMap.put(Material.ORANGE_CONCRETE, new ColorSet<>(224, 97, 1));
        colorMap.put(Material.ORANGE_GLAZED_TERRACOTTA, new ColorSet<>(155, 147, 92));
        colorMap.put(Material.ORANGE_CONCRETE_POWDER, new ColorSet<>(227, 132, 32));
        //colorMap.put(Material.ORANGE_SHULKER_BOX, new ColorSet<>(234, 106, 9));
        colorMap.put(Material.ORANGE_TERRACOTTA, new ColorSet<>(162, 84, 38));
        colorMap.put(Material.ORANGE_WOOL, new ColorSet<>(241, 118, 20));
        colorMap.put(Material.PACKED_ICE, new ColorSet<>(142, 180, 250));
        colorMap.put(Material.PINK_CONCRETE, new ColorSet<>(214, 101, 143));
        colorMap.put(Material.PINK_CONCRETE_POWDER, new ColorSet<>(229, 153, 181));
        colorMap.put(Material.PINK_GLAZED_TERRACOTTA, new ColorSet<>(235, 155, 182));
        //colorMap.put(Material.PINK_SHULKER_BOX, new ColorSet<>(230, 122, 158));
        colorMap.put(Material.PINK_TERRACOTTA, new ColorSet<>(162, 78, 79));
        colorMap.put(Material.PINK_WOOL, new ColorSet<>(238, 141, 172));
        colorMap.put(Material.PODZOL, new ColorSet<>(92, 63, 24));
        colorMap.put(Material.POLISHED_ANDESITE, new ColorSet<>(132, 135, 134));
        colorMap.put(Material.POLISHED_DIORITE, new ColorSet<>(193, 193, 195));
        colorMap.put(Material.POLISHED_GRANITE, new ColorSet<>(154, 107, 89));
        colorMap.put(Material.PRISMARINE_BRICKS, new ColorSet<>(99, 172, 158));
        colorMap.put(Material.PUMPKIN, new ColorSet<>(150, 84, 17));
        colorMap.put(Material.PURPLE_CONCRETE, new ColorSet<>(100, 32, 156));
        colorMap.put(Material.PURPLE_CONCRETE_POWDER, new ColorSet<>(132, 56, 178));
        colorMap.put(Material.PURPLE_GLAZED_TERRACOTTA, new ColorSet<>(110, 48, 152));
        //colorMap.put(Material.PURPLE_SHULKER_BOX, new ColorSet<>(103, 32, 156));
        colorMap.put(Material.PURPLE_TERRACOTTA, new ColorSet<>(118, 70, 86));
        colorMap.put(Material.PURPLE_WOOL, new ColorSet<>(122, 42, 173));
        colorMap.put(Material.PURPUR_BLOCK, new ColorSet<>(170, 126, 170));
        colorMap.put(Material.PURPUR_PILLAR, new ColorSet<>(171, 128, 171));
        colorMap.put(Material.QUARTZ_BLOCK, new ColorSet<>(236, 230, 223));
        colorMap.put(Material.QUARTZ_PILLAR, new ColorSet<>(235, 230, 223));
        colorMap.put(Material.REDSTONE_BLOCK, new ColorSet<>(176, 25, 5));
        colorMap.put(Material.REDSTONE_LAMP, new ColorSet<>(95, 55, 30));
        colorMap.put(Material.REDSTONE_ORE, new ColorSet<>(133, 108, 108));
        colorMap.put(Material.RED_CONCRETE, new ColorSet<>(142, 33, 33));
        colorMap.put(Material.RED_CONCRETE_POWDER, new ColorSet<>(168, 54, 51));
        colorMap.put(Material.RED_GLAZED_TERRACOTTA, new ColorSet<>(182, 60, 53));
        colorMap.put(Material.RED_MUSHROOM_BLOCK, new ColorSet<>(200, 47, 45));
        colorMap.put(Material.RED_NETHER_BRICKS, new ColorSet<>(70, 7, 9));
        colorMap.put(Material.RED_SAND, new ColorSet<>(191, 103, 33));
        colorMap.put(Material.RED_SANDSTONE, new ColorSet<>(181, 98, 31));
        //colorMap.put(Material.RED_SHULKER_BOX, new ColorSet<>(140, 31, 30));
        colorMap.put(Material.RED_TERRACOTTA, new ColorSet<>(143, 61, 47));
        colorMap.put(Material.RED_WOOL, new ColorSet<>(161, 39, 35));
        colorMap.put(Material.SAND, new ColorSet<>(219, 207, 163));
        colorMap.put(Material.SANDSTONE, new ColorSet<>(224, 214, 170));
        colorMap.put(Material.SCAFFOLDING, new ColorSet<>(174, 134, 80));
        //colorMap.put(Material.SHULKER_BOX, new ColorSet<>(139, 97, 139));
        colorMap.put(Material.SLIME_BLOCK, new ColorSet<>(112, 192, 92));
        //colorMap.put(Material.SMITHING_TABLE, new ColorSet<>(57, 59, 71));
        colorMap.put(Material.SMOOTH_STONE, new ColorSet<>(159, 159, 159));
        //colorMap.put(Material.SMOKER, new ColorSet<>(85, 83, 81));
        colorMap.put(Material.SNOW_BLOCK, new ColorSet<>(249, 254, 254));
        colorMap.put(Material.SOUL_SAND, new ColorSet<>(81, 62, 51));
        colorMap.put(Material.SPONGE, new ColorSet<>(196, 192, 75));
        colorMap.put(Material.SPRUCE_LOG, new ColorSet<>(109, 80, 47));
        colorMap.put(Material.STONE, new ColorSet<>(126, 126, 126));
        colorMap.put(Material.SPRUCE_PLANKS, new ColorSet<>(115, 85, 49));
        //colorMap.put(Material.STONECUTTER, new ColorSet<>(123, 119, 111));
        colorMap.put(Material.STONE_BRICKS, new ColorSet<>(122, 122, 122));
        colorMap.put(Material.STRIPPED_ACACIA_LOG, new ColorSet<>(166, 91, 52));
        colorMap.put(Material.STRIPPED_BIRCH_LOG, new ColorSet<>(191, 172, 116));
        colorMap.put(Material.STRIPPED_JUNGLE_LOG, new ColorSet<>(166, 123, 82));
        colorMap.put(Material.STRIPPED_DARK_OAK_LOG, new ColorSet<>(66, 44, 23));
        colorMap.put(Material.STRIPPED_OAK_LOG, new ColorSet<>(160, 130, 77));
        colorMap.put(Material.STRIPPED_SPRUCE_LOG, new ColorSet<>(106, 80, 47));
        colorMap.put(Material.TERRACOTTA, new ColorSet<>(152, 94, 68));
        //colorMap.put(Material.TNT, new ColorSet<>(143, 62, 54));// the whole shit was exploding on redish screens
        colorMap.put(Material.WET_SPONGE, new ColorSet<>(171, 181, 70));
        colorMap.put(Material.WHITE_CONCRETE, new ColorSet<>(207, 213, 214));
        colorMap.put(Material.WHITE_CONCRETE_POWDER, new ColorSet<>(226, 227, 228));
        colorMap.put(Material.WHITE_GLAZED_TERRACOTTA, new ColorSet<>(189, 212, 203));
        //colorMap.put(Material.WHITE_SHULKER_BOX, new ColorSet<>(216, 221, 221));
        colorMap.put(Material.WHITE_TERRACOTTA, new ColorSet<>(210, 178, 161));
        colorMap.put(Material.WHITE_WOOL, new ColorSet<>(234, 236, 237));
        colorMap.put(Material.YELLOW_CONCRETE, new ColorSet<>(241, 175, 21));
        colorMap.put(Material.YELLOW_CONCRETE_POWDER, new ColorSet<>(233, 199, 55));
        colorMap.put(Material.YELLOW_GLAZED_TERRACOTTA, new ColorSet<>(234, 192, 89));
        //colorMap.put(Material.YELLOW_SHULKER_BOX, new ColorSet<>(248, 189, 29));
        colorMap.put(Material.YELLOW_TERRACOTTA, new ColorSet<>(186, 133, 35));
        colorMap.put(Material.YELLOW_WOOL, new ColorSet<>(249, 198, 40));
    }

    private static class ColorSet<R, G, B> {
        R red = null;
        G green = null;
        B blue = null;

        ColorSet(R red, G green, B blue) {
            this.red = red;
            this.green = green; //morgan is a ilegal carb
            this.blue = blue;
        }

        public R getRed() {
            return red;
        }

        public G getGreen() {
            return green;
        }

        public B getBlue() {
            return blue;
        }

    }

    public static Material fromRGB(int r, int g, int b) {
        TreeMap<Integer, Material> closest = new TreeMap<>();
        colorMap.forEach((color, set) -> {
            int red = Math.abs(r - set.getRed());
            int green = Math.abs(g - set.getGreen());
            int blue = Math.abs(b - set.getBlue());
            closest.put(red + green + blue, color);
        });
        return closest.firstEntry().getValue();
    }
}
