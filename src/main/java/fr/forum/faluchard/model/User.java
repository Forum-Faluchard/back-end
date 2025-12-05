package fr.forum.faluchard.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "FAL_USER")
@SequenceGenerator(name = "userIdSeq", initialValue = 1, allocationSize = 100)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSeq")
    private Long id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(length = 64)
    private String password;
}
