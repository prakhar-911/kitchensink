package com.example.kitchensink.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.kitchensink.model.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

	List<Member> findAllByOrderByNameAsc();

	Member findByEmail(String email);

}
