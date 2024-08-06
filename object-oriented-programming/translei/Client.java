import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Write a description of class Client here.
 *
 * @author PIRATA
 * @version v_002
 */
public class Client implements Serializable
{
    public enum ClientType
    {
        BUSINESS, PRIVATE
    }

    // Instance variables
    private String name;
    private String address;
    private String idNIF;
    private ClientType type;
    private ArrayList<CommercialProduct> products;

    // Constructors
    public Client()
    {
        this.name = "UNDEFINED";
        this.address = "UNDEFINED";
        this.idNIF = "UNDEFINED";
        this.type = ClientType.PRIVATE;
        this.products = new ArrayList<>();
    }

    public Client(String name, String addr, String nif, ClientType type, List<CommercialProduct> prods)
    {
        this.setName(name);
        this.setAddress(addr);
        this.setNIF(nif);
        this.setType(type);
        this.setProducts(prods);
    }

    public Client(Client c)
    {
        this.name = c.getName();
        this.address = c.getAddress();
        this.idNIF = c.getNIF();
        this.type = c.getType();
        this.products = new ArrayList<>(c.getProducts());
    }

    // Getters & Setters
    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return this.address; }

    public void setAddress(String addr) { this.address = addr; }

    public String getNIF() { return this.idNIF; }

    public void setNIF(String nif) { this.idNIF = nif; }

    public ClientType getType() { return this.type; }

    public void setType(ClientType type) { this.type = type; }

    public List<CommercialProduct> getProducts()
    {
        List<CommercialProduct> res = new ArrayList<>();
        for(CommercialProduct cp : this.products) {
            res.add(cp.clone());
        }
        return res;
    }

    public void setProducts(List<CommercialProduct> prods)
    {
        this.products = new ArrayList<>();
        for(CommercialProduct cp : prods) {
            this.products.add(cp.clone());
        }
    }

    // Instance methods
    public CommercialProduct lastProduct()
    {
        return this.products.get(this.products.size() - 1).clone();
    }

    public double sumContractedValue()
    {
        double sum = 0;
        for(CommercialProduct p : this.products) {
            sum += p.getCost();
        }
        return sum;
    }

    public void addProduct(CommercialProduct cp)
    {
        this.products.add(cp.clone());
    }
    
    // Complementary methods
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
        if((o == null) || (o.getClass() != this.getClass()))
            return false;
        Client cl = (Client) o;
        // Not comparing the products. Just the person itself.
        return cl.getNIF().equals(this.getNIF())
            && cl.getName().equals(this.getName())
            && cl.getAddress().equals(this.getAddress())
            && cl.getType().equals(this.getType());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getNIF());
        sb.append(" -- " + this.getName());
        sb.append(" -- " + this.sumContractedValue() + "€");
        return sb.toString();
    }

    @Override
    public Client clone() { return new Client(this); }

    @Override
    public int hashCode() { return this.getNIF().hashCode(); }

    // Comparator - could be created in a separated file!
    // public class ClientNameComparator implements Comparator<Client> { ... } with the override of compare in it
    /**
     * Comparator for comparing clients based on their names.
     */
    public static Comparator<Client> ClientNameComparator = new ClientNameComparator();

    public static class ClientNameComparator implements Comparator<Client>, Serializable
    {
        /**
         * Compares two clients based on their names.
         *
         * @param cli1  The first client.
         * @param cli2  The second client.
         * @return      A negative integer, zero, or a positive integer as the first
         *              client's name is less than, equal to, or greater than the second client.
         */
        @Override
        public int compare(Client cli1, Client cli2)
        {
            // Take into atention that names are repeatable. Using NIF on compare to negate that.
            String s1 = cli1.getName() + cli1.getNIF();
            String s2 = cli2.getName() + cli2.getNIF();
            return s1.compareTo(s2);
        }
    };

    /**
     * Comparator for comparing clients based on their NIFs.
     */
    public static Comparator<Client> ClientNifComparator = new ClientNifComparator();

    public static class ClientNifComparator implements Comparator<Client>, Serializable
    {
        /**
         * Compares two clients based on their NIFs.
         *
         * @param cli1  The first client.
         * @param cli2  The second client.
         * @return      A negative integer, zero, or a positive integer as the first
         *              client's NIF is less than, equal to, or greater than the second client.
         */
        @Override
        public int compare(Client cli1, Client cli2)
        {
            String s1 = cli1.getNIF();
            String s2 = cli2.getNIF();
            return s1.compareTo(s2);
        }
    };

    /**
     * Comparator for comparing clients based on their contracted values.
     */
    public static Comparator<Client> ClientValueComparator = new ClientValueComparator();

    public static class ClientValueComparator implements Comparator<Client>, Serializable
    {
        /**
         * Compares two clients based on their NIFs.
         *
         * @param cli1  The first client.
         * @param cli2  The second client.
         * @return      A negative integer, zero, or a positive integer as the first
         *              client's value is less than, equal to, or greater than the second client.
         */
        @Override
        public int compare(Client cli1, Client cli2)
        {
            double d1 = cli1.sumContractedValue();
            double d2 = cli2.sumContractedValue();
            int res = Double.compare(d1, d2);
            if(res == 0) {
                res = cli1.getNIF().compareTo(cli2.getNIF());
            }
            return res;
        }
    };
}
