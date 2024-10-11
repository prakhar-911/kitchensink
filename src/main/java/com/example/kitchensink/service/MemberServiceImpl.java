package com.example.kitchensink.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.kitchensink.exception.NotFoundException;
import com.example.kitchensink.exception.RecordAlreadyExistsException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberRepository memberRepository;

	private MongoTemplate mongoTemplate;

	public MemberServiceImpl(MemberRepository memberRepository, MongoTemplate mongoTemplate) {
		this.memberRepository = memberRepository;
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public List<Member> getAllMembers() throws Exception {
		List<Member> members = null;
		try {
			members = memberRepository.findAllByOrderByNameAsc();
		} catch (Exception e) {
			throw new Exception("Excetion occurred while fetching members");
		}
		if (members.isEmpty()) {
			throw new NotFoundException("Members Not Found");
		}
		return members;
	}

	@Override
	public Member getMemberById(String id) throws NotFoundException {
		return memberRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Member with id : " + id + " Not Found"));

	}

	@Override
	public Member createMember(Member member) {
		if (memberRepository.findById(member.getId()).isPresent()) {
			throw new RecordAlreadyExistsException("Id already exists");
		}
		if (emailAlreadyExists(member.getEmail())) {
			throw new RecordAlreadyExistsException("Email Id already exists");
		}
		return mongoTemplate.save(member);
	}

	private boolean emailAlreadyExists(String email) {
		Member member = memberRepository.findByEmail(email);
		return member != null;
	}

	@Override
	public void deleteMember(String id) {
		if (memberRepository.findById(id).isEmpty()) {
			throw new NotFoundException("No member exists to delete");
		}
		memberRepository.deleteById(id);
	}

}
