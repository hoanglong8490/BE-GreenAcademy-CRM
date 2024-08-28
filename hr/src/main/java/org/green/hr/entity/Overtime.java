package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "overtime")
public class Overtime {
    @Id
    @ColumnDefault("nextval('overtime_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "overtime_date", nullable = false)
    private Instant overtimeDate;

    @Column(name = "hours")
    private Float hours;

    @Column(name = "overtime_salary")
    private Float overtimeSalary;

    @Column(name = "multiplier")
    private Float multiplier;

    @Column(name = "status")
    private Short status;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}