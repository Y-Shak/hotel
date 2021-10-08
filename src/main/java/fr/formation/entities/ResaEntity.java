package fr.formation.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "resa" , schema = "hotel", catalog = "")
public class ResaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @OneToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private  ClientEntity client;

    @OneToOne
    @JoinColumn(name = "hotel", referencedColumnName = "id")
    private HotelEntity hotel;

    @Column(nullable = true)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate datedeb;
    @Column(nullable = true)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDate datefin;
    @Column(name = "num_chambre", nullable = false)
    private int numChambre;
}
