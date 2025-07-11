package com.humanbooster.evalspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humanbooster.evalspring.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
