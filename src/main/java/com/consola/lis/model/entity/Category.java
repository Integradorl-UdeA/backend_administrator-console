package com.consola.lis.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category", uniqueConstraints = {@UniqueConstraint(columnNames={"categoryName"})})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;
    private Boolean quantizable;

    @JsonRawValue
    private String attributes;

    @JsonRawValue
    @Column(name = "list_attributes")
    private String listAttributes;


}

