package com.example.byobproject.models;





import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
public class Beer {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=1)
    private String brewery;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Description must not be empty")
    private String description;

    @ManyToMany(mappedBy = "beers", cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToOne
    private Category category;


    public Beer(String brewery, String name, String description) {
        this.brewery = brewery;
        this.name = name;
        this.description = description;
    }

    public Beer() { }

    public int getId() {
        return id;
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


    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }
   public Category getCategory() {
        return category;
        }

    public void setCategory(Category category) {
        this.category = category;
   }


}
