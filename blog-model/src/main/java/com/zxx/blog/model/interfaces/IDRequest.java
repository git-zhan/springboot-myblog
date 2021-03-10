package com.zxx.blog.model.interfaces;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IDRequest {
	
	@NotNull(message = "id not null !")
	private List<String> ids;
}
