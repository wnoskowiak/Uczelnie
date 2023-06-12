package com.example.uczelnie.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.uczelnie.login.models.User;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//   Optional<User> findByUsername(String username);

//   Boolean existsByUsername(String username);

//   Boolean existsByEmail(String email);
// }

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.uczelnie.login.repository.ObjectRepository;

@Repository
public class UserRepository implements ObjectRepository<User> {

	private Map<Long, User> repository;

	public UserRepository() {
		this.repository = new HashMap<>();
	}

	@Override
	public void store(User emp) {
		repository.put(emp.getId(), emp);
	}

	@Override
	public User retrieve(Long id) {
		return repository.get(id);
	}

	@Override
	public Optional<User> search(String name) {
		Collection<User> emps = repository.values();
		for (User emp : emps) {
			if (emp.getUsername().equalsIgnoreCase(name))
				return Optional.of(emp) ;
		}
		return Optional.empty();
	}

	@Override
	public User delete(Long id) {
		User e = repository.get(id);
		this.repository.remove(id);
		return e;
	}

}