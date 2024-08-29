package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "overtime")
public class Overtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "overtime_date", nullable = false)
    private Date overtimeDate;

    @Column(name = "hours")
    private Float hours;

    @Column(name = "overtime_salary")
    private Float overtimeSalary;

    @Column(name = "multiplier")
    private Float multiplier;

    @Column(name = "status")
    private Short status;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}