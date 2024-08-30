package org.green.hr.service.impl;

import jakarta.transaction.Transactional;
import org.green.hr.entity.Position;
import org.green.hr.repository.PositionRepository;
import org.green.hr.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService implements IPositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    @Override
    public Position createPosition(Position position) {
        return this.positionRepository.save(position);
    }
}
