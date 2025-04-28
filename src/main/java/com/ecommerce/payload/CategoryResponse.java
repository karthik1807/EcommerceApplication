package com.ecommerce.payload;

import java.util.List;

import com.ecommerce.dto.CategoryDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
	private List<CategoryDTO> categoryContent;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalpages;
	private boolean lastPage;
}
