package seng201.team0.models;

/**
 * Interface class representing all purchasable items/towers
 */
public interface Purchasable {
    /**
     * Gets the buying price of item/tower
     * @return Buying price
     */
    public int getBuyingPrice();

    /**
     * Gets the selling price of item/tower
     * @return Selling price
     */
    public int getSellingPrice();

    public String setDescription(String typeTower, int resourceAmount, int reloadSpeed);

    /**
     * Gets the description of item/tower
     * @return Description
     */
    public String getDescription();
}