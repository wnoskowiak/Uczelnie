package com.example.uczelnie.login.repository;

import java.util.Optional;

public interface ObjectRepository<T> {

	public void store(T t);

	public T retrieve(Long id);

	public Optional<T> search(String name);

	public T delete(Long id);
}