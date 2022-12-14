package com.app.neos.controller.admin;

import com.app.neos.domain.college.CollegeDTO;
import com.app.neos.domain.inquiry.InquiryDTO;
import com.app.neos.domain.store.StoreFlieDTO;
import com.app.neos.entity.inquiry.Inquiry;
import com.app.neos.repository.inquiry.InquiryRepository;
import com.app.neos.service.admin.AdminService;
import com.app.neos.service.alarm.AlarmService;
import com.app.neos.type.alarm.AlarmCategory;
import com.app.neos.type.inquiry.InquiryStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-rest/*")
@Slf4j
public class AdminRestController {
    private final AdminService adminService;
    private final InquiryRepository inquiryRepository;
    private final AlarmService alarmService;

//    대학 추가 시 로고 사진 업로드
    @PostMapping("college-upload")
    public String updateProfileImage(@RequestParam MultipartFile profileImageFile) {
        log.info("이미지"+profileImageFile.toString());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadForder = Paths.get("C:", "neos", "upload").toString();
        String profileUploadForder = Paths.get("profileImage", today).toString();
        String uploadPath = Paths.get(uploadForder, profileUploadForder).toString();

        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }


        UUID uuid = UUID.randomUUID();
        String profileImageName = uuid + "_" + profileImageFile.getOriginalFilename();

        try {
            File target = new File(uploadPath, profileImageName);
            profileImageFile.transferTo(target);

        } catch (Exception e) {
            return "false";
        }

        return profileUploadForder + "\\" + profileImageName;
    }

//    배너 등록시 배너 사진 업로드
    @PostMapping("banner-upload")
    public String updateBannerImage(@RequestPart("bannerImageFile") MultipartFile bannerImageFile) {
        log.info("이미지"+bannerImageFile.toString());
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String uploadForder = Paths.get("C:", "neos", "upload").toString();
        String profileUploadForder = Paths.get("profileImage", today).toString();
        String uploadPath = Paths.get(uploadForder, profileUploadForder).toString();

        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }


        UUID uuid = UUID.randomUUID();
        String profileImageName = uuid + "_" + bannerImageFile.getOriginalFilename();

        try {
            File target = new File(uploadPath, profileImageName);
            bannerImageFile.transferTo(target);

        } catch (Exception e) {
            return "false";
        }

        return profileUploadForder + "\\" + profileImageName;
    }

//    자료 상점 파일들 보기
    @GetMapping("show-file/{storeId}")
    public List<StoreFlieDTO> showFile(@PathVariable Long storeId){
        List<StoreFlieDTO> storeFiles = adminService.findStoreFileByStoreId(storeId);
        return storeFiles;
    }

//    문의를 올린 해당 유저 정보 가져오기
    @GetMapping("inquiry-reply/{inquiryId}")
    public InquiryDTO showReply(@PathVariable Long inquiryId){
        InquiryDTO inquiryDTO = adminService.findByInquiryId(inquiryId);
        return inquiryDTO;
    }

//    문의하기 전체 리스트 가져오기
    @GetMapping("inquiry/list")
    public Page<InquiryDTO> showInquiry(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<InquiryDTO> inquiryDTOS = adminService.findInquiryPage(pageable);
        return inquiryDTOS;
    }

    // 문의하기 답글 업데이트
    @PostMapping("inquiry-reply/save")
    @Transactional
    public String saveReply(InquiryDTO inquiryDTO, Long inquiryId){
        inquiryDTO.setInquiryStatus(InquiryStatus.COMPLETE);

        Inquiry inquiry = inquiryRepository.findById(inquiryId).get();

        inquiry.update(inquiryDTO);

        AlarmCategory category  = AlarmCategory.INQUIRY;
        alarmService.alarm(inquiry,category);

        return "save success";
    }




}
