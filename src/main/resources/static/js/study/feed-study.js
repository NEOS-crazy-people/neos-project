/*
 *
 */

console.log("스터디 피드 들어옴");

// 프로필 호버 DIV
/*상세보기 상단 작성자*/
$('.userIdWrap').on("mouseover",function(){
    $(this).children().children('.idHoverMenu').css("display", "block");
})

$('.userIdWrap').on("mouseout",function(){
    $(this).children().children('.idHoverMenu').css("display", "none");
})

/*우측 리더 정보*/
$('.userIdTxt').on("mouseover",function(){
    $(this).children('.idHoverMenu').css("display", "block");
})

$('.userIdTxt').on("mouseout",function(){
    $(this).children('.idHoverMenu').css("display", "none");
})

/*우측 구독중인 네오스인*/
$('.hoverNameWrap').on("mouseover",function(){
    $(this).children('.hoverName').css("display", "block");
})

$('.hoverNameWrap').on("mouseout",function(){
    $(this).children('.hoverName').css("display", "none");
})

/*댓글 작성자*/


$('#_next').on("mouseover",".profileName",function () {
    $(this).children('.hoverView').css("display", "block");
})
$('#_next').on("mouseout",".profileName",function () {
    $(this).children('.hoverView').css("display", "none");
})

// 작은 하트 호버
$('.favoriteSymbol').on("mouseover", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-hover.03f7a2966e132a098a985cdc42f83b46.svg)")
})

$('.favoriteSymbol').on("mouseout", function () {
    $(this).children('.favorite').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-empty-white.b1db0e3e169a5cf61afa41daf5bfdc15.svg)")
    $(this).children('.active').css("background-image", "url(https://letspl.me/_next/static/media/ic-favorite-full.5a5209416a8859031c9099b5540c281a.svg)")
})

$(".favoriteUser").on("click", function () {
    $(this).toggleClass("active");
})

// 댓글 열기, 닫기
// $(".feedReply").on("click", function () {
//     $(this).toggleClass('active');
//     if($(this).hasClass('active')){
//         $(this).text("댓글 열기");
//         $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "none");
//     }else{
//         $(this).text("댓글 닫기");
//         $(this).parents('.feedUploadWrap').children('.replyWrap').css("display", "flex");
//     }
//
// })

// 본인이 작성한 댓글, 피드 삭제
$(".txtBtn").on("click",function () {
    if($(this).text() == "삭제") {
        $(".modalWrapOpen").show();
        $(".modal1").css('display', 'inline-block');
    }
})

// 댓글 수정


// 댓글 작성 중 취소
$('.whiteBtn').on("click", function () {
    if($(this).text()=="취소"){
        $(this).parents('.txtInput').children("textarea").val("");
    }
})

// 피드 작성 중 취소
// let $feedContent=
$('.blackBtn').on("click", function () {
    $(this).parents('.feedInputSection').children('.feedInputWrap').children("textarea").val("")
        .attr("rows", 2);
    $(this).parents('.feed').css("display", "none");

})


// 피드 수정
let $feedText;
$(".feedBtn").on("click", function () {
    if($(this).text()=="수정"){
        $feedText = $(this).parents('.feedContentsWrap').children('.feedContents').children("textarea").text();
        $(this).text("등록");
        $(this).next().next().text("취소");
        $(this).parents('.feedContentsWrap').children('.feedContents').children("textarea").attr("disabled", false)
            .css("height", "127px");
    }
    if($(this).text()=="취소"){
        $(this).text("삭제");
        $(this).prev().prev().text("수정");
        $(this).parents('.feedContentsWrap').children('.feedContents').children("textarea").val($feedText);
        $(this).parents('.feedContentsWrap').children('.feedContents').children("textarea").attr("disabled", true)
            .css("height", "62px");
    }
})


// 피드 작성 textarea
/*버튼, 카운팅 div display*/
$(".textarea").on("propertychange change keyup paste input", function () {
    if (!$(this).val()) {
        $("div.feed").css("display", "none");
        $(this).attr("rows", 2);
    } else {
        $("div.feed").css("display", "inline-flex");
        $(this).attr("rows", 5);
    }
})

$(".textarea").on("keyup", function () {
    $("p.count").children(":first").text($(this).val().length);
})

// // 피드 작성자 채팅
// $(".chatList").on("click",function(){
//     let name = $(this).parents(".profileName").text().split(" ")[0];
//     $(".modal1").children(".modalContent").children(".modalName").text(name + "님과 대화를 시작합니다.");
//     $(".modalWrapOpen").show();
//     $(".modal1").css('display','inline-block');
// });

