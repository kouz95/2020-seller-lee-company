package com.jikgorae.api.member.presentation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;

import com.jikgorae.api.ControllerTest;
import com.jikgorae.api.member.application.MemberService;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(controllers = {AuthController.class, AuthAdviceController.class})
class AuthAdviceControllerTest extends ControllerTest {
    @MockBean
    private MemberService memberService;

    // @Disabled
    // @DisplayName("닉네임에 해당하는 회원이 없을 경우 HttpStatus는 BadRequest이다.")
    // @Test
    // void handleIllegalMemberLoginException_InvalidEmail() throws Exception {
    //     String request = objectMapper.writeValueAsString(INVALID_EMAIL_MEMBER_LOGIN_REQUEST);
    //
    //     doThrow(new IllegalLoginException("닉네임이 일치하는 회원이 존재하지 않습니다."))
    //             .when(memberService).login(any());
    //
    //     // @formatter:off
    //     mockMvc
    //             .perform(
    //                     post(API_URI+LOGIN_NOT_OAUTH_URI)
    //                             .content(request)
    //                             .contentType(MediaType.APPLICATION_JSON)
    //                             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isBadRequest())
    //             .andDo(document("login/advice/email",
    //                     preprocessRequest(prettyPrint()),
    //                     preprocessResponse(prettyPrint()),
    //                     requestFields(
    //                             fieldWithPath("nickname").type(JsonFieldType.STRING).description("사용자가 입력한 닉네임"),
    //                             fieldWithPath("password").type(JsonFieldType.STRING).description("사용자가 입력한 비밀번호")
    //                     )));
    //     // @formatter:on
    // }
    //
    // @Disabled
    // @DisplayName("회원의 비밀번호가 일치하지 않을 경우 HttpStatus는 BadRequest이다.")
    // @Test
    // void handleIllegalMemberLoginException_InvalidPassword() throws Exception {
    //     String request = objectMapper.writeValueAsString(INVALID_PASSWORD_MEMBER_LOGIN_REQUEST);
    //
    //     doThrow(new IllegalLoginException("비밀번호가 일치하지 않습니다.")).when(memberService).login(any());
    //
    //     // @formatter:off
    //     mockMvc
    //             .perform(
    //                     post(API_URI+LOGIN_NOT_OAUTH_URI)
    //                             .content(request)
    //                             .contentType(MediaType.APPLICATION_JSON)
    //                             .accept(MediaType.APPLICATION_JSON))
    //             .andExpect(status().isBadRequest())
    //             .andDo(document("login/advice/password",
    //                     preprocessRequest(prettyPrint()),
    //                     preprocessResponse(prettyPrint()),
    //                     requestFields(
    //                             fieldWithPath("nickname").description("사용자가 입력한 닉네임"),
    //                             fieldWithPath("password").description("사용자가 입력한 비밀번호")
    //                     )));
    //     // @formatter:on
    // }
}