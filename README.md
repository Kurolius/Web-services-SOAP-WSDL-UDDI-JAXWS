# Web-services-SOAP-WSDL-UDDI-JAXWS

## Déploiemen du Web service avec un simple Serveur JaxWS

### Classe Compte :

```java=17

public class Compte {
    private int code;
    @XmlTransient
    private Date dateCreation;
    private double solde;

    public Compte(int code, Date dateCreation, double solde) {
        this.code = code;
        this.dateCreation = dateCreation;
        this.solde = solde;
    }

    public Compte() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}

```
### Classe BanqueService :

```java=17

@WebService(serviceName = "BanqueWS")
public class BanqueService {
    @WebMethod(operationName = "Convert")
    public double conversion(@WebParam(name = "montant") double mt){
        return mt*10.54;
    }

    @WebMethod
    public Compte getCompte(@WebParam(name = "code") int code){
        return new Compte(code,new Date(),Math.random()*9888);
    }

    @WebMethod
    public List<Compte> comptes(){
        return List.of(
        new Compte(1,new Date(),Math.random()*9888),
        new Compte(2,new Date(),Math.random()*9888),
        new Compte(3,new Date(),Math.random()*9888)

        );
    }
}

```
### serveur JaxWS:

```java=17

public class ServeurJWS {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:9191/",new BanqueService());
        System.out.println("Web Service déployé sur l'adresse http://0.0.0.0:9191/!");
    }
}

```

![image](https://user-images.githubusercontent.com/84138772/208242980-35046f75-877a-478e-9a67-d98651b7d337.png)

## Consultation et analyse du fichier WSDL avec un Browser HTTP

![image](https://user-images.githubusercontent.com/84138772/208243018-b635635b-62e5-44c7-9097-725e3061d975.png)

![image](https://user-images.githubusercontent.com/84138772/208243056-63741637-523b-4d26-afe1-74a5144ab6d7.png)


## Test avec SoapUI

![image](https://user-images.githubusercontent.com/84138772/208243291-fc8b285e-f94d-48bf-9fb5-75eb60bf1059.png)

![image](https://user-images.githubusercontent.com/84138772/208243499-75b43f7d-0ba8-4b66-92bb-f989628f8b61.png)

![image](https://user-images.githubusercontent.com/84138772/208243456-fbfbefac-0b90-4420-b065-25c73366bc5c.png)

![image](https://user-images.githubusercontent.com/84138772/208243522-8207e230-d2bf-40cf-85dd-851993a9b8b4.png)

## Cliente SOAP Java :

### Generation du proxy avec jakarta : 

![image](https://user-images.githubusercontent.com/84138772/208244615-b542207e-8924-4c0e-ace5-fde2345e6541.png)

### Dossier proxy : 

![image](https://user-images.githubusercontent.com/84138772/208244660-9e39abed-abc0-4720-bd88-6f053f5da457.png)

### Code :

```java=17

public class ClientWS {
    public static void main(String[] args) {
        //middleware
        BanqueService stub = new BanqueWS().getBanqueServicePort();
        System.out.println(stub.convert(5600));
        Compte compte = stub.getCompte(5);
        System.out.println(compte.getCode()+" "+compte.getSolde());
    }
}

```
### Test :

![image](https://user-images.githubusercontent.com/84138772/208244732-e5f5ea22-4e59-4236-a634-57effea80406.png)
