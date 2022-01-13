package xohoon.devTask.domain.dto.toy;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ToyDetailDto {
    private Long id;
    private String toy_subject; // main title
    private String toy_part; // 전문분야
    private String toy_part_personnel; // 분야 모집 인원
    private String toying_day; // 진행 기간
    private String toy_need_skill; // 과제진행 필요한 기술
    private String toy_btn_id; // 버튼 id
}
