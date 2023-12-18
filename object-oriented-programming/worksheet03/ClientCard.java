
/**
 * The ClientCard class represents a customer card for a shopping program.
 *
 * This card accumulates bonus points as purchases are made and maintains information
 * about the card holder's name, an alphanumeric code, total cash spent, and bonus points.
 * The class follows specific rules for earning bonus points and allows various operations
 * such as deducting points, modifying the card holder, changing the premium rate, transferring
 * points, and recording new purchases to update the card data.
 *
 * The rules for earning bonus points:
 * 1. For each purchase made in Euros, the customer receives a bonus number of points (integer)
 *    which is calculated by rounding down to 10% of the purchase value.
 * 2. Whenever a multiple of 50 points is reached, the customer accumulates an additional
 *    5 points for every 50, which are added to those already on the card.
 *
 * @author (PIRATA)
 * @version (2012.06.04)
 */
public class ClientCard
{
    // Class variables
    private static float PRIZE_RATE = 0.1F; // The premium rate for the bonus points.
    // Instance variables
    private String cardHolderName;      // The card holder's name.
    private String cardCode;            // The alphanumeric card's code. 
    private float totalCashSpent;       // The total ammount of cash spent on purchases.
    private int bonusPoints;            // The unused bonus points accumulated.

    /**
     * Default constructor for objects of class ClientCard.
     * Initializes the card holder name as "UNKNOWN", the card's code as "UNDEFINED" and
     * both the total cash spent and the bonus points earned as 0.
     */
    public ClientCard()
    {
        this.cardHolderName = "UNKNOWN";
        this.cardCode = "UNDEFINED";
        this.totalCashSpent = 0;
        this.bonusPoints = 0;
    }
    
    /**
     * Constructs a new ClientCard by copying the attributes of the given ClientCard.
     * 
     * @param cc    The ClientCard to copy the data from.
     */
    public ClientCard(ClientCard cc)
    {
        this.cardHolderName = cc.getHolderName();
        this.cardCode = cc.getCardCode();
        this.totalCashSpent = cc.getTotalSpent();
        this.bonusPoints = cc.getPoints();
    }
    
    /**
     * Constructor for creating a new ClientCard with specified attributes.
     *
     * @param nm    The name of the card holder.
     * @param cd    The alphanumeric code of the card.
     * @param cs    The total cash spent on the card.
     * @param p     The bonus points on the card.
     */
    public ClientCard(String nm, String cd, float cs, int p)
    {
        this.setHolderName(nm); // This way it would check the name's validity if it was set up.
        this.setCardCode(cd);   // This way it would check the code's validity if it was set up.
        this.setTotalSpent(cs);
        this.setPoints(p);
    }
    
    // Getters & Setters
    /**
     * Get the name of the card holder.
     *
     * @return  The name of the card holder.
     */
    public String getHolderName() { return this.cardHolderName; }
    
    /**
     * Get the alphanumeric code of the card.
     *
     * @return  The alphanumeric card code.
     */
    public String getCardCode() { return this.cardCode; }
    
    /**
     * Get the total cash spent on the card.
     *
     * @return  The total cash spent.
     */
    public float getTotalSpent() { return this.totalCashSpent; }
    
    /**
     * Get the bonus points on the card.
     *
     * @return  The bonus points.
     */
    public int getPoints() { return this.bonusPoints; }
    
    /**
     * Get the current premium rate for bonus points.
     *
     * @return  The premium rate as a float value.
     */
    public static float getBonusRate() { return ClientCard.PRIZE_RATE; }
    
    /**
     * Set the name of the card holder.
     *
     * @param nm    The name of the card holder.
     */
    private void setHolderName(String nm) { this.cardHolderName = nm; }  // Add verification that name is "real" on a RL scenario!
    
    /**
     * Swet the alphanumeric code of the card.
     *
     * @param code  The alphanumeric card code.
     */
    private void setCardCode(String code) { this.cardCode = code; }      // Code should also have some sort of checking!
    
    /**
     * Set the total cash spent on the card.
     *
     * @param v The total cash spent.
     */
    private void setTotalSpent(float v) { this.totalCashSpent = (v < 0) ? 0 : v; }   // No values under 0.
    
    /**
     * Set the bonus points on the card.
     *
     * @param pts   The bonus points.
     */
    private void setPoints(int pts) { this.bonusPoints = (pts < 0) ? 0 : pts; }      // No values under 0.
    
    /**
     * Modify the premium rate (e.g., from 10% to 11%).
     *
     * @param rt    The new premium rate to set (in the range [0, 1]).
     */
    public static void modifyBonusRate(float rt)
    {
        if(rt >= 0 && rt <= 1) {
            ClientCard.PRIZE_RATE = rt;
        }
    }
    
    // Instance methods
    /**
     * Deduct a specified number of points from the card due to prize withdrawal.
     *
     * @param p The number of points to deduct.
     * @return  true if the deduction is successful, false if not enough points are available.
     */
    public boolean deductPoints(int p)
    {   // Only discounts if there's enough points
        boolean flag = false;
        if((p > 0) && (this.getPoints() >= p)) {
            this.bonusPoints -= p;
            flag = true;
        }
        return flag;
    }
    
    /**
     * Change the owner's name of the card.
     *
     * @param nm    The new name to set as the card holder's name.
     */
    public void changeCardOwner(String nm)
    {
        this.setHolderName(nm);
    }
    
    /**
     * Transfer bonus points from another ClientCard to this card.
     *
     * @param cc    The ClientCard from which to transfer points.
     */
    public void transferPoints(ClientCard cc)
    {
        this.setPoints(this.getPoints() + cc.getPoints());
        cc.setPoints(0);
    }
    
    /**
     * Record a new purchase on the card, calculate bonus points, and update card data.
     *
     * @param value The value of the purchase in Euros.
     */
    public void addPurchase(float value)
    {
        float left2Fifty;
        int pointsCumul = 0;
        if(value > 0) {
            left2Fifty = this.getTotalSpent() - (((float) Math.floor(this.getTotalSpent() / 50)) * 50);
            left2Fifty += value;
            pointsCumul = (int) Math.floor(value * ClientCard.getBonusRate());
            pointsCumul += ((int) Math.floor(left2Fifty / 50)) * 5;
            this.setPoints(this.getPoints() + pointsCumul);
            this.setTotalSpent(this.getTotalSpent() + value);
        }
    }
    
    // Complementary methods
    /**
     * Check if two ClientCard objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the client cards are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        ClientCard cc = (ClientCard) o;
        return ((this.getHolderName().equals(cc.getHolderName())) &&
                (this.getCardCode().equals(cc.getCardCode())) &&
                (this.getTotalSpent() == cc.getTotalSpent()) &&
                (this.getPoints() == cc.getPoints()));
    }
    
    /**
     * Returns a string representation of the ClientCard in the format
     * "ClientCard {HolderName:'Name', Code:'code', Points:00, TotalSpent:00.00}".
     * 
     * @return  A string representation of the ClientCard.
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder("ClientCard {");
        s.append("HolderName:\'" + this.getHolderName() + "\'");
        s.append(", Code:\'" + this.getCardCode() + "\'");
        s.append(", Points:" + this.getPoints());
        s.append(", TotalSpent:" + this.getTotalSpent() + "}");
        return s.toString();
    }
    
    /**
     * Creates a new ClientCard object that is a deep copy of this ClientCard.
     * 
     * @return  A new ClientCard object with the same attributes as the original.
     */
    public ClientCard clone() { return new ClientCard(this); }
}
