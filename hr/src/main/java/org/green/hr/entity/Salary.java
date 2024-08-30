package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @NotNull
    @Column(name = "overtime_id", nullable = false)
    private Long overtimeId;

    @NotNull
    @Column(name = "allowance_id", nullable = false)
    private Long allowanceId;

    @NotNull
    @Column(name = "salary_advance_id", nullable = false)
    private Long salaryAdvanceId;

    @NotNull
    @Column(name = "reward_discipline_id", nullable = false)
    private Long rewardDisciplineId;

    @NotNull
    @Column(name = "time_off_id", nullable = false)
    private Long timeOffId;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Float salary;

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