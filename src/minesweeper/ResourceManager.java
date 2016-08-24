package minesweeper;

import javafx.scene.image.Image;

import java.util.HashMap;

/**
 * Created by kasra on 8/23/2016.
 */
public class ResourceManager {
    private static HashMap<String, Image> resourceMap = new HashMap<>();
    
    public static Image getImage(String filename) {
        if (resourceMap.containsKey(filename)) {
            return resourceMap.get(filename);
        } else {
            Image image = new Image(filename);
            resourceMap.put(filename, image);
            return image;
        }
    }
    
    
}
