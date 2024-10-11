package com.example.kitchensink.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.example.kitchensink.model.Member;
import com.example.kitchensink.repository.MemberRepository;
import com.example.kitchensink.service.MemberServiceImpl;

class MemberServiceTest {

	@Mock
	private MongoTemplate mongoTemplate;

	@InjectMocks
	private MemberServiceImpl memberService;

	@Mock
	MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateMember() throws Exception {
		Member newMember = new Member();
		newMember.setName("Jane Doe");
		newMember.setEmail("jane@mailinator.com");
		newMember.setPhoneNumber("2125551234");
		Mockito.when(memberRepository.findById(newMember.getId())).thenReturn(Optional.ofNullable(null));
		Mockito.when(memberRepository.findByEmail(newMember.getEmail())).thenReturn(null);

		memberService.createMember(newMember);

		verify(mongoTemplate, times(1)).save(newMember);

	}
}
