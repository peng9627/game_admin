package niuniu.application.gamerecord;

import niuniu.application.gamerecord.command.CreateGameRecordCommand;
import niuniu.application.gamerecord.command.ListGameRecordCommand;
import niuniu.application.gamerecord.representation.ApiGameRecordRepresentation;
import niuniu.application.gamerecord.representation.GameRecordRepresentation;
import niuniu.core.mapping.IMappingService;
import niuniu.domain.model.gamerecord.GameRecord;
import niuniu.domain.service.gamerecord.IGameRecordService;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 游戏记录 Api 服务层
 * Created by zhangjin on 2017/6/2.
 */
@Service("gameRecordAppService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class GameRecordAppService implements IGameRecordAppService {

    private final IGameRecordService gameRecordService;

    private final IMappingService mappingService;

    @Autowired
    public GameRecordAppService(IGameRecordService gameRecordService, IMappingService mappingService) {
        this.gameRecordService = gameRecordService;
        this.mappingService = mappingService;
    }


    /**
     * 分页（带条件）查询
     *
     * @param command 查询条件
     * @return Pagination对象
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination<ApiGameRecordRepresentation> pagination(ListGameRecordCommand command) {

        command.verifyPage();
        command.verifyPageSize(25);

        Pagination<GameRecord> pagination = gameRecordService.pagination(command);
        List<ApiGameRecordRepresentation> data = mappingService.mapAsList(pagination.getData(), ApiGameRecordRepresentation.class);

        return new Pagination<>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }


    @Override
    @Transactional(readOnly = true)
    public List<ApiGameRecordRepresentation> list(ListGameRecordCommand command) {

        return mappingService.mapAsList(gameRecordService.list(command), ApiGameRecordRepresentation.class);
    }

    /**
     * 创建游戏记录
     *
     * @param command 创建命令
     */
    @Override
    public void create(CreateGameRecordCommand command) {

        gameRecordService.create(command);
    }

    /**
     * 根据ID获取游戏记录
     *
     * @param id 记录ID
     * @return GameRecordRepresentation
     */
    @Override
    @Transactional(readOnly = true)
    public GameRecordRepresentation searchById(String id) {

        return mappingService.map(gameRecordService.searchById(id), GameRecordRepresentation.class, false);
    }
}
