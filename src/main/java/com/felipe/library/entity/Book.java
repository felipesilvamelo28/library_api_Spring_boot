package com.felipe.library.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double edition;
    private String publicationYear;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(
        name = "book_author",
        joinColumns =
            {@JoinColumn(name = "book_id", referencedColumnName = "id")},
        inverseJoinColumns =
            {@JoinColumn(name = "author_id", referencedColumnName = "id")}
    )
    private Author author;
    private Double price;
}
