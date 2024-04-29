package com.cnu.devlog_springboot.service;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.slack.api.webhook.WebhookPayloads.payload;

@Slf4j
@Component
public class SlackAlertService {

    private final Slack slackClient = Slack.getInstance();

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    // slack cient에 메세지 작성, 전송 로직
    public void sendSlackAlertLog(RuntimeException e, HttpServletRequest request) {
        try {
            slackClient.send(webhookUrl, payload(p -> p
                    .text("\uD83D\uDEA8" +
                            " 서버에 에러가 감지되었습니다. 즉시 확인이 필요합니다. " +
                            "\uD83D\uDEA8")
                    .attachments(
                            List.of(generateSlackAttachment(e, request))
                    )
            ));
        } catch (IOException slackError) {
            // 실제 에러 로그가 아닌 slack과의 통신 장애이기 때문에 error가 아닌 debug로 log
            log.debug("Slack 통신 과정에 예외 발생");
        }
    }

    // 메세지 내에 첨부될 정보를 추가, 생성
    private Attachment generateSlackAttachment(RuntimeException e, HttpServletRequest request) {
        String requestTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
        String xffHeader = request.getHeader("X-FORWARDED-FOR");
        return Attachment.builder()
                .color("ff0000")
                .title(requestTime + " 발생 에러 로그")
                .fields(List.of(
                                generateSlackField("Request IP", xffHeader == null ? request.getRemoteAddr() : xffHeader),
                                generateSlackField("Request URL", request.getRequestURL() + " " + request.getMethod())
//                                generateSlackField("Error Code", e.getErrorCode().getStatus().toString()),
//                                generateSlackField("Error Message", e.getLocalizedMessage())
                        )
                )
                .build();
    }

    // 슬랙의 단락별 제목과 내용을 구성하는 객체 생성
    private Field generateSlackField(String title, String value) {
        return Field.builder()
                .title(title)
                .value(value)
                .valueShortEnough(false)
                .build();
    }
}