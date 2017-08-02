package game.application.notice.representation.mapping;

import game.application.notice.representation.ApiNotice;
import game.domain.model.notice.Notice;
import ma.glasnost.orika.CustomMapper;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class ApiNoticeMapper extends CustomMapper<Notice, ApiNotice> {
}
