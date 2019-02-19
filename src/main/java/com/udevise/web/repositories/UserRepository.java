package com.udevise.web.repositories;

import com.udevise.web.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String> {
  Optional<User> getUserByEmailAddress(String emailAddress);
  Optional<User> getUserById(String id);
}
