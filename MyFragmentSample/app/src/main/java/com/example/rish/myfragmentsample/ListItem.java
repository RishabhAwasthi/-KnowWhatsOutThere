package com.example.rish.myfragmentsample;

public class ListItem {

    public String itemName;
    public String itemQuant;
    public int itemImage;

    public ListItem(){}

    public ListItem(String name, String quant, int image){
        this.itemImage=image;
        this.itemName=name;
        this.itemQuant=quant;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuant() {
        return itemQuant;
    }

    public void setItemQuant(String itemQuant) {
        this.itemQuant = itemQuant;
    }
}
