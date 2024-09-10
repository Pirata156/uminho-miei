import java.util.GregorianCalendar;
import java.util.HashMap;   // Class
import java.util.TreeSet;   // Class
import java.util.Map;       // Interface
import java.util.Set;       // Interface

/**
 * The Bank class associates a Term Account with each account code
 * and provides methods for various banking operations.
 *
 * @author PIRATA
 * @version 2012.04.30
 */
public class Bank
{
    // Instance variables
    private String bankName;                        // The name of the bank
    private HashMap<String,TermAccount> accounts;   // Relation <AccountCode,TermAccount>

    // Constructors
    /**
     * Default constructor for the Bank class.
     * Initializes the accounts with an empty HashMap.
     */
    public Bank()
    {
        this.bankName = "";
        this.accounts = new HashMap<String,TermAccount>();
    }

    /**
     * Copy constructor for the Bank class.
     * Creates a new Bank object with the same attribute as the given Bank object.
     *
     * @param b The Bank object to be copied.
     */
    public Bank(Bank b)
    {
        this.bankName = b.getName();
        this.accounts = b.getAccounts();    // Already copies!
    }

    /**
     * Parameterized constructor for the Bank class.
     * Initializes the accounts with an empty HashMap and sets the name of the bank as given.
     *
     * @param name  The name of the bank.
     */
    public Bank(String name)
    {
        this.setName(name);
        this.accounts = new HashMap<>();
    }

    // Getters & Setters
    /**
     * Get the of the bank.
     *
     * @return  The name of the bank.
     */
    public String getName() { return this.bankName; }
    
    /**
     * Set the of the bank.
     *
     * @param name  The new name of the bank.
     */
    public void setName(String name) { this.bankName = name; }
    
    /**
     * Get the map of account codes to TermAccount objects.
     *
     * @return  The map of account codes to TermAccount objects.
     */
    public HashMap<String, TermAccount> getAccounts()
    {
        HashMap<String,TermAccount> res = new HashMap<>();
        for(TermAccount ta : this.accounts.values()) {
            res.put(ta.getCode(), ta.clone());
        }
        return res;
    }

    /**
     * Set the map of account codes to TermAccount objects.
     *
     * @param accounts The new map of account codes to TermAccount objects.
     */
    private void setAccounts(HashMap<String,TermAccount> accs)
    {
        this.accounts = new HashMap<>();
        for(TermAccount ta : accs.values()) {
            this.accounts.put(ta.getCode(), ta.clone());
        }
    }

    // Instance methods
    /**
     * Enter a new account into the bank.
     * It already assumes that it is a new account.
     *
     * @param acc   The TermAccount object.
     */
    public void insertNewAccount(TermAccount acc)
    {   // Woof! In other cases might be better still check if contains!
        this.accounts.put(acc.getCode(), acc.clone());
    }

    /**
     * Determine the set of account codes belonging to a given holder.
     *
     * @param holder    The account holder's name.
     * @return          A set of account codes belonging to the given holder.
     */
    public Set<String> accountsOf(String holder)
    {
        Set<String> res = new TreeSet<String>();
        for(TermAccount ta : this.getAccounts().values()) {
            if(ta.getHolder().equals(holder)) {
                res.add(ta.getCode());
            }
        }
        return res;
    }

    /**
     * Determine the set of account codes belonging to a set of holders.
     *
     * @param holders   The set of account holders' names.
     * @return          A map of account codes belonging to the given holders.
     */
    public Map<String,Set<String>> accountsByHolders(Set<String> holders)
    {
        Map<String,Set<String>> res = new HashMap<String,Set<String>>();
        for(String holder : holders) {
            res.put(holder, accountsOf(holder));
        }
        return res;
    }

    /**
     * Determine the codes of accounts with capital greater than a given value.
     *
     * @param capThresh The capital threshold.
     * @return          A set of account codes with capital greater than the threshold.
     */
    public Set<String> accountsCapitalOver(double capThresh)
    {
        Set<String> res = new TreeSet<String>();
        for(TermAccount ta : this.getAccounts().values()) {
            if(ta.getCapital() > capThresh) {
                res.add(ta.getCode());
            }
        }
        return res;
    }

