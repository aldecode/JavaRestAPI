package com.project.restapi.dataaccesslayer.repositories;

import com.project.restapi.dataaccesslayer.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IRepository<User> {
}
