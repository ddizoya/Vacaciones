package net.vacaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.vacaciones.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> { 

	public Users findByName(String name);
	
	
}
