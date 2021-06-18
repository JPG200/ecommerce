package application.domain;

import application.commons.*;

public class Client {
    private final NonEmptyString clientName;
    private final NonEmptyString clientLastName;
    private final NonEmptyString clientUbication;
    private final IdentificationNumber idClient;
    private final IdentificationType idType;

    public Client(NonEmptyString clientName, NonEmptyString clientLastName, NonEmptyString clientUbication, IdentificationNumber idClient, IdentificationType idType) {
        Validate.notNull(clientName,"Client Name can not be null");
        Validate.notNull(clientLastName,"Client Last Name can not be null");
        Validate.notNull(clientUbication,"Client Ubication can not be null");
        Validate.notNull(idClient,"Id Client can not be null");
        Validate.notNull(idType,"Id Type can not be null");

        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.clientUbication = clientUbication;
        this.idClient = idClient;
        this.idType = idType;
    }

    public IdentificationNumber getidentificationNumber(){return this.idClient;}
    public NonEmptyString getclientName(){return this.clientName;}
    public NonEmptyString getclientLastName(){return this.clientLastName;}
    public NonEmptyString getclientUbication(){return this.clientUbication;}
    public IdentificationNumber getidClient(){return this.idClient;}
    public IdentificationType getIdentificationType(){return this.idType;}
}
