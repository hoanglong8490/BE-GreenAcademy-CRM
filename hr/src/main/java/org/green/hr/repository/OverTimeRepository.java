package org.green.hr.repository;

import org.green.hr.entity.Overtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverTimeRepository extends JpaRepository<Overtime, Integer> {
}
