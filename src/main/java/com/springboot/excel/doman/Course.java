package com.springboot.excel.doman;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "COURSE_DTLS")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_course;
    private String name;
    private Double price;
}
