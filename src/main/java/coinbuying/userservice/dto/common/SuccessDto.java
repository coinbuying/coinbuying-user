package coinbuying.userservice.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SuccessDto {
    private final int rt = HttpStatus.CREATED.value();

    private final String rtMsg = HttpStatus.CREATED.getReasonPhrase();
}
