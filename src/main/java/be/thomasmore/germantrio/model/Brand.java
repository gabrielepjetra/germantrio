package be.thomasmore.germantrio.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    private String flagship;
    private String imgUrl;

    @OneToMany(mappedBy = "brand")
    private List<CarModel> carModels = new ArrayList<>();

    public Brand() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getFlagship() {
        return flagship;
    }

    public void setFlagship(String flagship) {
        this.flagship = flagship;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }
}
