package com.jikgorae.chat.message.acceptance;

import static com.jikgorae.chat.config.WebSockConfig.*;
import static com.jikgorae.chat.message.presentation.MessageController.*;
import static org.junit.jupiter.api.DynamicTest.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jikgorae.chat.AcceptanceTest;
import com.jikgorae.chat.message.application.MessageRequest;
import com.jikgorae.chat.message.fixture.MessageFixture;

public class MultiThreadTest extends AcceptanceTest {
    @TestFactory
    Stream<DynamicTest> multi() {
        return Stream.of(dynamicTest("multi", () -> {
            Long roomId = 1L;
            enterChatRoom(roomId);
            MessageRequest request = MessageFixture.requestOf(roomId);
            Runnable d = () -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("0");
                        sendMessage(request);
                        Thread.sleep(1000);
                    }
                } catch (JsonProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Runnable d1 = () -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("1");
                        sendMessage(request);
                        Thread.sleep(1000);
                    }
                } catch (JsonProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Runnable d2 = () -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("2");
                        sendMessage(request);
                        Thread.sleep(1000);
                    }
                } catch (JsonProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Runnable d3 = () -> {
                try {
                    for (int i = 0; i < 5; i++) {
                        System.out.println("3");
                        sendMessage(request);
                        Thread.sleep(1000);
                    }
                } catch (JsonProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread thread = new Thread(d);
            Thread thread1 = new Thread(d1);
            Thread thread2 = new Thread(d2);
            Thread thread3 = new Thread(d3);

            thread.start();
            thread1.start();
            thread2.start();
            thread3.start();
        }));
    }

    private void enterChatRoom(Long roomId) {
        session.subscribe(DESTINATION + roomId, new DefaultStompFrameHandler());
    }

    private void sendMessage(MessageRequest request) throws JsonProcessingException {
        session.send(PUBLISH + MESSAGE_URI,
                objectMapper.writeValueAsString(request).getBytes());
    }
}