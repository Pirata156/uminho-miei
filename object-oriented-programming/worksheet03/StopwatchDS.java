import java.util.GregorianCalendar;

/**
 * The StopwatchDS class represents a double-split stopwatch that can be used to
 * measure and calculate time intervals between multiple start and stop events.
 *
 * @author (PIRATA)
 * @version (2012.06.04)
 */
public class StopwatchDS
{
    // Instance variables
    private long timeStart;         // Stores the time when the stopwatch is started.
    private long timeFirstStop;     // Stores the time when the first stop event occurs.
    private long timeSecondStop;    // Stores the time when the second stop event occurs.

    // Constructors
    /**
     * Default constructor for objects of class StopwatchDS.
     * Initializes all the times at 0.
     */
    public StopwatchDS()
    {
        this.timeStart = 0;
        this.timeFirstStop = 0;
        this.timeSecondStop = 0;
    }
    
    /**
     * Constructs a new StopwatchDS by copying the attributes of the given StopwatchDS.
     * 
     * @param s The StopwatchDS to copy the data from.
     */
    public StopwatchDS(StopwatchDS s)
    {
        this.setStart(s.getStart());
        this.setFstStop(s.getFstStop());
        this.setSndStop(s.getSndStop());
    }
    
    /**
     * Constructs a new StopwatchDS using the given times.
     * 
     * @param st    The starting time in millis of the ERA.
     * @param fst   The first stop time in millis of the ERA.
     * @param snd   The second stop time in millis of the ERA.
     */
    public StopwatchDS(long st, long fst, long snd)
    {
        this.setStart(st);
        this.setFstStop(fst);
        this.setSndStop(snd);
    }
    
    // Getters & Setters
    /**
     * Get the start time of the StopwatchDS.
     * 
     * @return  The start time of the StopwatchDS.
     */
    public long getStart() { return this.timeStart; }
    
    /**
     * Get the first stop time of the StopwatchDS.
     * 
     * @return  The first stop time of the StopwatchDS.
     */
    public long getFstStop() { return this.timeFirstStop; }
    
    /**
     * Get the second stop time of the StopwatchDS.
     * 
     * @return  The second stop time of the StopwatchDS.
     */
    public long getSndStop() { return this.timeSecondStop; }
    
    /**
     * Set the start time of the StopwatchDS.
     * 
     * @param t The start time of the StopwatchDS.
     */
    public void setStart(long t) { this.timeStart = (t < 0) ? 0 : t; }
    
    /**
     * Set the first stop time of the StopwatchDS.
     * 
     * @param t The first stop time of the StopwatchDS.
     */
    public void setFstStop(long t) { this.timeFirstStop = (t < 0) ? 0 : t; }
    
    /**
     * Set the second stop time of the StopwatchDS.
     * 
     * @param t The second stop time of the StopwatchDS.
     */
    public void setSndStop(long t) { this.timeSecondStop = (t < 0) ? 0 : t; }

    // Instance methods
    /**
     * Starts the stopwatch and records the current time as the start time.
     */
    public void start()
    {
        GregorianCalendar gc = new GregorianCalendar();
        this.setStart(gc.getTimeInMillis());
    }

    /**
     * Marks the first stop event and records the current time.
     */
    public void firstStop()
    {   // Could be a choice to check that start() was called before!
        GregorianCalendar gc = new GregorianCalendar();
        this.setFstStop(gc.getTimeInMillis());
    }
    
    /**
     * Marks the second stop event and records the current time.
     */
    public void secondStop()
    {   // Could be a choice to check that start() and firstStop() was called before!
        GregorianCalendar gc = new GregorianCalendar();
        this.setSndStop(gc.getTimeInMillis());
    }
    
