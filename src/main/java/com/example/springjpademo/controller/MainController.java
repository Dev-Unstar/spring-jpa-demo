package com.example.springjpademo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Tag(name = "Main", description = "Main 관련")
public class MainController {
    @Operation(
            summary = "Main",
            description = "메인페이지 반환",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "응답정보 설명"
                    )
            }
    )

    @GetMapping(value="/")
    public String main(){
        return "main";
    }
    /*
     * swagger 샘플 어노테이션 참고용
    @GetMapping(value = "/user/getUser/{id}")
    public Map<String, String> getUser(@Parameter(description = "조회할 사용자의 아이디", example = "userid")
                                       @PathVariable(name = "id", required = true)
                                       String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "홍길동");
        return map;
    }
     */
}
