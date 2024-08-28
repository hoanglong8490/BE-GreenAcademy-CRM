package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalTime;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "time_tracking")
public class TimeTracking {
    @Id
    @ColumnDefault("nextval('time_tracking_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "time_tracking_date", nullable = false)
    private Instant timeTrackingDate;

    @NotNull
    @Column(name = "salary_id", nullable = false)
    private Integer salaryId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "holiday_id", nullable = false)
    private Holiday holiday;

    @NotNull
    @Column(name = "time_in", nullable = false)
    private LocalTime timeIn;

    @NotNull
    @Column(name = "time_out", nullable = false)
    private LocalTime timeOut;

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