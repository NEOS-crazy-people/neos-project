
/*const $commentButton = $(".replyComponent_replyOnOff__QKoso").find(
    ".buttonComponents_button__1hvQa"
);

let commentButtonCheck = false;

$commentButton.on("click", function () {
    var $superTag = $(this).closest(".replyComponent_reply__3l-Wc");
    if ($superTag.children().length == 1 && !commentButtonCheck) {
        commentButtonCheck = true;
        $superTag.append(
            `<div class="replyComponent_replyFilter__10kow">
        <button class="buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_black__3vMzB   ">
        <img class="buttonComponents_smImg__1vYDM" src="../images/community/bluedot.png">등록순</button>
        <button class="buttonComponents_button__1hvQa buttonComponents_plain__1ljW5 buttonComponents_gray__1blI9   ">최신순</button></div>`
        );
        $superTag.append(
            `<div class="replyComponent_replyInputBox__2yLKK">
        <div class="replyComponent_replyInputContainer__3TJW8">
        <textarea class="replyComponent_replyInput__2vKhX" maxlength="1000" placeholder="댓글을 입력해주세요." style="height: 21px !important;
        resize: none;" oninput='this.style.height = "21px"; this.style.height = this.scrollHeight + "px"'></textarea></div>
        <div class="replyComponent_replyButtonGroup__2i0ow">
        <div class="replyComponent_replyButtonGroupCount__j0XkV">
        <span class="textCount">0</span><span>/</span><span>1000</span>
        </div><button class="buttonComponents_button__1hvQa buttonComponents_square__3hf2r    ">확인</button></div></div>`
        );
        $superTag.append(
            `<div class="replyComponent_replyBox__1duHS"><div class="replyComponent_replyContainer__3dxJZ"></div></div>`
        );
    } else if ($superTag.children().length == 4) {
        $superTag.find(".replyComponent_replyFilter__10kow").remove();
        $superTag.find(".replyComponent_replyInputBox__2yLKK").remove();
        $superTag.find(".replyComponent_replyBox__1duHS").remove();
        commentButtonCheck = false;
    }
});*/

const $noLoginModal = $(".noLoginModal");
const $closeBtn =$(".noLoginModal .whiteBtn");
const $yesBtn =$(".noLoginModal .redBtn");
const $xBtn = $(".noLoginModal .closeBtn")
$closeBtn.click(function(){
    $noLoginModal.hide();
})
$yesBtn.click(function(){
    $noLoginModal.hide();
})
$xBtn.click(function(){
    $noLoginModal.hide();
})

/*-----------------------------------------------------------------------------------------------------------*/
/*모달*/
const $writeBtn = $(".postBtn");
const $cancelBtn = $(".editorWrapButtonBoxLeft").find(".buttonComponents_md__26Zib");
const $exitBtn = $(".closeButtonBox").find("img");
const $registerBtn = $(".editorWrapButtonBoxRight").find("button")
$writeBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:flex !important;");
    $(".registerPost_1j").css("display","block");
})
$cancelBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:none !important;")
    $(".editorMdoal").find(".editorTitle input").val("");
    $(".editorMdoal").find(".note-editable").text("");
    $(".updatePost_1j").css("display","none");
})
$exitBtn.on("click",function(){
    $(".editorMdoal").attr("style","display:none !important;")
    $(".editorMdoal").find(".editorTitle input").val("");
    $(".editorMdoal").find(".note-editable").text("");
    $(".updatePost_1j").css("display","none");
})

/*-----------------------------------------------------------------------------------------------------------*/
$(document).ready(function(){
//취소버튼
    $cancelBtn.hover(function(){
        $(this).css('background-color','#1a7cff');
        $(this).css('border-color','#1a7cff');
        $(this).css('color','#fff');
        $(this).css('opacity','0.5')
    },function(){
        $(this).css('background-color','#fff');
        $(this).css('border','1px solid #ced2d4');
        $(this).css('color','black');
        $(this).css('opacity','1')
    })

})
/*-----------------------------------------------------------------------------------------------------------*/
$(document).ready(function(){
//펼치기
    $(".centerSectionBox").on("click", ".buttonComponents_blue__1FlTU", function () {
        const $card = $(this).parent().prev();
        if (
            $card.hasClass(
                "loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4"
            )
        ) {
            $card
                .removeClass(
                    "loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4"
                )
                .addClass("loungeCardContentsComponents_loungeContentsAreaExpand__3U5Se");
            $(this).text("닫기");
        } else {
            $card
                .removeClass(
                    "loungeCardContentsComponents_loungeContentsAreaExpand__3U5Se"
                )
                .addClass(
                    "loungeCardContentsComponents_loungeContentsAreaDefault__3QCG4"
                );
            $(this).text("펼치기");
        }
    });

//좋아요 버튼
    $("div.centerSectionBox").on("click",".likeBtn",function () {
        let likeP = $(this).children(".likeCount").val();
        if($(this).children(".likeImg").attr("src")=='https://letspl.me/assets/images/ic-letspler-heart-empty.png'){
            // $(this).children(".likeImg").attr("src","https://letspl.me/assets/images/ic-letspler-heart-full.png")
            // console.log(parseInt(likeP+1));
            // $(this).children(".likeCount").text(parseInt(likeP+1));
        }else{
            // $(this).children(".likeImg").attr("src",'https://letspl.me/assets/images/ic-letspler-heart-empty.png')
            // console.log(parseInt(likeP-1));

            // $(this).children(".likeCount").text(parseInt(likeP-1));
        }
    });

/*댓글 펼치기*/
    $(".centerSectionBox").on("click", ".buttonComponents_gray__1blI9", function () {
        if ($(this).closest(".replyComponent_reply__3l-Wc").children(".replyForm").children(".replyComponent_replyInputBox__2yLKK").css("display") == "flex") {
            $(this).closest(".replyComponent_reply__3l-Wc").children(".replyForm").children(".replyComponent_replyInputBox__2yLKK").css("display", "none");
            $(this).closest(".replyComponent_reply__3l-Wc").children(".replyForm").next(".replyComponent_replyBox__1duHS").css("display", "none");
        } else {
            $(this).closest(".replyComponent_reply__3l-Wc").children(".replyForm").children(".replyComponent_replyInputBox__2yLKK").css("display", "flex");
            $(this).closest(".replyComponent_reply__3l-Wc").children(".replyForm").next(".replyComponent_replyBox__1duHS").css("display", "block");
        }
    })

/*댓글*/
// textarea 부모클래스                              택스트애리아 클래스
    $("div.centerSectionBox").on("keyup", ".replyContent",function (e) {
        // console.log($(this));
            let content = $(this).val();
            if (content.length == 0 || content == "") {
                //텍스트애리아 로부터 숫자바뀌는거 span 태그 찾는작업
                $(this).parent().next().find(".rereplyTextCount").text("0");
            } else {
                $(this).parent().next().find(".rereplyTextCount").text(content.length);
            }
        }
    );
})


