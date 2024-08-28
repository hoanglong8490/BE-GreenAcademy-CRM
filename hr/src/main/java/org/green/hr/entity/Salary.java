package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @ColumnDefault("nextval('salary_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Integer contractId;

    @NotNull
    @Column(name = "overtime_id", nullable = false)
    private Integer overtimeId;

    @NotNull
    @Column(name = "allowance_id", nullable = false)
    private Integer allowanceId;

    @NotNull
    @Column(name = "salary_advance_id", nullable = false)
    private Integer salaryAdvanceId;

    @NotNull
    @Column(name = "reward_discipline_id", nullable = false)
    private Integer rewardDisciplineId;

    @NotNull
    @Column(name = "time_off_id", nullable = false)
    private Integer timeOffId;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Float salary;

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