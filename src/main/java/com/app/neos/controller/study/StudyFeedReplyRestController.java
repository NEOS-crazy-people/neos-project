package com.app.neos.controller.study;

import com.app.neos.aspect.annotation.QuestionReplyAlarm;
import com.app.neos.domain.study.StudyFeedDTO;
import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.entity.study.StudyFeed;
import com.app.neos.entity.study.StudyFeedReply;
import com.app.neos.service.study.StudyFeedReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/feed-reply/")
@RequiredArgsConstructor
public class StudyFeedReplyRestController {
    private final StudyFeedReplyService studyFeedReplyService;


    @PostMapping("/{bno}")
    public String post(@PathVariable("bno") Long feedId, StudyFeedReplyDTO studyFeedReplyDTO, Long userId, HttpSession session){
        Long myId = (Long)session.getAttribute("loginUser");
        StudyFeedDTO studyFeedDTO = new StudyFeedDTO();
        studyFeedDTO.setStudyFeedId(feedId);
        studyFeedReplyDTO.setStudyFeed(studyFeedDTO);
        studyFeedReplyService.expUp(myId);
        studyFeedReplyService.post(studyFeedReplyDTO,userId);
        return "sucess";
    }

    @GetMapping("/{bno}")
    public List<StudyFeedReplyDTO> showList(@PathVariable("bno") Long feedId){
        return studyFeedReplyService.show(feedId);
    }

    @DeleteMapping("/{bno}")
    public String deleteReply(@PathVariable("bno") Long replyId,HttpSession session){
        Long myId = (Long)session.getAttribute("loginUser");
        studyFeedReplyService.expDown(myId);
        studyFeedReplyService.remove(replyId);
        return "success";
    }

    @PutMapping("/{bno}")
    public String updateReply(@PathVariable("bno") Long replyId, StudyFeedReplyDTO studyFeedReplyDTO){
        studyFeedReplyDTO.setStudyFeedReplyId(replyId);
        studyFeedReplyService.update(studyFeedReplyDTO);
        return "success";
    }


}
