package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 2,max = 50)
    private String description;
    @NotNull
    @Size(min = 2,max = 50)
    private String brand;
    @DecimalMin("50")
    private double price;

    private int quantity=1;

    @ManyToOne
    private Category category;
}
