package com.project.predictstock.Repositories;

import com.project.predictstock.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
