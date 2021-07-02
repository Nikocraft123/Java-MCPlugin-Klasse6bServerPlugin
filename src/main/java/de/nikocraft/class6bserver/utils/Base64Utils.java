//PACKAGE
package de.nikocraft.class6bserver.utils;


//IMPORTS

//Bukkit
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

//Java
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


//STATIC BASE 64 UTILS CLASS
public class Base64Utils {

    //STATIC METHODS

    //Convert an item stack array to a base 64
    public static ItemStack[] convertItemStackArrayToBase64(String base64) throws IOException {

        try {

            //Get Bukkit object input stream from string
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(new ByteArrayInputStream(Base64Coder.decodeLines(base64)));

            //Define item stack array
            ItemStack[] items = new ItemStack[dataInput.readInt()];

            //Read all items into the item stack array
            for (int i = 0; i < items.length; i++) items[i] = (ItemStack) dataInput.readObject();

            //Close the input stream
            dataInput.close();

            //Return items
            return items;

        } catch (ClassNotFoundException e) {
            //Catch Error
            throw new IOException("Unable to decode class type.", e);
        }

    }

    //Convert a base 64 to an item stack array
    public static String convertBase64ToItemStackArray(ItemStack[] items) throws IllegalStateException {

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);

            // Write the size of the inventory
            dataOutput.writeInt(items.length);

            // Save every element in the list
            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }

            // Serialize that array
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }

}
