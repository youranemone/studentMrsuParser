package parser;

import java.util.Objects;

public class Client {
    int id;              // идентификатор
    String name;            // ФИО
    int personnelNumber; // персональный номер
    String address;

    public Client(int id, String name, int personnelNumber, String address) {
        this.id = id;
        this.name = name;
        this.personnelNumber = personnelNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(int personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id && personnelNumber == client.personnelNumber && Objects.equals(name, client.name) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, personnelNumber, address);
    }
}
