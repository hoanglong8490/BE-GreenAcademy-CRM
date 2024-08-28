package org.green.hr.repository;

import org.green.hr.entity.RewardDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardDisciplineRepository extends JpaRepository<RewardDiscipline, Integer> {
}
