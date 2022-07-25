/**
 * MIT License
 *
 * Copyright (c) 2022 Nikos Siatras
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package graalbox.Core.HTTPServer;

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