/* 위까지가 텍스트에리아 즉 댓글쓰기 */
const $heartButtons = $(".replyComponent_replyButtonBox__2O3ME").find(
    ".button_heart_1ljw5"
);

$heartButtons.on("click", function () {
    var count = parseInt($(this).text());
    var emptyHeart = `<img class="" src="https://letspl.me/assets/images/ic-letspler-heart-empty.png">`;
    var fullHeart = `<img class="" src="https://letspl.me/assets/images/ic-letspler-heart-full.png">`;
    if (
        $(this).find("img").attr("src") ==
        "https://letspl.me/assets/images/ic-letspler-heart-empty.png"
    ) {
        $(this).html(fullHeart + (count + 1));
    } else {
        $(this).html(emptyHeart + (count - 1));
    }
});


/* 댓글 좋아요 버튼 */
$(".replyComponent_reply__3l-Wc").on("click", ".replyComponent_replyFilter__10kow button", function () {
    if ($(this).hasClass("buttonComponents_gray__1blI9")) {
        $(this)
            .parent()
            .find("button")
            .each((i, item) => {
                if ($(item).hasClass("buttonComponents_black__3vMzB")) {
                    $(item)
                        .removeClass("buttonComponents_black__3vMzB")
                        .addClass("buttonComponents_gray__1blI9");
                    $(item).find("img").remove();
                }
            });
        $(this)
            .removeClass("buttonComponents_gray__1blI9")
            .addClass("buttonComponents_black__3vMzB");
        $(this).prepend(
            `<img class="buttonComponents_smImg__1vYDM" src="../images/community/bluedot.png">`
        );
    }
});

/*대댓글 펼치기*/
// const $rereplyButton = $(".replyComponent_replyOpenAndLikeButtonGroup__3r9hv").find(".rereplyButton_1j")
// $rereplyButton.on("click",function(){
//     var $button = $(this).parent().next().find(".rereplyContainer__3x");
//     if($button.css("display")=='block'){
//         $button.css("display","none");
//     }else{
//         $button.css("display","block");
//     }
// })

const $replyUpdateBtn = $(".userInformationComponents_userReplySection__3ty7Q").find(".replyUpdate");
let replyText = '';
let replyCommentCheck = false;
// $("div.centerSectionBox").on("click",$replyUpdateBtn,function () {
//     if(replyCommentCheck){
//         alert("해당 댓글 수정을 완료 해주세요.")
//         return;
//     }
//     if(!replyCommentCheck){
//         var text = $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children().eq(0).text();
//         replyText = text;
//         const $replaceTag =  $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children().eq(0);
//         var tab = `<textarea maxlength="1000" placeholder="대댓글을 입력해주세요." style="resize: none; padding: 16px 24px; border: 1px solid rgb(223, 227, 228); border-radius: 6px; height: 63px !important;" oninput='this.style.height = "21px"; this.style.height = this.scrollHeight + "px"'>`+text+`</textarea>`
//         tab += `<div class="replyComponent_replyButtonGroup__2i0ow" style="justify-content: flex-end;"><button class = "replyUpdateButton_1j" style="border: 1px solid rgb(206, 210, 212); padding: 8px 16px; border-radius: 6px;">확인</button><button class = "replyCancelButton_1j" style="border: 1px solid rgb(206, 210, 212); padding: 8px 16px; border-radius: 6px;">취소</button></div>`;
//         if($replaceTag.prop(`tagName`)=='P'){
//             $(this).closest(".userInformationComponents_userReplySection__3ty7Q").next().children().eq(0).replaceWith(tab);
//         }
//         replyCommentCheck=true;
//     }
//
// })

$("div.centerSectionBox").on("click",".replyCancelButton_1j",function () {
   var text= $(this).parent().prev().val();
    $(this).parent().prev().replaceWith(`<p class="replyContent_content_1j">`+replyText+`</p>`);
    $(this).parent().remove();
    replyCommentCheck=false;
})