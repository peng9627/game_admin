package game.application.gamerecord;

import game.application.gamerecord.command.CreateGameRecordCommand;
import game.application.gamerecord.command.ListGameRecordCommand;
import game.application.gamerecord.representation.ApiGameRecordRepresentation;
import game.application.gamerecord.representation.GameRecordRepresentation;
import game.infrastructure.persistence.hibernate.generic.Pagination;

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
