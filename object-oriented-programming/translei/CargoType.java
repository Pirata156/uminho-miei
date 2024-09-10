/**
 * Enum representing different types of cargo.
 * Each constant has specific characteristics related to transportation.
 *
 * - PERISHABLE: Requires refrigeration.
 * - TOXIC: Cannot be transported in the same vehicles as perishable goods, even if not at the same time.
 * - UNDIFFERENTIATED: Doesn't require refrigeration but may be transported in refrigerated vehicles if convenient.
 *
 * @author PIRATA
 * @version v_002
 */
public enum CargoType
{
    PERISHABLE, TOXIC, UNDIFFERENTIATED;
    
    /**
     * Returns a string representation of the cargo type.
     *
     * @return A string describing the cargo type.
     */
    @Override
    public String toString()
    {
        switch(this) {
            case PERISHABLE:
                return "perishable";
            case TOXIC:
                return "toxic";
            case UNDIFFERENTIATED:
                return "undifferentiated";
            default:
                return "";
        }
    }
}
