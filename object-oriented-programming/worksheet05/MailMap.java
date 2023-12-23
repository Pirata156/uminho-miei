import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

/**
 * The MailMap class associates all received mail with each sending address.
 * It provides various operations to manage and query the email data.
 * Each address is associated with a list of received emails.
 *
 * @author PIRATA
 * @version 2012.04.23
 */
public class MailMap
{
    // Instance variable
    private HashMap<String,ArrayList<Mail>> mailboxes;

    // Constructors
    /**
     * Default constructor for the MailMap class.
     * Initializes mailboxes with an empty HashMap.
     */
    public MailMap()
    {
        this.mailboxes = new HashMap<String,ArrayList<Mail>>();
    }

    /**
     * Copy constructor for the MailMap class.
     * Creates a new MailMap object with the same attribute as the given MailMap object.
     *
     * @param mm    The MailMap object to be copied.
     */
    public MailMap(MailMap mm)
    {
        this.mailboxes = mm.getMailboxes();
    }

    /**
     * Parameterized constructor for the MailMap class.
     * Initializes mailboxes with the given map.
     *
     * @param mbs   The map of addresses to lists of emails.
     */
    public MailMap(HashMap<String,ArrayList<Mail>> mbs)
    {
        this.setMailboxes(mbs);
    }

    // Getters & Setters
    /**
     * Get the mailboxes containing addresses and associated emails.
     *
     * @return  The mailboxes containing addresses and associated emails.
     */
    public HashMap<String,ArrayList<Mail>> getMailboxes()
    {
        HashMap<String,ArrayList<Mail>> res = new HashMap<>();
        ArrayList<Mail> aux;
        for(Map.Entry<String,ArrayList<Mail>> entry : this.mailboxes.entrySet()) {
            aux = new ArrayList<>();
            for(Mail m : entry.getValue()) {
                aux.add(m.clone());
            }
            res.put(entry.getKey(),aux);
        }
        return res;
    }

    /**
     * Set the mailboxes containing addresses and associated emails.
     *
     * @param mbs   The new mailboxes containing addresses and associated emails.
     */
    public void setMailboxes(HashMap<String,ArrayList<Mail>> mbs)
    {
        this.mailboxes = new HashMap<String,ArrayList<Mail>>();
        ArrayList<Mail> aux;
        for(Map.Entry<String,ArrayList<Mail>> entry : mbs.entrySet()) {
            aux = new ArrayList<>();
            for(Mail m : entry.getValue()) {
                aux.add(m.clone());
            }
            this.mailboxes.put(entry.getKey(),aux);
        }
    }

    // Instance methods
    /**
     * Determine the total number of addresses from which mail was received.
     *
     * @return  The total number of addresses from which mail was received.
     */
    public int totalAddresses() { return mailboxes.keySet().size(); }

    /**
     * Save a new email received.
     *
     * @param mail  The email to be saved.
     */
    public void saveEmail(Mail mail)
    {
        String sender = mail.getAddress();
        ArrayList<Mail> aux = null;
        if(!this.mailboxes.containsKey(sender)) {
            // I still haven't gotten any email from this dude
            aux = new ArrayList<>();
            aux.add(mail.clone());
            this.mailboxes.put(sender, aux);
        } else {
            // Already gotten meesages from this sender
            this.mailboxes.get(sender).add(mail.clone());
        }
    }

    /**
     * Determine how many emails originate from a given address.
     *
     * @param add   The address to query.
     * @return      The number of emails originating from the given address.
     */
    public int countFromAddress(String add)
    {
        int res = 0;
        if(this.mailboxes.containsKey(add)) {
            res = this.mailboxes.get(add).size();
        }
        return res;
        /*
         * Whole method could have been written with only:
         * return this.mailboxes.getOrDefault(add, new ArrayList<>()).size();
         */
    }

