package game.domain.model.task;

import game.core.id.ConcurrencySafeEntity;

/**
 * Created by pengyi
 * Date : 18-1-26.
 * desc:
 */
public class Task extends ConcurrencySafeEntity {

    private String name;
    private int reward;
    private int todayGameCount;
    private int taskType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getTodayGameCount() {
        return todayGameCount;
    }

    public void setTodayGameCount(int todayGameCount) {
        this.todayGameCount = todayGameCount;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }
}
