package com.backend.springproject.Signup;

import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepository extends JpaRepository<Signup, Long> {

}
