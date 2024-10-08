package org.gelecekbilimde.scienceplatform.post.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostMediaCreateRequest {

	private Long postMediaId;

	private boolean cover;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private Long mediaId;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private String postId;

	@NotNull(message = "cannot be null")
	@NotBlank(message = "cannot be null")
	private String userId;
}
