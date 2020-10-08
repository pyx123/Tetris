import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import data.BlockInfo;
import data.PlayerData;
import lombok.SneakyThrows;
import model.ActionType;
import model.StepData;
import org.apache.log4j.Logger;
import util.ShapeType;
import util.ShapeUtil;

import java.util.ArrayList;
import java.util.List;

public final class ActionManager {
    private static Logger logger = Logger.getLogger(ActionManager.class);

    private ActionManager() {
    }

    public static ActionType getAction(StepData data) {
        logger.info("start to getAction");
        ActionType type = ActionType.W;
        logger.info("end to getAction=" + type.toString());
        return type;
    }

    @SneakyThrows
    public static void main(String[] args) {
        StepData stepData = new StepData();
        PlayerData mine = new PlayerData();
        PlayerData enemy = new PlayerData();
        mine.setUser("mine_abc");
        mine.setScore(0);
        mine.setBomb(0);
        mine.setNextBombScore(3000);
        mine.setMap(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0}});
        mine.setCurX(5);
        mine.setCurY(-2);
        mine.setCurMap(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 1}});
        mine.setCurShape(ShapeType.L);
        mine.setNextMap(new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 1, 0}});
        mine.setNextShape(ShapeType.S);
        mine.setMoreBlocks(Lists.newArrayList(new BlockInfo<int[][]>(ShapeType.L, new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 0, 0}}),
                new BlockInfo<int[][]>(ShapeType.I, new int[][]{{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}})));

        enemy.setUser("enemy_abc");
        enemy.setScore(0);
        enemy.setBomb(0);
        enemy.setNextBombScore(3000);
        enemy.setMap(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0}});
        enemy.setCurX(5);
        enemy.setCurY(-2);
        enemy.setCurMap(new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 1}});
        enemy.setCurShape(ShapeType.L);
        enemy.setNextMap(new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 1, 0}});
        enemy.setNextShape(ShapeType.S);
        enemy.setMoreBlocks(Lists.newArrayList(new BlockInfo<int[][]>(ShapeType.L, new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 0, 0}}),
                new BlockInfo<int[][]>(ShapeType.I, new int[][]{{0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}, {0, 1, 0, 0}})));

        stepData.setMine(mine);
        stepData.setEnemy(enemy);
        stepData.setSn(0);
        stepData.setTime(480);


//        String str = new ObjectMapper().writeValueAsString(stepData);
//        System.out.println(str);143,67,91,29,99,75,120
        getBestActions(mine, new int[]{45, 34, 32, 200, 79, 34, 30});

    }

    public static List<ActionType> getBestActions(PlayerData playerData, int[] w) {
        List<ActionType> actions = new ArrayList<>();
        int[][] map = playerData.getMap();
        int[][] curMap = playerData.getCurMap();
        int curX = playerData.getCurX();
        int curY = playerData.getCurY();
        List<int[][]> stations = ShapeUtil.getAllStation(curMap);
        int bestScore = -999999;
        int bestX = curX;
        int bestChangeTime = 0;

        for (int i = 0; i < stations.size(); i++) {
            int[][] station = stations.get(i);
            int x = -1;
            while (ShapeUtil.canRight(map, x, curY, station)) {
                x++;
                int[][] newMap = ShapeUtil.downStraight(map, x, curY, station);
                int score = ShapeUtil.score(newMap, w) - w[6] * (i + Math.abs(curX - x));
                if (score > bestScore) {
                    bestChangeTime = i;
                    bestX = x;
                    bestScore = score;
                    ShapeUtil.printMatrix(newMap);
                    System.out.println(x + "," + score);
                }
            }
        }

        while (bestChangeTime > 0) {
            actions.add(ActionType.W);
            bestChangeTime--;
        }
        for (int i = 0; i < Math.abs(curX - bestX); i++) {
            if (curX == bestX) {
                break;
            }
            if (curX - bestX > 0) {
                actions.add(ActionType.A);
            } else {
                actions.add(ActionType.D);
            }
        }
        actions.add(ActionType.X);
        System.out.println(actions);
        return actions;
    }

    public static int[][] getBestMap(PlayerData playerData, int[] w) {
        int[][] map = playerData.getMap();
        int[][] curMap = playerData.getCurMap();
        int curX = playerData.getCurX();
        int curY = playerData.getCurY();
        List<int[][]> stations = ShapeUtil.getAllStation(curMap);
        int bestScore = -999999;
        int[][] bestMap = map.clone();

        for (int i = 0; i < stations.size(); i++) {
            int[][] station = stations.get(i);
            int x = -1;
            while (ShapeUtil.canRight(map, x, curY, station)) {
                x++;
                int[][] newMap = ShapeUtil.downStraight(map, x, curY, station);

                int score = ShapeUtil.score(newMap, w) - 1 * (i + Math.abs(curX - x));
                if (score > bestScore) {
                    bestScore = score;
                    bestMap = newMap.clone();
                }
            }
        }
        return bestMap;
    }

    public static void removeFull(PlayerData data) {
        int[][] mat = data.getMap();
        int[][] newMat = new int[mat.length][mat[0].length];
        int index = mat.length - 1;
        int score = data.getScore();

        for (int i = mat.length - 1; i > 0; i--) {
            if (!isFull(mat[i])) {
                newMat[index] = mat[i];
                index--;
            } else {
                score++;
            }
        }

        data.setMap(newMat);
        data.setScore(score);
    }

    private static boolean isFull(int[] ints) {
        for (int anInt : ints) {
            if (anInt == 0) {
                return false;
            }
        }
        return true;
    }
}
