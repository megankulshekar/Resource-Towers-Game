package seng201.team0;

/**
 * Abstract class representing all statistics for purchasable items and towers
 */
public interface Purchasable {
    /**
     * Gets the buying price of item/tower
     * @return Buying price in dollars
     */
    public int getBuyingPrice();

    /**
     * Gets the selling price of item/tower
     * @return Selling price in dollars
     */
    public int getSellingPrice();

    /**
     * Gets the description of item/tower
     * @return Description
     */
    public String getDescription();
}
