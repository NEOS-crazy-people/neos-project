package com.app.neos.entity.study;


import com.app.neos.domain.study.StudyMilestoneDTO;
import com.app.neos.entity.period.Period;
import com.app.neos.entity.user.User;
import com.app.neos.type.study.milestone.StudyMilestoneStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "TBL_STUDY_MILESTONE")
@Getter @ToString(exclude = {"mileStoneWriter","study"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyMilestone extends Period {
    @Id @GeneratedValue
    private Long studyMileStoneId;

    @NotNull
    private String studyMileStoneTitle;
    @NotNull
    private String studyMileStoneContent;

    @Enumerated(EnumType.STRING) @NotNull
    private StudyMilestoneStatus studyMilestoneStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User mileStoneWriter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDY_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Study study;

    public void changeMileStoneWriter(User mileStoneWriter){
        this.mileStoneWriter = mileStoneWriter;
    }

    public void changeStudy(Study study){
        this.study = study;
    }

    @Builder
    public StudyMilestone(@NotNull String studyMileStoneTitle, @NotNull String studyMileStoneContent, @NotNull StudyMilestoneStatus studyMilestoneStatus) {
        this.studyMileStoneTitle = studyMileStoneTitle;
        this.studyMileStoneContent = studyMileStoneContent;
        this.studyMilestoneStatus = studyMilestoneStatus;
    }

    public void update(StudyMilestoneDTO studyMilestoneDTO){
        this.studyMileStoneTitle = studyMilestoneDTO.getStudyMileStoneTitle();
        this.studyMileStoneContent = studyMilestoneDTO.getStudyMileStoneContent();
    }

    public void complete(){
        this.studyMilestoneStatus = StudyMilestoneStatus.FINISH;
    }



    public StudyMilestoneDTO toDTO(){
        StudyMilestoneDTO dto = new StudyMilestoneDTO();
        dto.setStudyMileStoneId(this.studyMileStoneId);
        dto.setStudyMileStoneTitle(this.studyMileStoneTitle);
        dto.setStudyMileStoneContent(this.studyMileStoneContent);
        dto.setStudyMilestoneStatus(this.studyMilestoneStatus);
        dto.setStudy(this.study.toDTO());
        dto.setMileStoneWriter(this.mileStoneWriter.toDTO());
        dto.setCreateDate(this.getCreatedDate().toLocalDate());
        dto.setCreatedTime(this.getCreatedDate());
        dto.setUpdatedTime(this.getUpdatedDate());
        return dto;
    }

}