    /**
     * Create a list containing all addresses that sent emails containing
     * in their subject a list of words given as a parameter.
     *
     * @param words The list of words to check in email subjects.
     * @return      A list containing all addresses meeting the criteria.
     */
    public List<String> addressesSubjectWith(HashSet<String> words)
    {
        List<String> res = new ArrayList<String>();
        boolean exists = false;
        int i, j;
        String word;
        for(Map.Entry<String,ArrayList<Mail>> entry : this.mailboxes.entrySet()) {
            exists = false;
            for(i = 0; i < entry.getValue().size() && !exists; i++) {
                Mail email = entry.getValue().get(i);
                Iterator<String> itWords = words.iterator();    // Yay iterators!
                while(itWords.hasNext() && !exists) {
                    word = itWords.next();
                    if(email.containsInSubject(word)) {
                        res.add(entry.getKey());
                        exists = true;
                    }
                }
            }
        }
        return res;
    }

    /**
     * The same as the previous question but creating a set containing the emails.
     *
     * @param words The list of words to check in email subjects.
     * @return      A set containing all emails meeting the criteria.
     */
    public Set<Mail> emailsSubjectWith(HashSet<String> words)
    {
        HashSet<Mail> res = new HashSet<Mail>();
        boolean exists = false;
        Iterator<String> itWords;
        String word;
        for(ArrayList<Mail> emails : this.mailboxes.values()) {
            for(Mail email : emails) {
                exists = false;
                itWords = words.iterator();
                while(itWords.hasNext() && !exists) {
                    word = itWords.next();
                    if(email.containsInSubject(word)) {
                        res.add(email.clone());
                        exists = true;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Delete all emails received before a date that is given as a parameter.
     *
     * @param date  The date to compare with.
     */
    public void deleteFromBefore(GregorianCalendar date)
    {
        // TODO: Gotta check more about this removeIf and lambdas! This is dope
        for(ArrayList<Mail> emails : mailboxes.values()) {
            emails.removeIf(mail -> mail.isBefore(date));
        }
    }

    /**
     * Create a list of addresses that sent emails today.
     *
     * @return  A list of addresses that sent emails today.
     */
    public List<String> addressesOfToday()
    {
        List<String> res = new ArrayList<>();
        boolean flag = false;
        ArrayList<Mail> emails;
        int i;
        GregorianCalendar today = new GregorianCalendar();
        for(Map.Entry<String, ArrayList<Mail>> entry : mailboxes.entrySet()) {
            flag = false;
            emails = entry.getValue();
            for(i = 0; i < emails.size() && !flag; i++) {
                if(emails.get(i).fromSameDay(today)) {
                    res.add(entry.getKey());
                    flag = true;
                }
            }
        }
        return res;
    }

    /**
     * Given a list of words, delete all emails from a given address that contain any of these in their subject (anti-spam).
     *
     * @param address The address to filter emails.
     * @param words   The list of words to check in email subjects.
     */
    public void deleteSpamEmails(String address,HashSet<String> words)
    {
        if(this.mailboxes.keySet().contains(address)) {
            this.mailboxes.get(address).removeAll(this.emailsSubjectWith(words));
        }
        // TODO: There is for sure a way to make this more efficient! But for the class it's fine!
    }

    /**
     * Create a list with all email addresses from Portugal.
     *
     * @return  A list with all email addresses from Portugal.
     */
    public List<String> addressesFromPortugal()
    {
        List<String> res = new ArrayList<>();
        for(String address : this.mailboxes.keySet()) {
            // Assume that addresses from Portugal end with ".pt"
            if(address.endsWith(".pt")) {
                res.add(address);
            }
        }
        return res;
    }

    // Complementary methods
    /**
     * Check if two MailMap objects are equal based on their attributes.
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

        MailMap mm = (MailMap) o;
        return mailboxes.equals(mm.getMailboxes());
    }

    /**
     * Returns a string representation of the MailMap object.
     *
     * @return  A string representation of the MailMap object.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("MailMap{");
        for(Map.Entry<String, ArrayList<Mail>> entry : mailboxes.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            sb.append(entry.getValue()).append("; ");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Creates a new MailMap object that is a deep copy of this MailMap.
     *
     * @return  A new MailMap object with the same attributes as the original.
     */
    public MailMap clone() { return new MailMap(this); }
}
