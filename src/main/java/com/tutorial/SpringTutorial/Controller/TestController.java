package com.tutorial.SpringTutorial.Controller;

import com.tutorial.SpringTutorial.Config.PropertiesConfig;
import com.tutorial.SpringTutorial.entity.JoinFetch.Course;
import com.tutorial.SpringTutorial.entity.JoinFetch.Student;
import com.tutorial.SpringTutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequestMapping("/api")
@RestController
public class TestController {

    private final PropertiesConfig myConfig;

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    public TestController(PropertiesConfig myConfig) {
        this.myConfig = myConfig;
    }

    @PostMapping("/test")
    public void getInviteEndDate() {

        System.out.println("Invite End Date: " + myConfig.getInviteEndDate());
    }

    @PostMapping("/joinFetch")
    public Set<Course> testJoinFetch() {
        Optional<Student> results = studentRepository.findByIdWithCourses(3L);
        return results.get().getCourses();
    }

}

//Form data傳資料到後端,回傳json格式

//@RequestMapping("/website/esg/api")
//@RestController
//public class F0000301Controller {
//    @Autowired
//    private F0000301Service service;
//    @PostMapping(path = "f0000301" ,consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
//    public BaseResp<F0000301Resp> f0000301(@RequestHeader HttpHeaders headers, @RequestParam Map<String, String> formData) {
//
//        F0000301Resp resp= null;
//        ReqHeader cHeader = new ReqHeader();
//        cHeader.setTxId("f0000301");
//
//        HttpUtil.HttpRespData respData;
//        try {
//            String secret = formData.get("secret");
//            String response = formData.get("response");
//
//            F0000301Req cRequest = new F0000301Req();
//            cRequest.setSecret(secret);
//            cRequest.setResponse(response);
//
//            resp = service.f0000301(cRequest);
//        } catch (Exception e) {
//            throw new CommonException(e, cHeader);
//        }
//        System.out.println("--------------------");
//        System.out.println("respData.respStr "+ resp);
//
//
//        return ControllerUtil.tsmpResponseBaseObj(ESGRtnCodeEnum.SUCCESS.getCode(),
//                ESGRtnCodeEnum.SUCCESS.getDefaultMessage(), cHeader, resp);
//    }
//}