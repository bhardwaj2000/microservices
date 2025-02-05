package com.dynamodb.demo.repository;

import com.dynamodb.demo.model.Lend;
import com.dynamodb.demo.model.LendStatus;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface LendRepository extends CrudRepository<Lend,String> {
    Optional<Lend> findByBookIdAndStatus(String bookId, LendStatus status);
}
