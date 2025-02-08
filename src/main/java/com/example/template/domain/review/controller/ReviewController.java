package com.example.template.domain.review.controller;

import com.example.template.common.annotation.AuthUser;
import com.example.template.common.response.ApiResponse;
import com.example.template.domain.member.entity.Member;
import com.example.template.domain.review.dto.ReviewRequestDto;
import com.example.template.domain.review.dto.ReviewResponseDto;
import com.example.template.domain.review.dto.ReviewUpdateDto;
import com.example.template.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "후기 API", description = "후기 관련 API입니다")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    @Operation(summary = "리뷰 작성하기")
    public ApiResponse<Long> createReview(@RequestBody ReviewRequestDto reviewRequestDto, @Parameter(hidden = true) @AuthUser Member member) {

        log.info("리뷰 작성하기");

        return ApiResponse.onSuccess(reviewService.createReview(reviewRequestDto, member));
    }

    @GetMapping("/read")
    @Operation(summary = "리뷰 읽어오기")
    public ApiResponse<List<ReviewResponseDto>> readReview(@Parameter(hidden = true) @AuthUser Member member) {
        return ApiResponse.onSuccess(reviewService.getAllReviews(member));
    }

    @GetMapping("/read/college")
    @Operation(summary = "리뷰 검색(대학 기준)")
    public ApiResponse<List<ReviewResponseDto>> readReviewByCollege(@Parameter(hidden = true) @AuthUser Member member,
                                                                    @RequestParam("collegeId") String college) {
        return ApiResponse.onSuccess(reviewService.getAllReviewsByCollege(college, member));
    }

    @PostMapping("/update")
    @Operation(summary = "리뷰 업데이트")
    public ApiResponse<Long> updateReview(@RequestBody ReviewUpdateDto reviewUpdateDto, @Parameter(hidden = true) @AuthUser Member member) {
        return ApiResponse.onSuccess(reviewService.updateReview(reviewUpdateDto, member));
    }

    @DeleteMapping("/get")
    @Operation(summary = "리뷰 삭제")
    public ApiResponse<Long> getAlarm(@RequestParam("reviewId") long reviewId, @Parameter(hidden = true) @AuthUser Member member) {
        return ApiResponse.onSuccess(reviewService.deleteReview(reviewId, member));
    }
}
