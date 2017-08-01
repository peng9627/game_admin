package niuniu.application.notice.representation.mapping;

import ma.glasnost.orika.CustomMapper;
import niuniu.application.notice.representation.ApiNotice;
import niuniu.domain.model.notice.Notice;
import org.springframework.stereotype.Component;

/**
 * Created by pengyi
 * Date : 16-7-19.
 */
@Component
public class ApiNoticeMapper extends CustomMapper<Notice, ApiNotice> {
}
