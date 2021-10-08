package fr.formation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "client" , schema = "hotel", catalog = "")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "nom_complet", nullable = false, length = 50)
    private String nomComplet;
    @Column(nullable = false)
    private String telephone;
    private String email;
    private String adresse;

}
