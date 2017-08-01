package niuniu.domain.service.gamerecord;

import niuniu.application.gamerecord.command.CreateGameRecordCommand;
import niuniu.application.gamerecord.command.ListGameRecordCommand;
import niuniu.domain.model.gamerecord.GameRecord;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;

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
