package com.aequilibrium.tbattle.repos;

import com.aequilibrium.tbattle.model.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformersRepo extends JpaRepository<Transformer, Long> {
}
