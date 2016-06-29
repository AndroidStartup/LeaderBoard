package com.example.mahmoud.leaderboard;

/**
 * Defines the data structure for ShoppingListItem objects.
 */
public class ShoppingListItem {
    private String item;
    private String owner;

    /**
     * Required public constructor
     */
    public ShoppingListItem() {
    }

    public ShoppingListItem(String item, String owner) {
        this.item = item;
        this.owner = owner;
    }

    public String getItem() { return item; }

    public String getOwner() {
        return owner;
    }

}
