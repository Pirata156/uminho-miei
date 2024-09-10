import java.util.ArrayList;
import java.util.GregorianCalendar;     // DateTime class

/**
 * Represents a list of emails in a given email account.
 * Provides operations for managing and retrieving information about the emails.
 *
 * @author PIRATA
 * @version 2012.04.23
 */
public class MailList
{
    // Instance variables
    private ArrayList<Mail> emails;     // List to store emails

    /**
     * Default constructor for MailList. Initializes an empty list of emails.
     */
    public MailList()
    {
        this.emails = new ArrayList<Mail>();
    }

    /**
     * Copy constructor for MailList.
     *
     * @param ml    Another MailList to copy elements from.
     */
    public MailList(MailList ml)
    {
        this.emails = ml.getEmails();   // Copy the list of emails
    }

    /**
     * Constructor for MailList with a list of emails.
     *
     * @param mails List of emails to initialize the MailList.
     */
    public MailList(ArrayList<Mail> mails)
    {
        this.setEmails(mails);      // Sets a copy of the list of emails
    }

    // Getters & Setters
    /**
     * Get the list of emails in the MailList.
     *
     * @return  A deep copy of the list of emails.
     */
    public ArrayList<Mail> getEmails()
    {
        ArrayList<Mail> res = new ArrayList<Mail>();
        for(Mail mail : this.emails) {
            res.add(mail.clone());
        }
        return res;
    }

    /**
     * Set the list of emails in the MailList. Only non-repeating elements are cloned.
     *
     * @param mails List of emails to be stored.
     */
    public void setEmails(ArrayList<Mail> mails)
    {
        this.emails = new ArrayList<Mail>();
        for(Mail mail : mails) {
            if(!this.emails.contains(mail)) {
                this.emails.add(mail.clone());
            }
        }
    }

    // Instance methods
    /**
     * Determine the total number of emails in the list.
     *
     * @return  The total number of emails.
     */
    public int size() { return this.emails.size(); }

    /**
     * Save a new email in the list.
     *
     * @param email The new email to be saved.
     */
    public void addEmail(Mail email) { this.emails.add(email.clone()); }

    /**
     * Determine how many emails originate from a given address.
     *
     * @param addr  The address to count emails for.
     * @return      The number of emails from the given address.
     */
    public int countFromAddress(String addr)
    {
        int count = 0;
        for(Mail email : this.getEmails()) {
            if(email.asAddress(addr)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Create a list containing the indexes of emails that contain a word in the subject.
     *
     * @param word  The word to search for in the subject.
     * @return      A list of indexes of emails containing the word in the subject.
     */
    public ArrayList<Integer> indexSubjectContains(String word)
    {
        int i;
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for(i = 0; i < this.emails.size(); i++) {
            if(this.emails.get(i).containsInSubject(word)) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    /**
     * Create a list containing emails that contain a word in the subject.
     *
     * @param word  The word to search for in the subject.
     * @return      A list of emails containing the word in the subject.
     */
    public ArrayList<Mail> emailsSubjectContains(String word)
    {
        ArrayList<Mail> res = new ArrayList<>();
        for(Mail email : this.emails) {
            if(email.containsInSubject(word)) {
                res.add(email.clone());
            }
        }
        return res;
    }

    /**
     * Delete all emails received before a given date.
     *
     * @param dt    The date to compare against for deletion.
     */
    public void deleteBeforeDate(GregorianCalendar dt)
    {
        // So, ArrayList as this cool method called removeAll! You know what that means!
        ArrayList<Mail> toDelete = new ArrayList<>();
        for(Mail email : this.emails) {
            if(email.isBefore(dt)) {
                toDelete.add(email);
            }
        }
        this.emails.removeAll(toDelete);
    }
    
    /**
     * Create a list of the today's emails.
     *
     * @return      A list of emails received during today.
     */
    public ArrayList<Mail> emailsOfToday()
    {
        ArrayList<Mail> res = new ArrayList<>();
        GregorianCalendar dt = new GregorianCalendar();
        for(Mail email : this.emails) {
            if(email.fromSameDay(dt)) {
                res.add(email.clone());
            }
        }
        return res;
    }

    /**
     * Delete all emails that contain any of the specified words in their subject (anti-spam).
     *
     * @param words The list of words to check in email subjects.
     */
    public void deleteEmailsContaining(ArrayList<String> words)
    {
        boolean flag;
        int i;
        ArrayList<Mail> toDelete = new ArrayList<>();
        for(Mail email : this.emails) {
            flag = false;
            for(i = 0; !flag && i < words.size(); i++) {
                if(email.containsInSubject(words.get(i))) {
                    toDelete.add(email);
                    flag = true;    // No need to check further if one word is found
                }
            }
        }
        this.emails.removeAll(toDelete);
    }

    /**
     * Delete all emails prior to a given date.
     *
     * @param dt    The date to compare against for deletion.
     */
    public void deleteFromBefore(GregorianCalendar dt)
    {
        ArrayList<Mail> toDelete = new ArrayList<>();
        for(Mail email : this.emails) {
            if(email.isBefore(dt)) {
                toDelete.add(email);
            }
        }
        this.emails.removeAll(toDelete);
    }

    // Complementary methods
    /**
     * Check if two MailList objects are equal based on their list of emails.
     *
     * @param o The object to compare to.
     * @return  true if the MailLists are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || this.getClass() != o.getClass())
            return false;
        MailList ml = (MailList) o;
        return this.emails.equals(ml.getEmails());  // ArrayList's equals compare all elements on same order.
    }

    /**
     * Returns a string representation of the MailList in the format "MailList [<mail>; ... ]".
     *
     * @return  A string representation of the MailList.
     */
    public String toString()
    {
        int i;
        StringBuffer sb = new StringBuffer("MailList [");
        for(i = 0; i < this.emails.size(); i++) {
            sb.append(this.emails.get(i).toString());
            if(i + 1 < this.emails.size()) {
                sb.append("; ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Creates a new MailList object that is a deep copy of this MailList.
     *
     * @return  A new MailList object with the same emails as the original.
     */
    public MailList clone() { return new MailList(this); }
}
