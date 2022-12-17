package org.enset;

import jakarta.xml.ws.Endpoint;
import org.enset.ws.BanqueService;

public class ServeurJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:9191/",new BanqueService());
        System.out.println("Web Service déployé sur l'adresse http://0.0.0.0:9191/!");
    }
}
