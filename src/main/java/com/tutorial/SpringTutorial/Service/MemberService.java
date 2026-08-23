package com.tutorial.SpringTutorial.Service;

import com.tutorial.SpringTutorial.vo.MemberCreateRequest;
import com.tutorial.SpringTutorial.vo.MemberResponse;

public interface MemberService {
    MemberResponse createMember(MemberCreateRequest request);
}

