package game.application.user.command;

public class EditCommand {

    private int userId;
    private int dianPao;
    private int zimo;
    private int gameCount;
    private int parent;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDianPao() {
        return dianPao;
    }

    public void setDianPao(int dianPao) {
        this.dianPao = dianPao;
    }

    public int getZimo() {
        return zimo;
    }

    public void setZimo(int zimo) {
        this.zimo = zimo;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
