import com.sun.jmx.snmp.tasks.Task;
import controller.GameController;
import model.LiaisonRMI;
import model.ShipShop;
import model.ShipShopInterface;
import view.panel.MainObserver;

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    public static void main(String[] args)  throws NamingException, RemoteException, NotBoundException {
        Thread server = new Thread(new Task() {
            @Override
            public void cancel() {

            }

            @Override
            public void run() {
                try {
                    String name = "shipshop";

                    System.out.println("Constructing server implementation...");

                    ShipShop impl = new ShipShop();

                    System.out.println("Binding server implementation to registry...");

                    Registry registry = LocateRegistry.createRegistry(8080);
                    registry.bind(name, impl);

                    System.out.println("Waiting for invocations from clients...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        server.run();


        Registry registry = LocateRegistry.getRegistry(8080);

        ShipShopInterface shipshop = (ShipShopInterface) registry.lookup("shipshop");

        // Initialise model
        LiaisonRMI liaisonRMI = new LiaisonRMI(shipshop);

        // Initialise controller
        GameController controller = new GameController(liaisonRMI);

        // Initialise views
        MainObserver mainObserver = new MainObserver(controller);

        // Adding controller
        liaisonRMI.addObserver(mainObserver);
    }
}
