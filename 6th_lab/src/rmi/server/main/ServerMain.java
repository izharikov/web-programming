package rmi.server.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rmi.server.RmiRecordOption;
import rmi.server.RmiRecordOptionImpl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.MessageFormat;

/**
 * @author Ihar Zharykau
 */
public class ServerMain {
    private static final Logger log = LogManager.getLogger(ServerMain.class);
    public static final int PORT = 8005;
    public static final String RMI_URL_FORMAT = "rmi://localhost:" + PORT + "/{0}";
    public static final String RMI_OBJECT_NAME = "recordOption";

    public static void main(String... args)  {
        log.info("Starting server");
        try {
            RmiRecordOption recordOption = new RmiRecordOptionImpl();
            Naming.rebind(MessageFormat.format(RMI_URL_FORMAT, RMI_OBJECT_NAME), recordOption);
            log.info("Server started successfully");
        } catch (RemoteException | MalformedURLException e){
            log.error(e.getMessage(), e);
        }
    }
}
