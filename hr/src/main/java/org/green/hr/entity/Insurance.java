package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @ColumnDefault("nextval('insurance_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "insurance_number", nullable = false, length = 50)
    private String insuranceNumber;

    @Size(max = 10)
    @NotNull
    @Column(name = "insurance_category", nullable = false, length = 10)
    private String insuranceCategory;

    @Size(max = 255)
    @NotNull
    @Column(name = "provider_address", nullable = false)
    private String providerAddress;

    @Column(name = "provider_date")
    private Instant providerDate;

    @Column(name = "expiry_date")
    private Instant expiryDate;

    @Size(max = 255)
    @Column(name = "clinic")
    private String clinic;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

/*
 TODO [Reverse Engineering] create field to map the 'status' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "status", columnDefinition = "bit")
    private Object status;
*/
}