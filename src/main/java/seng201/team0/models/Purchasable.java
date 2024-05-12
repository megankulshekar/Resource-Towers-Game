package seng201.team0.models;

/**
 * Interface class representing all purchasable items/towers
 */
public interface Purchasable {
    /**
     * Gets the buying price of item/tower
     * @return Buying price
     */
    int getBuyingPrice();

    /**
     * Gets the selling price of item/tower
     * @return Selling price
     */
    int getSellingPrice();

    /**
     * Gets the description of item/tower
     * @return Description
     */
    String getDescription();
}