    /**
     * Create a Map of accounts with an interest rate higher than a given value.
     *
     * @param irThresh  The interest rate threshold.
     * @return          A Map of account codes to TermAccount objects with interest rate higher than the threshold.
     */
    public Map<String,TermAccount> accountsInterestRateOver(double irThresh)
    {
        Map<String,TermAccount> res = new HashMap<>();
        for(TermAccount ta: this.accounts.values()) {
            if(ta.getInterestRate() > irThresh) {
                res.put(ta.getCode(), ta.clone());
            }
        }
        return res;
    }

    /**
     * Set of accounts that earn interest today.
     *
     * @return  A set of accounts that earn interest today.
     */
    public Set<TermAccount> accountsInterestToday()
    {
        Set<TermAccount> res = new TreeSet<TermAccount>();
        GregorianCalendar today = new GregorianCalendar();
        for(TermAccount ta : this.getAccounts().values()) {
            if(ta.isInterestDay()) {
                res.add(ta.clone());
            }
        }
        return res;
    }

    /**
     * Given a list of account codes, increase their interest rates by an amount X.
     *
     * @param accs  The list of account codes.
     * @param x     The amount to increase the interest rates.
     */
    public void increaseInterestRates(Set<String> accs, double x)
    {
        TermAccount aux;
        for(String code : accs) {
            aux = this.accounts.get(code);  // Gets reference or null if non-existing.
            if(aux != null) {
                aux.changeRate(aux.getInterestRate() + x);
            }
        }
    }

    /**
     * Return the names of all account holders.
     *
     * @return  A set of all account holders' names.
     */
    public Set<String> allAccountHolders()
    {
        Set<String> res = new TreeSet<String>();
        for(TermAccount acc : this.accounts.values()) {
            res.add(acc.getHolder());   // If holder already exists it won't add a repeated
            // There's not a real need to check if already exists in the set.
        }
        return res;
    }

    /**
     * Auxiliary method that sums the capital of all the accounts matching the codes given.
     * 
     * @param codes The codes of the accounts to get and sum the capital from.
     * @return      The sum of all the capital for the respective accounts given.
     */
    private double sumCapital(Set<String> codes)
    {
        double sum = 0;
        for(String code : codes) {
            sum += this.accounts.get(code).getCapital();
        }
        return sum;
    }
    
    /**
     * Create a Map that associates the total value of capital invested in their various accounts
     * with each existing holder name.
     *
     * @return  A Map associating each holder name with the total capital invested.
     */
    public Map<String,Double> getTotalCapitalByHolder()
    {
        Map<String, Double> res = new HashMap<String, Double>();
        for(String holder : this.allAccountHolders()) {
            res.put(holder, this.sumCapital(this.accountsOf(holder)));
        }
        return res;
    }

    // Complementary methods
    /**
     * Check if two Bank objects are equal based on their attributes.
     *
     * @param o The object to compare with.
     * @return  true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;

        Bank b = (Bank) o;
        return (this.bankName.equals(b.getName()) &&
                accounts.equals(b.getAccounts()));
        // So... Seems equals in Set List and etc apply equals to all elements! But when in doubt, do it yourself!
    }

    /**
     * Returns a string representation of the Bank object.
     *
     * @return  A string representation of the Bank object.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Bank{");
        sb.append("Name:'" + this.getName() + "';");
        sb.append(accounts.toString());
        sb.append("}");
        return sb.toString();
    }

    /**
     * Creates a new Bank object that is a deep copy of this Bank object.
     *
     * @return  A new Bank object with the same attributes as the original.
     */
    public Bank clone() { return new Bank(this); }

    /**
     * Returns a hash code value for the Bank object.
     *
     * @return  A hash code value for the Bank object.
     */
    public int hashCode() { return accounts.toString().hashCode(); }    // When in doubt solution! YARGH
}
