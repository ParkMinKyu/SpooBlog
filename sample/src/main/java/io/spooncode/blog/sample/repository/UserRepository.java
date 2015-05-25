package io.spooncode.blog.sample.repository;

import io.spooncode.blog.sample.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by woniper on 15. 5. 22..
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
