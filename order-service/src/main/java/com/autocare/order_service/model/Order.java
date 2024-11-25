package com.autocare.order_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
since the hibernate ddl-auto is set to none, hibernate not going to make tables automatically,
instead we need to create the database using the flyway migration, for that the SQL code should enter in file
which is located inside the resources db.migration
the format of the file name should V<number>__<name>.sql
let's say we want to add an another column to the table, that code can be written in another separate migration file
which is running automatically in application starts
*/

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String skuCode;
    private double price;
    private int quantity;
}
