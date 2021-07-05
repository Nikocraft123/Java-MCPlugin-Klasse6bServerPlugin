//PACKAGE
package de.nikocraft.class6bserver.ui.inventories;


//IMPORTS


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

//ABSTRACT INVENTORY BUILDER CLASS
public abstract class InventoryBuilder {

    //VARIABLES

    //The inventory object
    private Inventory inventory;

    //The title of the inventory
    private String title;


    //CONSTRUCTORS
    public InventoryBuilder(int size, String title, String base64) {

        //Set the title
        this.title = title;

        //Create the inventory
        inventory = Bukkit.createInventory(null, size, title);

    }


    //GETTERS

    //The inventory object
    public Inventory getInventory() { return inventory; }

    //The title of the inventory
    public String getTitle() {
        return title;
    }
}
