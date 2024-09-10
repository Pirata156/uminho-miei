import java.util.GregorianCalendar;     // DateTime class

/**
 * Represents an email received in a given email account.
 * Each email contains the address of the sender, the date sent, the date received, the subject, and the text of the email.
 *
 * @author PIRATA
 * @version 2012.04.16
 */
public class Mail
{
    // Instance variables
    private String address;
    private String subject;
    private String text;
    private GregorianCalendar sendDate;
    private GregorianCalendar receivedDate;

    // Constructors
    /**
     * Default constructor for Mail. Initializes empty values for all attributes.
     */
    public Mail()
    {
        this.address = "";
        this.subject = "";
        this.text = "";
        this.sendDate = new GregorianCalendar();
        this.receivedDate = new GregorianCalendar();
    }
    
    /**
     * Copy constructor for Mail.
     *
     * @param m Another Mail to copy attributes from.
     */
    public Mail(Mail m)
    {
        this.address = m.getAddress();
        this.subject = m.getSubject();
        this.text = m.getText();
        this.sendDate = (GregorianCalendar) m.getSendDate().clone();
        this.receivedDate = (GregorianCalendar) m.getReceivedDate().clone();
    }
    
    /**
     * Constructor for Mail with all attributes.
     *
     * @param ad    The address of the sender.
     * @param sb    The subject of the email.
     * @param txt   The text of the email.
     * @param sD    The date the email was sent.
     * @param rD    The date the email was received.
     */
    public Mail(String ad, String sb, String txt, GregorianCalendar sD, GregorianCalendar rD)
    {
        this.setAddress(ad);
        this.setSubject(sb);
        this.setText(txt);
        this.setSendDate(sD);
        this.setReceivedDate(rD);
    }

    // Getters & Setters
    /**
     * Get the address of the sender.
     *
     * @return  The address of the sender.
     */
    public String getAddress() { return this.address; }
    
    /**
     * Set the address of the sender.
     *
     * @param ad    The address of the sender.
     */
    public void setAddress(String ad) { this.address = ad; }

    /**
     * Get the subject of the email.
     *
     * @return  The subject of the email.
     */
    public String getSubject() { return this.subject; }

    /**
     * Set the subject of the email.
     *
     * @param sb    The subject of the email.
     */
    public void setSubject(String sb) { this.subject = sb; }
    
    /**
     * Get the text of the email.
     *
     * @return  The text of the email.
     */
    public String getText() { return this.text; }

    /**
     * Set the text of the email.
     *
     * @param txt   The text of the email.
     */
    public void setText(String txt) { this.text = txt; }
    
    /**
     * Get the date the email was sent.
     *
     * @return  The date the email was sent.
     */
    public GregorianCalendar getSendDate() { return (GregorianCalendar) this.sendDate.clone(); }

    /**
     * Set the date the email was sent.
     *
     * @param sD    The date the email was sent.
     */
    public void setSendDate(GregorianCalendar sD) { this.sendDate = (GregorianCalendar) sendDate.clone(); }
    
    /**
     * Get the date the email was received.
     *
     * @return  The date the email was received.
     */
    public GregorianCalendar getReceivedDate() { return (GregorianCalendar) this.receivedDate.clone(); }
    
    /**
     * Set the date the email was received.
     *
     * @param rD    The date the email was received.
     */
    public void setReceivedDate(GregorianCalendar rD) { this.receivedDate = (GregorianCalendar) receivedDate.clone(); }
    
    // Instance methods - Needed for MailList
    /**
     * Checks if the email is from a specific address.
     *
     * @param addr  The address to compare with.
     * @return      true if the email is from the specified address, false otherwise.
     */
    public boolean asAddress(String addr) { return this.getAddress().equals(addr); }
    
    /**
     * Checks if the subject of the email contains a specific string.
     *
     * @param str   The string to check in the subject.
     * @return      true if the subject contains the specified string, false otherwise.
     */
    public boolean containsInSubject(String str) { return this.getSubject().contains(str); }
    
    /**
     * Checks if the email is received on the same day as the specified date.
     *
     * @param dt    The date to compare with the received date.
     * @return      true if the email is received on the same day as the specified date, false otherwise.
     */
    public boolean fromSameDay(GregorianCalendar dt)
    {
        int dDt, mDt, yDt, dRd, mRd, yRd;
        dDt = dt.get(GregorianCalendar.DAY_OF_MONTH);
        mDt = dt.get(GregorianCalendar.MONTH);
        yDt = dt.get(GregorianCalendar.YEAR);
        dRd = this.getReceivedDate().get(GregorianCalendar.DAY_OF_MONTH);
        mRd = this.getReceivedDate().get(GregorianCalendar.MONTH);
        yRd = this.getReceivedDate().get(GregorianCalendar.YEAR);
        return (dDt == dRd && mDt == mRd && yDt == yRd);
    }
    
    /**
     * Checks if the email is received before a specified date.
     *
     * @param dt    The date to compare with the received date.
     * @return      true if the email is received before the specified date, false otherwise.
     */
    public boolean isBefore(GregorianCalendar dt) { return this.getReceivedDate().before(dt); }
    
    // Complementary methods
    /**
     * Checks if two Mail objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the Mail objects are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Mail m = (Mail) o;
        return (this.address.equals(m.address) &&
                this.subject.equals(m.subject) &&
                this.text.equals(m.text) &&
                this.sendDate.equals(m.sendDate) &&
                this.receivedDate.equals(m.receivedDate));
    }
    
    /**
     * Returns a string representation of the Mail object.
     *
     * @return  A string representation of the Mail object.
     */
    public String toString()
    {
        StringBuilder s = new StringBuilder("Mail{");
        s.append("From: " + this.getAddress() + ";");
        s.append(" Subject: " + this.getSubject() + ";");
        s.append(" Sent: " + this.getSendDate().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (this.getSendDate().get(GregorianCalendar.MONTH) + 1) + "/" +
            this.getSendDate().get(GregorianCalendar.YEAR) + ";");
        s.append("Received: " + this.getReceivedDate().get(GregorianCalendar.DAY_OF_MONTH) + "/" + (this.getReceivedDate().get(GregorianCalendar.MONTH) + 1) + "/" +
            this.getReceivedDate().get(GregorianCalendar.YEAR) + ";");
        s.append(this.getText() + "}");
        return s.toString();
    }
    
    /**
     * Creates a new Mail object that is a deep copy of this Mail.
     *
     * @return  A new Mail object with the same attributes as the original.
     */
    public Mail clone() { return new Mail(this); }
    
    /**
     * Returns a hash code for the Mail object.
     *
     * @return  A hash code for the Mail object.
     */
    public int hashCode() { return this.toString().hashCode(); }    // Seems to be needed for next classes Maps!?
}
