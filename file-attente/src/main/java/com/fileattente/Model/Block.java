package com.fileattente.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "Blocked")
public class Block {
    @Id
    @GeneratedValue
    private int id;
    private String cinBlocked;


    public Block(String cin) {
        this.cinBlocked=cin;
    }
}
