package hibernate.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean deleted = Boolean.FALSE;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @PrePersist
    protected void onCreate() {
        this.created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
    }

    // khi truy vấn tới product thì nó sẽ lấy thông tin category lên luôn
    // nếu lazy lấy product thì chưa lấy category cho đến khi nào mình truy cập category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "prod_manufacturer",
            joinColumns = {
                    @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "manufacturer_id")
            }
    )
    private Set<Manufacturer> manufacturers = new HashSet<>();

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Manufacturer manufacturers) {
        this.manufacturers.add(manufacturers);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
