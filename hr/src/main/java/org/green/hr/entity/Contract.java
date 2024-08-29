package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "contract")
public class Contract {
    @Id
    @ColumnDefault("nextval('contract_id_seq'::regclass)")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 20)
    @NotNull
    @Column(name = "contract_code", nullable = false, length = 20)
    private String contractCode;

    @Size(max = 50)
    @Column(name = "contract_category", length = 50)
    private String contractCategory;

    @Column(name = "content_contract")
    private String contentContract;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "date_start")
    private Instant dateStart;

    @Column(name = "date_end")
    private Instant dateEnd;

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

    // Lấy danh sách các file từ contentContract
    public List<String> getContentContractFiles() {
        return contentContract == null ? List.of() : Arrays.asList(contentContract.split(","));
    }

    // Thiết lập danh sách các file vào contentContract
    public void setContentContractFiles(List<String> files) {
        this.contentContract = files == null ? null : files.stream().collect(Collectors.joining(","));
    }

}