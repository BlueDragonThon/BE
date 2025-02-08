package com.example.template.domain.review.service;

import com.example.template.common.exception.handler.GeneralHandler;
import com.example.template.common.response.status.ErrorCode;
import com.example.template.domain.member.entity.Member;
import com.example.template.domain.review.dto.ReviewRequestDto;
import com.example.template.domain.review.dto.ReviewResponseDto;
import com.example.template.domain.review.dto.ReviewUpdateDto;
import com.example.template.domain.review.entity.Review;
import com.example.template.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Long createReview(ReviewRequestDto reviewRequestDto, Member member) {

        Review review = Review.builder()
                .member(member)
                .title(reviewRequestDto.getTitle())
                .content(reviewRequestDto.getContent())
                .build();

        reviewRepository.save(review);

        return review.getId();
    }

    @Override
    public Long updateReview(ReviewUpdateDto reviewUpdateDto, Member member) {

        Review review = reviewRepository.findById(reviewUpdateDto.getReviewId()).orElseThrow(()-> new GeneralHandler(ErrorCode._BAD_REQUEST));

        review.setTitle(reviewUpdateDto.getTitle());
        review.setContent(reviewUpdateDto.getContent());

        reviewRepository.save(review);

        return review.getId();
    }

    @Override
    public List<ReviewResponseDto> getAllReviews(Member member) {
        List<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc();

        return reviews.stream()
                .map(review -> ReviewResponseDto.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .content(review.getContent())
                        .writer(review.getMember().getName())
                        .isUserCreated(review.getMember().getId() == member.getId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Long deleteReview(long reviewId, Member member) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(()-> new GeneralHandler(ErrorCode._BAD_REQUEST));

        reviewRepository.delete(review);

        return review.getId();
    }
}
