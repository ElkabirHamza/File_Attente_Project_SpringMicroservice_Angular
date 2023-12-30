package com.fileattente.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "info")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nombreDansMachine;

    private String name;

    private int nombreEnCaisse;


}
