package sellerlee.back.chatroom.presentation;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sellerlee.back.chatroom.presentation.ChatRoomController.*;
import static sellerlee.back.fixture.ArticleFixture.*;
import static sellerlee.back.fixture.MemberFixture.*;
import static sellerlee.back.security.oauth2.authentication.AuthorizationExtractor.*;
import static sellerlee.back.security.web.context.TokenSecurityInterceptorTest.*;

import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import sellerlee.back.ControllerTest;
import sellerlee.back.chatroom.application.ChatRoomCreateRequest;
import sellerlee.back.chatroom.application.ChatRoomResponse;
import sellerlee.back.chatroom.application.ChatRoomService;

@WebMvcTest(controllers = ChatRoomController.class)
class ChatRoomControllerTest extends ControllerTest {
    @MockBean
    private ChatRoomService chatRoomService;

    @DisplayName("ChatRoom에 POST 요청시 Status Code는 Created이다.")
    @Test
    void createChatRoom() throws Exception {
        ChatRoomCreateRequest createRequest = new ChatRoomCreateRequest(ARTICLE1.getId(),
                MEMBER1.getId());
        when(chatRoomService.createChatRoom(any()))
                .thenReturn(1L);

        String request = objectMapper.writeValueAsString(createRequest);

        // @formatter:off
        mockMvc
                .perform(
                        post(CHAT_ROOM_URI)
                                .header(AUTHORIZATION, TEST_AUTHORIZATION_HEADER)
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(
                        document("chat-rooms/post",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName("Authorization").description("회원의 토큰")
                                ),
                                requestFields(
                                        fieldWithPath("articleId").type(JsonFieldType.NUMBER).description("게시글의 ID"),
                                        fieldWithPath("buyerId").type(JsonFieldType.NUMBER).description("구매자(채팅 생성자)의 ID")
                                ),
                                responseHeaders(
                                        headerWithName("Location").description("생성된 채팅방의 ID가 담긴 URI")
                                )));
        // @formatter:on
    }

    @DisplayName("특정 게시글의 채팅방 GET 요청시 Status Code는 OK이다.")
    @Test
    void showChatRoomOfArticle() throws Exception {
        when(chatRoomService.showChatRoomsOf(1L)).thenReturn(Collections.singletonList(
                new ChatRoomResponse(MEMBER1.getAvatar(), MEMBER1.getNickname())));

        // @formatter:off
        mockMvc
                .perform(
                        get(CHAT_ROOM_URI)
                                .header(AUTHORIZATION, TEST_AUTHORIZATION_HEADER)
                                .param("articleId", "1"))
                .andExpect(status().isOk())
                .andDo(
                        document("chat-rooms/",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestHeaders(
                                        headerWithName("Authorization").description("회원의 토큰")
                                ),
                                requestParameters(
                                        parameterWithName("articleId").description("게시글의 ID")
                                )
                        ));
        // @formatter:on
    }
}
