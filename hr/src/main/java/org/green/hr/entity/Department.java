package org.green.hr.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
<<<<<<< HEAD
import org.green.hr.converter.BooleanToBitConverter;
=======
import java.util.List;
>>>>>>> a6c4baf49445b18f5fc4dad0ee8a78e459482517

@lombok.Getter
@lombok.Setter
@Accessors(chain = true)
@Entity
@Table(name = "department")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(max = 255)
  @NotNull
  @Column(name = "department_name", nullable = false)
  private String departmentName;

<<<<<<< HEAD
  @Size(max = 255)
  @Column(name = "description")
  private String description;
=======
    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_at")
    private Date createAt;
>>>>>>> a6c4baf49445b18f5fc4dad0ee8a78e459482517

  @Convert(converter = BooleanToBitConverter.class)
  @Column(name = "status", columnDefinition = "BIT")
  private Boolean status;


  @Column(name = "create_at")
  private Date createAt;

  @Column(name = "update_at")
  private Date updateAt;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<Position> positions = new ArrayList<>();

}