    /**
     * Formats a given time in milliseconds into a string representation in the
     * "HH:MM:SS:SSS" format (hours, minutes, seconds, and milliseconds).
     *
     * @param timeMillis    The time in milliseconds to format.
     * @return              The formatted time string.
     */
    private static String millisToString(long timeMillis)
    {
        long millis, secs, mins, hours;
        String res;
        millis = timeMillis % 1000;
        secs = timeMillis / 1000;
        mins = secs / 60;
        secs = secs % 60;
        hours = mins / 60;
        mins = mins % 60;
        if(hours > 0) {
            res = String.format("%02d:%02d:%02d:%03d", hours, mins, secs, millis);
        } else {
            res = String.format("%02d:%02d:%03d", mins, secs, millis);
        }
            return res;
    }
    
    /**
     * Gets the time between the start and the first stop in a formatted string.
     *
     * @return  The formatted time between the start and the first stop.
     */
    public String startToFstStop()
    {
        long diff = this.getFstStop() - this.getStart();
        if(diff < 0) {
            diff = 0;
        }
        return StopwatchDS.millisToString(diff);
    }
    
    /**
     * Gets the time between the start and the second stop in a formatted string.
     *
     * @return  The formatted time between the start and the second stop.
     */
    public String startToSndStop()
    {
        long diff = this.getSndStop() - this.getStart();
        if(diff < 0) {
            diff = 0;
        }
        return StopwatchDS.millisToString(diff);
    }
    
    /**
     * Gets the time between the first and the second stop in a formatted string.
     *
     * @return  The formatted time between the first and the second stop.
     */
    public String fstToSndStop()
    {
        long diff = this.getSndStop() - this.getFstStop();
        if(diff < 0) {
            diff = 0;
        }
        return StopwatchDS.millisToString(diff);
    }
    
    /**
     * Gets the formatted start time as a string.
     *
     * @return  The formatted start time string.
     */
    public String absStart()
    {
        return StopwatchDS.millisToString(this.getStart());
    }
    
    /**
     * Gets the formatted time of the first stop event as a string.
     *
     * @return  The formatted time of the first stop event string.
     */
    public String absFstStop()
    {
        return StopwatchDS.millisToString(this.getFstStop());
    }
    
    /**
     * Gets the formatted time of the second stop event as a string.
     *
     * @return  The formatted time of the second stop event string.
     */
    public String absSndStop()
    {
        return StopwatchDS.millisToString(this.getSndStop());
    }
    
    /**
     * Generates a string representation of the stopwatch results.
     *
     * @param prt   If true, print the result to the console.
     * @return      The string representation of the results.
     */
    public String results(boolean prt)
    {
        String res;
        StringBuilder sb = new StringBuilder();
        sb.append("Total time:" + this.startToSndStop() + ";");
        sb.append(" First interval:" + this.startToFstStop() + ";");
        sb.append(" Second interval:" + this.fstToSndStop());
        res = sb.toString();
        if(prt) {
            System.out.println(res);
        }
        return res;
    }
    
    // Complementary methods
    /**
     * Check if two StopwatchDS objects are equal based on their attributes.
     *
     * @param o The object to compare to.
     * @return  true if the StopwatchDSs are equal, false otherwise.
     */
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (this.getClass() != o.getClass()))
            return false;
        StopwatchDS s = (StopwatchDS) o;
        return ((this.getStart() == s.getStart()) &&
                (this.getFstStop() == s.getFstStop()) &&
                (this.getSndStop() == s.getSndStop()));
    }
    
    /**
     * Returns a string representation of the StopwatchDS in the format
     * "StopwatchDS {Start:HH:MM:SS:SSS; FirstStop:HH:MM:SS:SSS; SecondStop:HH:MM:SS:SSS}".
     * 
     * @return  A string representation of the StopwatchDS.
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("StopwatchDS {");
        sb.append("Start:" + this.absStart() + "; ");
        sb.append("FirstStop:" + this.absFstStop() + "; ");
        sb.append("SecondStop:" + this.absSndStop() + "}");
        return sb.toString();
    }
    
    /**
     * Creates a new StopwatchDS object that is a deep copy of this StopwatchDS.
     * 
     * @return  A new StopwatchDS object with the same attributes as the original.
     */
    public StopwatchDS clone()
    {
        return new StopwatchDS(this);
    }
}
