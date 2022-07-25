package graalbox.HTTPServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author Nikos Siatras
 */
public class HTTPServer implements HttpHandler
{

    public HTTPServer()
    {

    }

    public void Start() throws IOException
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(28888), 10);
        server.createContext("/", this);
        server.start();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        String response = "Hi there!";
        exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
