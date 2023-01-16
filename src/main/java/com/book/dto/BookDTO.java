package com.book.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    @NotNull
    private Integer publicationDate;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String author;
    @NotNull
    private Integer numberOfPages;
}
