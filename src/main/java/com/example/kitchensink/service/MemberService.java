package com.example.kitchensink.service;

import java.util.List;

import com.example.kitchensink.model.Member;

public interface MemberService {

	List<Member> getAllMembers() throws Exception;

	Member getMemberById(String id);

	Member createMember(Member member);

	void deleteMember(String id);

}
