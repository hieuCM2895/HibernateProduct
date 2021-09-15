package hibernate.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "manufacturer")
public class Manufacturer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String country;

    @ManyToMany(mappedBy = "manufacturers", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products.add(products);
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", products=" + products +
                '}';
    }
}
