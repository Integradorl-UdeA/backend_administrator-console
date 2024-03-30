package com.consola.lis.model.entity;


import com.consola.lis.model.enums.StateItem;
import com.consola.lis.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="general_item")
public class GeneralItem {

    @Id
    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "category_id")
    private int categoryId;

    private Boolean lendable;

    @Enumerated(EnumType.STRING)
    StateItem state;

    @JsonRawValue
    private String attributes;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
}
