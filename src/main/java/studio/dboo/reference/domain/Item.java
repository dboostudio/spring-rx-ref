package studio.dboo.reference.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.awt.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String id;
    private String name;
    private double price;

    private String description;
    private String distributorRegion;
    private Date realeaseDate;
    private int availableUnits;
    private Point location;
    private boolean active;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
