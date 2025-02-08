package com.example.template.domain.review.service;

import com.example.template.domain.member.entity.Member;
import com.example.template.domain.review.dto.ReviewRequestDto;
import com.example.template.domain.review.dto.ReviewResponseDto;
import com.example.template.domain.review.dto.ReviewUpdateDto;

import java.util.List;

public interface ReviewService {
    Long createReview(ReviewRequestDto reviewRequestDto, Member member);
    Long updateReview(ReviewUpdateDto reviewUpdateDto, Member member);
    List<ReviewResponseDto> getAllReviews(Member member);
    Long deleteReview(long reviewId, Member member);

}
