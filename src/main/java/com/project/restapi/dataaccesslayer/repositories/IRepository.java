package com.project.restapi.dataaccesslayer.repositories;

import com.project.restapi.dataaccesslayer.entities.EntityBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T extends EntityBase> extends JpaRepository<T, Long>{
}
