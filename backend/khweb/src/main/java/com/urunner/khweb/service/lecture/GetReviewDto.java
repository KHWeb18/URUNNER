package com.urunner.khweb.service.lecture;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class GetReviewDto {

    double avg;

    long count;

    public GetReviewDto(long avg, long count) {
        this.avg = (double)avg/(count-1);
        this.count = count;
    }
}
