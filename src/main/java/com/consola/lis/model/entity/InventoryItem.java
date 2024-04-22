package com.consola.lis.model.entity;


import com.consola.lis.model.enums.StateItem;
import com.consola.lis.model.enums.WalletOwners;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="inventory_item")
public class InventoryItem {

    @Id
    @Column(name = "item_id")
    private String itemId;


    @Column(name = "category_id")
    private int categoryId;

    @Enumerated(EnumType.STRING)
    private WalletOwners wallet;

    private Boolean lendable;

    @Enumerated(EnumType.STRING)
    StateItem state;

    @JsonRawValue
    private String attributes;

    private int quantity;

    private int total;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
}
