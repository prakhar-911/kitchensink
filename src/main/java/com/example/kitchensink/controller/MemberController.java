package com.example.kitchensink.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kitchensink.exception.NotFoundException;
import com.example.kitchensink.exception.RecordAlreadyExistsException;
import com.example.kitchensink.model.Member;
import com.example.kitchensink.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping
	public ResponseEntity<Object> getAllMembers() {
		List<Member> members = null;
		try {
			members = memberService.getAllMembers();
			return ResponseEntity.ok(members);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getMemberById(@PathVariable String id) {
		Member member = null;
		try {
			member = memberService.getMemberById(id);
			return ResponseEntity.ok(member);
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@PostMapping("/create")
	public ResponseEntity<Object> createMember(@RequestBody @Validated Member member) throws Exception {
		Member newMember = null;
		try {
			newMember = memberService.createMember(member);
			return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
		} catch (RecordAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable String id) throws Exception {
		try {
			memberService.deleteMember(id);
			return ResponseEntity.status(HttpStatus.OK).body("Member with id : " + id + " deleted");
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
