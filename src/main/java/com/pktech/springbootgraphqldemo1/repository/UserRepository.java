package com.pktech.springbootgraphqldemo1.repository;


import com.pktech.springbootgraphqldemo1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
