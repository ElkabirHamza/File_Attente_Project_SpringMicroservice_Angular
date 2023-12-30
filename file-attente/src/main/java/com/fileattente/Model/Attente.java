package com.fileattente.Model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fileattente.Enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Attente")
public class Attente {

    @Id
    private String id;

    @Column(name = "cin")
    private String cin;

    @Column(name = "service")
    private String service;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreation;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    private int nb;

    private String Tel;



}
