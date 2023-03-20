package com.iktpreobuka.project.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.project.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
