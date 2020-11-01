/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.app;

import com.demo.models.XML;
import com.demo.utils.XMLParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 *
 * @author karim.omran
 */
public class HttpService {

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            
            if("POST".equals(httpExchange.getRequestMethod())) { 
                XML xml = XMLParser.handleXMLParsing(httpExchange.getRequestBody());
                handleResponse(httpExchange, xml);
            } else {
                String response = "Not Supported " + httpExchange.getRequestMethod() + "!";
                httpExchange.sendResponseHeaders(200, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
    
    private static void handleResponse(HttpExchange httpExchange, XML xml)  throws  IOException {
            OutputStream outputStream = httpExchange.getResponseBody();
            StringBuilder htmlBuilder = new StringBuilder();
            
            htmlBuilder.append("<html>").
                    append("<body>").
                    append("<h1>").
                    append("XML Type : ")
                    .append(xml.getXMLType())
                    .append("</h1>")
                    .append("</body>")
                    .append("</html>");
            String htmlResponse = htmlBuilder.toString();
            httpExchange.sendResponseHeaders(200, htmlResponse.length());
            outputStream.write(htmlResponse.getBytes());
            outputStream.flush();
            outputStream.close();
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/demo", new MyHandler());
        server.setExecutor(null);
        server.start();
    }
    
}
