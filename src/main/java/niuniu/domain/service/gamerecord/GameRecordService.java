package niuniu.domain.service.gamerecord;

import niuniu.application.gamerecord.command.CreateGameRecordCommand;
import niuniu.application.gamerecord.command.ListGameRecordCommand;
import niuniu.core.exception.NoFoundException;
import niuniu.core.upload.IFileUploadService;
import niuniu.core.util.CoreDateUtils;
import niuniu.core.util.CoreStringUtils;
import niuniu.domain.model.gamerecord.GameRecord;
import niuniu.infrastructure.persistence.hibernate.gamerecord.GameRecordRepository;
import niuniu.infrastructure.persistence.hibernate.generic.Pagination;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * GameRecord Service
 * Created by zhangjin on 2017/6/1.
 */
@Service("gameRecordService")
public class GameRecordService implements IGameRecordService {

    private final GameRecordRepository gameRecordRepository;
    private final IFileUploadService fileUploadService;


    @Autowired
    public GameRecordService(GameRecordRepository gameRecordRepository, IFileUploadService fileUploadService) {
        this.gameRecordRepository = gameRecordRepository;
        this.fileUploadService = fileUploadService;
    }


    /**
     * 分页条件查询
     *
     * @param command 查询条件
     * @return Pagination对象
     */

    @Override
    public Pagination<GameRecord> pagination(ListGameRecordCommand command) {

        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUser())) {
            criterionList.add(Restrictions.or(Restrictions.like("userNames", command.getUser() + ",", MatchMode.ANYWHERE),
                    Restrictions.like("userNames", "," + command.getUser(), MatchMode.ANYWHERE)));
        }
        if (null != command.getGameType()) {
            criterionList.add(Restrictions.eq("gameType", command.getGameType()));
        }
        if (!CoreStringUtils.isEmpty(command.getRoomOwner())) {
            criterionList.add(Restrictions.like("roomOwner", command.getRoomOwner(), MatchMode.ANYWHERE));
        }
        if (command.getRoomNo() != null) {
            criterionList.add(Restrictions.eq("roomNo", command.getRoomNo()));
        }

        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));

        return gameRecordRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }


    /**
     * 条件查询list
     *
     * @param command 查询条件
     * @return List
     */
    @Override
    public List<GameRecord> list(ListGameRecordCommand command) {

        List<Criterion> criterionList = new ArrayList<>();
        Map<String, String> aliasMap = new HashMap<>();
        if (!CoreStringUtils.isEmpty(command.getUser())) {
            criterionList.add(Restrictions.or(Restrictions.like("userNames", command.getUser() + ",", MatchMode.ANYWHERE),
                    Restrictions.like("userNames", "," + command.getUser(), MatchMode.ANYWHERE)));
        }

        if (null != command.getGameType()) {
            criterionList.add(Restrictions.eq("gameType", command.getGameType()));
        }

        if (!CoreStringUtils.isEmpty(command.getRoomOwner())) {
            criterionList.add(Restrictions.like("roomOwner", command.getRoomOwner(), MatchMode.ANYWHERE));
        }
        if (command.getRoomNo() != null) {
            criterionList.add(Restrictions.eq("roomNo", command.getRoomNo()));
        }
        Restrictions.ge("createDate", CoreDateUtils.addDay(new Date(), -3));
        List<Order> orderList = new ArrayList<>();
        orderList.add(Order.desc("createDate"));
        return gameRecordRepository.list(criterionList, orderList, null, null, aliasMap, 20);
    }

    @Override
    public GameRecord searchById(String id) {

        GameRecord gameRecord = gameRecordRepository.getById(id);
        if (null == gameRecord) {
            throw new NoFoundException("没有找到ID[" + id + "]的GameRecord数据");
        }
        return gameRecord;
    }


    /**
     * 创建gameRecord
     *
     * @param command 创建对象内容
     */
    @Override
    public void create(CreateGameRecordCommand command) {

        GameRecord gameRecord = new GameRecord();
        gameRecord.setUserNames(command.getUserNames());
        gameRecord.setGameInfo(command.getGameInfo());
        gameRecord.setBaseScore(command.getBaseScore());
        gameRecord.setCreateDate(new Date());
        gameRecord.setGameType(command.getGameType());
        gameRecord.setRoomOwner(command.getRoomOwner());
        gameRecord.setTotalRound(command.getTotalRound());
        gameRecord.setRoomNo(command.getRoomNo());
        gameRecord.setRule(command.getRule());
        gameRecord.setDoubleBull(command.getDoubleBull());
        gameRecord.setSpottedBull(command.getSpottedBull());
        gameRecord.setBombBull(command.getBombBull());
        gameRecord.setSmallBull(command.getSmallBull());
        gameRecord.setPlayerPush(command.getPlayerPush());
        gameRecord.setStartedInto(command.getStartedInto());

        gameRecordRepository.save(gameRecord);

        fileUploadService.delete(String.valueOf(command.getRoomNo()));
    }

}
