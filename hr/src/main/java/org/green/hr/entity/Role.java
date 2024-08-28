package org.green.hr.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @ColumnDefault("nextval('role_role_id_seq'::regclass)")
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "role_code", nullable = false, length = 50)
    private String roleCode;

    @Column(name = "status")
    private Short status;

}