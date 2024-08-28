package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "work_process")
public class WorkProcess {
    @Id
    @ColumnDefault("nextval('work_process_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "time_off", nullable = false)
    private Integer timeOff;

    @NotNull
    @Column(name = "work_date", nullable = false)
    private Instant workDate;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reward_discipline_id", nullable = false)
    private RewardDiscipline rewardDiscipline;

}