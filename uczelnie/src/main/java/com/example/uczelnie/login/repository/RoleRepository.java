package com.example.uczelnie.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.uczelnie.login.models.ERole;
import com.example.uczelnie.login.models.Role;

// @Repository
// public interface RoleRepository extends JpaRepository<Role, Long> {
//   Optional<Role> findByName(ERole name);
// }

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.example.uczelnie.login.repository.ObjectRepository;

@Repository
public class RoleRepository implements ObjectRepository<Role> {

  private Map<Long, Role> repository;

  public RoleRepository() {
    this.repository = new HashMap<>();
  }

  @Override
  public void store(Role emp) {
    repository.put(emp.getId(), emp);
  }

  @Override
  public Role retrieve(Long id) {
    return repository.get(id);
  }

  public Optional<Role> findByName(ERole name) {
    Collection<Role> emps = repository.values();
    for (Role emp : emps) {
      if (emp.getName()==name)
        return Optional.of(emp);
    }
    return Optional.empty();
  }

  @Override
  public Optional<Role> search(String name) {
    return Optional.empty();
  }

  @Override
  public Role delete(Long id) {
    Role e = repository.get(id);
    this.repository.remove(id);
    return e;
  }

  public boolean existsByUsername(String username) {
    if (this.search(username).isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean existsByEmail(String email) {
    return false;
  }

}