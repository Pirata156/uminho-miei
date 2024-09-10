import java.util.TreeSet;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * Write a description of class ClientHashMap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ClientTreeSet implements Serializable
{
    private TreeSet<Client> clients;

    public ClientTreeSet()
    {
        this.clients = new TreeSet<Client>(Client.ClientNifComparator);
    }

    public ClientTreeSet(ClientTreeSet other)
    {
        this.clients = new TreeSet<Client>(Client.ClientNifComparator);
        for(Client c : other.clients) {
            this.clients.add(c.clone());
        }
    }

    public TreeSet<Client> getClients(Comparator<Client> sortAlgorithm)
    {
        TreeSet<Client> result = new TreeSet<Client>(sortAlgorithm);
        for(Client c : this.clients) {
            result.add(c.clone());
        }
        return result;
    }

    public TreeSet<Client> getClients()
    {
        return getClients(Client.ClientNifComparator);
    }

    public boolean add(Client c)
    {
        boolean flag = false;
        if(!this.clients.contains(c)) {
            this.clients.add(c);
            flag = true;
        }
        return flag;
    }

    public List<Client> listByName()
    {
        List<Client> result = new ArrayList<Client>(this.getClients(Client.ClientNameComparator));
        return result;
    }

    public List<Client> listByNIF()
    {
        List<Client> result = new ArrayList<Client>(this.getClients(Client.ClientNifComparator));
        return result;
    }

    public List<Client> listByContractedValue()
    {
        List<Client> result = new ArrayList<Client>(this.getClients(Client.ClientValueComparator));
        return result;
    }

    public List<Client> findNif(String nif)
    {
        List<Client> res = new ArrayList<Client>();
        for(Client c : this.clients) {
            if(c.getNIF().equals(nif)) {
                res.add(c.clone());
                break;
                // I be not a fan o''break', but oh well.
            }
        }
        return res;
    }

    public List<Client> findName(String name)
    {
        List<Client> res = new ArrayList<Client>();
        for(Client c : this.clients) {
            if(c.getName().equals(name)) {
                res.add(c.clone());
            }
        }
        return res;
    }

    public boolean addNewService(CommercialProduct service, String nif_client)
    {
        boolean res = false;
        ArrayList<Client> temp = new ArrayList<Client>();
        for(Client c : this.clients) {
            if(c.getNIF().equals(nif_client)) {
                temp.add(c);
                break;
            }
        }
        if(temp.size() > 0) {
            Client cl = temp.get(0);
            cl.addProduct(service);
            res = true;
        }
        return res;
    }
}
