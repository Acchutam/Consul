package com.User.entity;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private int id;
    private int rating;
    private int userId;
    private String feedback;
}
