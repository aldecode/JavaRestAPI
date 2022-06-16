package com.project.api.dataaccesslayer.repositories;

import com.project.api.dataaccesslayer.entities.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface IRepository<T extends EntityBase> extends JpaRepository<T, Long>{
}
