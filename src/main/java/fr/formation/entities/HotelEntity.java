package fr.formation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data @ToString @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "hotel", schema = "hotel", catalog = "")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(nullable = false)
    private String nom;
    private int etoiles;
    private String adresse;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String telephone;
    private String ville;
}
