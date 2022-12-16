package com.app.neos.service.fix;

import com.app.neos.domain.chatting.ChattingContentDTO;
import com.app.neos.domain.chatting.ChattingDTO;
import com.app.neos.domain.chatting.QChattingContentDTO;
import com.app.neos.domain.chatting.QChattingDTO;
import com.app.neos.entity.chatting.ChattingContent;
import com.app.neos.entity.chatting.ChattingRoom;
import com.app.neos.entity.chatting.QChatting;
import com.app.neos.entity.user.User;
import com.app.neos.repository.chatting.ChattingContentRepository;
import com.app.neos.repository.chatting.ChattingRepository;
import com.app.neos.repository.chatting.ChattingRoomRepository;
import com.app.neos.repository.user.UserRepository;
import com.app.neos.type.chatting.ChatType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.neos.entity.chatting.QChattingContent.chattingContent1;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FixServiceTest {
    @Autowired
    FixService fixService;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    ChattingContentRepository chattingContentRepository;
    @Autowired
    ChattingRoomRepository chattingRoomRepository;
    @Autowired
    ChattingRepository chattingRepository;
    @Autowired
    UserRepository userRepository;




    //    채팅방 내용 조회
        @Test
        public void test(){
            chattingContentRepository.findAll().stream().map(ChattingContent::toString).forEach(log::info);
        }


        @Test
        public void test2(){
        Long receiver = fixService.findByIdOneTest(4l).getReceiver().getUserId();
                ChattingContentDTO chattingContentDTO = jpaQueryFactory.select(new QChattingContentDTO(
                        chattingContent1.receiver
                )).distinct().from(chattingContent1)
                        .where(chattingContent1.receiver.userId.eq(receiver))
                        .fetchOne();

            }

        @Test
    public void saveTest(){
        ChattingContentDTO chattingContentDTO = new ChattingContentDTO();
            chattingContentDTO.setChatting(chattingRepository.findById(6L).get());
            chattingContentDTO.setMy(chattingRepository.findById(6L).get().getMyId());
            chattingContentDTO.setReceiver(chattingRepository.findById(6L).get().getReceiverId());
            chattingContentDTO.setChatType(ChatType.ENTER);
            chattingContentDTO.setChattingContent("테스트! ! !");

            ChattingContent chattingContent = chattingContentDTO.toEntity();

            chattingContent.changeMy(chattingContentDTO.getMy());
            chattingContent.changeReceiver(chattingContentDTO.getReceiver());
            chattingContent.changeChatting(chattingContentDTO.getChatting());

            fixService.saveChatting(chattingContentDTO);
        }
        }





