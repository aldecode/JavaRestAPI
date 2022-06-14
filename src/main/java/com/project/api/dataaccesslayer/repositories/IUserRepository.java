package com.project.api.dataaccesslayer.repositories;

import com.project.api.dataaccesslayer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
