package niuniu.application.gamerecord;

import niuniu.application.gamerecord.command.CreateGameRecordCommand;
import niuniu.application.gamerecord.command.ListGameRecordCommand;
import niuniu.application.gamerecord.representation.ApiGameRecordRepresentation;
import niuniu.application.gamerecord.representation.GameRecordRepresentation;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by zhangjin on 2017/6/2.
 */
public interface IGameRecordAppService {

    Pagination<ApiGameRecordRepresentation> pagination(ListGameRecordCommand command);

    List<ApiGameRecordRepresentation> list(ListGameRecordCommand command);

    void create(CreateGameRecordCommand command);

    GameRecordRepresentation searchById(String id);

}
