package com.app.neos.service.admin;

import com.app.neos.domain.Admin.AdminUserDTO;
import com.app.neos.domain.banner.BannerDTO;
import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.community.CommunityDTO;
import com.app.neos.domain.community.CommunityReplyDTO;
import com.app.neos.domain.counseling.CounselingDTO;
import com.app.neos.domain.counseling.CounselingReplyDTO;
import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.store.StoreDTO;
import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.domain.store.StoreReplyDTO;
import com.app.neos.domain.study.StudyDTO;
import com.app.neos.domain.study.StudyFeedReplyDTO;
import com.app.neos.domain.user.QUserDTO;
import com.app.neos.domain.user.UserDTO;
import com.app.neos.entity.banner.Banner;
import com.app.neos.entity.college.College;
import com.app.neos.entity.notice.Notice;
import com.app.neos.entity.user.User;
import com.app.neos.repository.admin.*;
import com.app.neos.repository.banner.BannerCustomRepository;
import com.app.neos.repository.banner.BannerRepository;
import com.app.neos.repository.college.CollegeCustomRepository;
import com.app.neos.repository.college.CollegeCustomRepositoryImpl;
import com.app.neos.repository.college.CollegeRepository;
import com.app.neos.repository.community.CommunityReplyRepository;
import com.app.neos.repository.community.CommunityRepository;
import com.app.neos.repository.counseling.CounselingReplyRepository;
import com.app.neos.repository.counseling.CounselingRepository;
import com.app.neos.repository.inquiry.InquiryRepository;
import com.app.neos.repository.notice.NoticeCustomRepository;
import com.app.neos.repository.notice.NoticeRepository;
import com.app.neos.repository.store.StoreReplyRepository;
import com.app.neos.repository.store.StoreRepository;
import com.app.neos.repository.study.StudyFeedReplyRepository;
import com.app.neos.repository.study.StudyRepository;
import com.app.neos.repository.user.UserCustomRepository;
import com.app.neos.repository.user.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.neos.entity.user.QUser.user;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final CollegeCustomRepository collegeCustomRepository;
    private final BannerRepository bannerRepository;
    private final BannerCustomRepository bannerCustomRepository;
    private final NoticeRepository noticeRepository;
    private final UserCustomRepository userCustomRepository;

    private final AdminCustomRepository adminCustomRepository;
    private final AdminStoreRepository adminStoreRepository;
    private final AdminStudyRepository adminStudyRepository;
    private final AdminCommunityRepository adminCommunityRepository;
    private final AdminCounselingRepository adminCounselingRepository;
    private final AdminInquiryRepository adminInquiryRepository;

    private final AdminStudyFeedReplyRepository adminStudyFeedReplyRepository;
    private final AdminCommunityReplyRepository adminCommunityReplyRepository;
    private final AdminCounselingReplyRepository adminCounselingReplyRepository;
    private final AdminStoreReplyRepository adminStoreReplyRepository;

    private final AdminStudyFollowRepository adminStudyFollowRepository;
    private final StudyRepository studyRepository;
    private final CommunityRepository communityRepository;
    private final CounselingRepository counselingRepository;
    private final StoreRepository storeRepository;

    private final StudyFeedReplyRepository studyFeedReplyRepository;
    private final CommunityReplyRepository communityReplyRepository;
    private final CounselingReplyRepository counselingReplyRepository;
    private final StoreReplyRepository storeReplyRepository;
    private final InquiryRepository inquiryRepository;





    //    ????????? ??????
    public void saveCollege(College college){
        collegeRepository.save(college);
    }

    //    ????????? ??????
    public List<CollegeDTO> findCollege(){
        return collegeCustomRepository.findAll();
    }

    //    ????????? ?????? ????????? ??????
    public Page<CollegeDTO> findCollegePage(Pageable pageable){
        return collegeCustomRepository.findAllPage(pageable);
    }

    //    ????????? ?????? ??????
    public CollegeDTO findByCollegeId(Long collegeId){
        return collegeCustomRepository.findByCollegeId(collegeId);
    }

    //    ????????? ?????? ?????? ???
    public int countByUser(Long collegeId){
        return collegeCustomRepository.countByUser(collegeId);
    }


    //    ????????? ????????? ???????????? ??????
    public College findCollegeEntity(Long collegeId){
        return collegeRepository.findById(collegeId).get();
    }

    //    ?????? ?????? ????????? ?????? ????????????
    public void deleteByCheck(String collegeIds){
        String[] arCollegeIds = collegeIds.split(",");

        for (int i = 0; i < arCollegeIds.length; i++){
            collegeRepository.deleteById(Long.parseLong(arCollegeIds[i]));
        }
    }

    //    ????????? ????????? ????????? ??????
    public void deleteById(String collegeId){
        collegeRepository.deleteById(Long.parseLong(collegeId));
    }



    //    ?????? ??????
    public void saveBanner(Banner banner){
        bannerRepository.save(banner);
    }

    //    ?????? ?????? ?????? ??????
    public List<BannerDTO> findBanner(){
        return bannerCustomRepository.findAll();
    }

    //    ?????? ???????????? ??????
    public BannerDTO findByBannerId(Long bannerId){
        return bannerCustomRepository.findById(bannerId);
    }

    //    ?????? ???????????? ????????? ??????
    public Banner findByBannerEntityId(Long bannerId){
        return bannerRepository.findById(bannerId).get();
    }

    //    ?????? ??????
    public void deleteByBannerId(Long bannerId){
        bannerRepository.deleteById(bannerId);
    }





    //    ???????????? ??????
    public void saveNotice(Notice notice){
        noticeRepository.save(notice);
    }


    //    ?????? ???????????? ??????
    public User findByUserId(Long userId){
        return  adminCustomRepository.findByUserId(userId);
    }

    //    ?????? ???????????? ??????
    public UserDTO findByUserDTOId(Long userId){
        return  adminCustomRepository.findByUserDTOId(userId);
    }

    //    ?????? ???????????? ????????? ?????? ????????? ?????????
    public AdminUserDTO workCount(Long userId){
        AdminUserDTO adminUserDTO = new AdminUserDTO();
        int replyCount = 0;

        int studyFeedReply = adminStudyFeedReplyRepository.findAllByStudyFeedReplyWriter_UserId(userId).size();
        int communityReply = adminCommunityReplyRepository.findByUser_UserId(userId).size();
        int counselingReply = adminCounselingReplyRepository.findByUser_UserId(userId).size();
        int storeReply = adminStoreReplyRepository.findByUser_UserId(userId).size();

        replyCount = studyFeedReply + communityReply  + counselingReply  + storeReply;


        adminUserDTO.setStoreCount((long) adminStoreRepository.findByUser_UserId(userId).size());
        adminUserDTO.setStudyCount((long) adminStudyRepository.findByUser_UserId(userId).size());
        adminUserDTO.setCommunityCount((long) adminCommunityRepository.findByUser_UserId(userId).size());
        adminUserDTO.setCounselingCount((long) adminCounselingRepository.findByUser_UserId(userId).size());
        adminUserDTO.setReplyCount((long) replyCount);
        adminUserDTO.setInquiryCount((long) adminInquiryRepository.findByUser_UserId(userId).size());

        return adminUserDTO;
    }

    //    ?????? ??????
    public void deleteByUserId(String userId){
        userRepository.deleteById(Long.parseLong(userId));
    }

    //    ?????? ?????? ????????? ?????? ????????????
    public void deleteByUserCheck(String userIds){
        String[] arUserIds = userIds.split(",");

        for (int i = 0; i < arUserIds.length; i++){
            userRepository.deleteById(Long.parseLong(arUserIds[i]));
        }
    }


    //    ????????? ?????? ????????? ??????
    public Page<StudyDTO> findStudyPage(Pageable pageable){
        Page<StudyDTO> studyDTOS = adminCustomRepository.findAllStudyPage(pageable);

        for (StudyDTO studyDTO : studyDTOS) {
            studyDTO.setFollowTotal(adminStudyFollowRepository.countByStudyStudyId(studyDTO.getStudyId()));
        }

        return studyDTOS;
    }

    //    ????????? ?????? ??????
    public List<StudyDTO> findAllStudy(){
        return  adminCustomRepository.findAllStudy();
    }


    //    ????????? ??????
    public void deleteByStudyId(String studyId){
        studyRepository.deleteById(Long.parseLong(studyId));
    }

    //    ????????? ?????? ????????? ?????? ????????????
    public void deleteByStudyCheck(String studyIds){
        String[] arStudyIds = studyIds.split(",");

        for (int i = 0; i < arStudyIds.length; i++){
            studyRepository.deleteById(Long.parseLong(arStudyIds[i]));
        }
    }


    //    ????????? ?????? ????????? ??????
    public Page<CommunityDTO> findCommunityPage(Pageable pageable){
        Page<CommunityDTO> communityDTOS = adminCustomRepository.findAllCommunityPage(pageable);

        return communityDTOS;
    }

    //    ????????? ?????? ??????
    public List<CommunityDTO> findAllCommunity(){
        return  adminCustomRepository.findAllCommunity();
    }

    //    ????????? ??????
    public void deleteByCommunityId(String communityId){
        communityRepository.deleteById(Long.parseLong(communityId));
    }

    //    ????????? ?????? ????????? ?????? ????????????
    public void deleteByCommunityCheck(String communityIds){
        String[] arCommunityIds = communityIds.split(",");

        for (int i = 0; i < arCommunityIds.length; i++){
            communityRepository.deleteById(Long.parseLong(arCommunityIds[i]));
        }
    }


    //    ??????????????? ?????? ????????? ??????
    public Page<CounselingDTO> findCounselingPage(Pageable pageable){
        Page<CounselingDTO> counselingDTOS = adminCustomRepository.findAllCounselingPage(pageable);

        return counselingDTOS;
    }

    //    ??????????????? ?????? ??????
    public List<CounselingDTO> findAllCounseling(){
        return  adminCustomRepository.findAllCounseling();
    }

    //    ??????????????? ??????
    public void deleteByCounselingId(String counselingId){
        counselingRepository.deleteById(Long.parseLong(counselingId));
    }

    //    ??????????????? ?????? ????????? ?????? ????????????
    public void deleteByCounselingCheck(String counselingIds){
        String[] arcounselingIds = counselingIds.split(",");

        for (int i = 0; i < arcounselingIds.length; i++){
            counselingRepository.deleteById(Long.parseLong(arcounselingIds[i]));
        }
    }


    //    ???????????? ?????? ????????? ??????
    public Page<StoreDTO> findStorePage(Pageable pageable){
        Page<StoreDTO> storeDTOS = adminCustomRepository.findAllStorePage(pageable);

        return storeDTOS;
    }

    //    ???????????? ?????? ??????
    public List<StoreDTO> findAllStore(){
        return  adminCustomRepository.findAllStore();
    }

    //    ???????????? ??????
    public void deleteByStoreId(String storeId){
        storeRepository.deleteById(Long.parseLong(storeId));
    }

    //    ???????????? ?????? ????????? ?????? ????????????
    public void deleteByStoreCheck(String storeIds){
        String[] arStoreIds = storeIds.split(",");

        for (int i = 0; i < arStoreIds.length; i++){
            storeRepository.deleteById(Long.parseLong(arStoreIds[i]));
        }
    }

    //    ???????????? ???????????? ????????????
    public List<StoreFlieDTO> findStoreFileByStoreId(Long storeId){
        return adminCustomRepository.findByStoreId(storeId);
    }


    //    ????????? ?????? ?????? ????????? ??????
    public Page<StudyFeedReplyDTO> findStudyRePage(Pageable pageable){
        Page<StudyFeedReplyDTO> studyFeedReplyDTOS = adminCustomRepository.findAllStudyReplyPage(pageable);

        return studyFeedReplyDTOS;
    }

    //    ????????? ?????? ?????? ??????
    public List<StudyFeedReplyDTO> findAllStudyRe(){
        return  adminCustomRepository.findAllStudyReply();
    }

    //    ????????? ?????? ??????
    public void deleteByStudyReId(String studyFeedReplyId){
        studyFeedReplyRepository.deleteById(Long.parseLong(studyFeedReplyId));
    }

    //    ????????? ?????? ?????? ????????? ?????? ????????????
    public void deleteByStudyReCheck(String studyFeedReplyIds){
        String[] arStudyFeedReIds = studyFeedReplyIds.split(",");

        for (int i = 0; i < arStudyFeedReIds.length; i++){
            studyFeedReplyRepository.deleteById(Long.parseLong(arStudyFeedReIds[i]));
        }
    }


    //    ????????? ?????? ?????? ????????? ??????
    public Page<CommunityReplyDTO> findCommunityRePage(Pageable pageable){
        Page<CommunityReplyDTO> communityReplyDTOS = adminCustomRepository.findAllCommunityReplyPage(pageable);

        return communityReplyDTOS;
    }

    //    ????????? ?????? ?????? ??????
    public List<CommunityReplyDTO> findAllCommunityRe(){
        return  adminCustomRepository.findAllCommunityReply();
    }

    //    ????????? ?????? ??????
    public void deleteByCommunityReId(String communityReplyId){
        communityReplyRepository.deleteById(Long.parseLong(communityReplyId));
    }

    //    ????????? ?????? ?????? ????????? ?????? ????????????
    public void deleteByCommunityReCheck(String communityReplyIds){
        String[] arCommunityReIds = communityReplyIds.split(",");

        for (int i = 0; i < arCommunityReIds.length; i++){
            communityReplyRepository.deleteById(Long.parseLong(arCommunityReIds[i]));
        }
    }


    //    ?????? ????????? ?????? ?????? ????????? ??????
    public Page<CounselingReplyDTO> findCounselingRePage(Pageable pageable){
        Page<CounselingReplyDTO> counselingReplyDTOS = adminCustomRepository.findAllCounselingReplyPage(pageable);

        return counselingReplyDTOS;
    }

    //    ?????? ????????? ?????? ?????? ??????
    public List<CounselingReplyDTO> findAllCounselingRe(){
        return  adminCustomRepository.findAllCounselingReply();
    }

    //    ?????? ????????? ?????? ??????
    public void deleteByCounselingReId(String counselingReplyId){
        counselingReplyRepository.deleteById(Long.parseLong(counselingReplyId));
    }

    //    ?????? ????????? ?????? ?????? ????????? ?????? ????????????
    public void deleteByCounselingReCheck(String counselingReplyIds){
        String[] arCounselingReIds = counselingReplyIds.split(",");

        for (int i = 0; i < arCounselingReIds.length; i++){
            counselingReplyRepository.deleteById(Long.parseLong(arCounselingReIds[i]));
        }
    }


    //    ?????? ?????? ?????? ?????? ????????? ??????
    public Page<StoreReplyDTO> findStoreRePage(Pageable pageable){
        Page<StoreReplyDTO> storeReplyDTOS = adminCustomRepository.findAllStoreReplyPage(pageable);

        return storeReplyDTOS;
    }

    //    ?????? ?????? ?????? ?????? ??????
    public List<StoreReplyDTO> findAllStoreRe(){
        return  adminCustomRepository.findAllStoreReply();
    }

    //    ?????? ?????? ?????? ??????
    public void deleteByStoreReId(String storeReplyId){
        storeReplyRepository.deleteById(Long.parseLong(storeReplyId));
    }

    //    ?????? ?????? ?????? ?????? ????????? ?????? ????????????
    public void deleteByStoreReCheck(String storeReplyIds){
        String[] arStoreReIds = storeReplyIds.split(",");

        for (int i = 0; i < arStoreReIds.length; i++){
            storeReplyRepository.deleteById(Long.parseLong(arStoreReIds[i]));
        }
    }


    //    ???????????? ?????? ????????? ??????
    public Page<InquiryDTO> findInquiryPage(Pageable pageable){
        Page<InquiryDTO> inquiryDTOS = adminCustomRepository.findAllInquiryPage(pageable);

        return inquiryDTOS;
    }

    //    ???????????? ?????? ??????
    public List<InquiryDTO> findInquiry(){
        return  adminCustomRepository.findAllInquiry();
    }

    //    ???????????? ??????
    public void deleteByInquiryId(String inquiryId){
        inquiryRepository.deleteById(Long.parseLong(inquiryId));
    }

    //    ???????????? ?????? ????????? ?????? ????????????
    public void deleteByInquiryCheck(String inquiryIds){
        String[] arInquiryIds = inquiryIds.split(",");

        for (int i = 0; i < arInquiryIds.length; i++){
            inquiryRepository.deleteById(Long.parseLong(arInquiryIds[i]));
        }
    }

    //    ???????????? ????????? ??????
    public InquiryDTO findByInquiryId(Long inquiryId){
        return adminCustomRepository.findByInquiryId(inquiryId);
    }


    //    ????????? ????????? ????????? ?????????
    public List<UserDTO> findMainUser(){
        return adminCustomRepository.findMainUser();
    }

    public List<StudyDTO> findMainStudy(){
        return adminCustomRepository.findMainStudy();
    }

    public List<InquiryDTO> findMainInquiry(){
        return adminCustomRepository.findMainInquiry();
    }

    public List<CollegeDTO> findMainCollege(){
        return adminCustomRepository.findMainCollege();
    }



}
