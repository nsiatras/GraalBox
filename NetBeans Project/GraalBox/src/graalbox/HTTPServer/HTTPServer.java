package graalbox.HTTPServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Nikos Siatras
 */
public class HTTPServer implements HttpHandler
{

    private InetAddress fServerIPAddress = null;
    private final int fListenerPort;

    public HTTPServer(String serverIPAddress, int listenerPort) throws UnknownHostException
    {
        if (serverIPAddress == null)
        {
            fServerIPAddress = InetAddress.getLocalHost();
        }
        else
        {
            fServerIPAddress = InetAddress.getByName(serverIPAddress);
        }

        fListenerPort = listenerPort;
    }

    public void Start() throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(fServerIPAddress, fListenerPort), 10);
        server.createContext("/", this);
        server.start();

        System.out.println("GraalBox Server started at: http://" + fServerIPAddress.getHostAddress() + ":" + fListenerPort);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        String response = "Hi there!";
        exchange.sendResponseHeaders(200, response.getBytes().length); //response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
