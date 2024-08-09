package com.example.week3homework.week3homework.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professor")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "professor_name", nullable = false)
    private String title;

    @OneToMany(mappedBy = "subjectProfessor")
    private Set<SubjectEntity> subjects;

    @ManyToMany(mappedBy = "professors")
    private Set<StudentEntity> students;

}
