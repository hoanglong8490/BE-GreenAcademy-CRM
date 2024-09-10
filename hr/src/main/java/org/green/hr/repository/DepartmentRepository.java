package org.green.hr.repository;

import java.util.Optional;
import org.green.hr.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
<<<<<<< HEAD

  Department findByDepartmentName(String departmentName);

  Optional<Department> findById(long id);


=======
>>>>>>> a6c4baf49445b18f5fc4dad0ee8a78e459482517
}
