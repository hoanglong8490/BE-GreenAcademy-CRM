package org.green.hr.repository;

import org.green.hr.entity.Qualification;
import org.green.hr.model.request.QualificationSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {

    @Query("SELECT q FROM Qualification q WHERE "
            + "(:status IS NULL OR q.status = :status) AND "
            + "(:keyword IS NULL OR LOWER(q.qualificationName) LIKE %:keyword% OR "
            + "LOWER(q.image) LIKE %:keyword%)")
    Page<Qualification> findByStatusAndKeyword(
            @Param("status") Short status,
            @Param("keyword") String keyword,
            Pageable pageable
    );

}
