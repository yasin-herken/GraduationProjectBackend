package graduationproject.backend.Customer.entity;

import graduationproject.backend.Order.entity.Orders;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Customer")
@Getter
@Setter
public class Customer {

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "orderId")
    Set<Orders> orders = new HashSet<Orders>(0);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String city;
    private String address;
    private String zipCode;
    private String phone;
    private String company;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String email, String country, String city, String address, String zipCode, String phone, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
        this.phone = phone;
        this.company = company;
    }

    public String getNameAndSurname() {
        return this.firstName + " " + this.lastName;
    }
}
