package game.domain.service.gamerecord;

import game.application.gamerecord.command.CreateGameRecordCommand;
import game.application.gamerecord.command.ListGameRecordCommand;
import game.domain.model.gamerecord.GameRecord;
import game.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by zhangjin on 2017/6/1.
 */
public interface IGameRecordService {

    Pagination<GameRecord> pagination(ListGameRecordCommand command);

    List<GameRecord> list(ListGameRecordCommand command);

    GameRecord searchById(String id);

    void create(CreateGameRecordCommand command);
}
