package ru.open.restApplication.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.open.restApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
