package net.vacaciones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.vacaciones.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> { 

	public Optional<Users> findByName(String name);
	
	
}
