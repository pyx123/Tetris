import data.ConstData;
import data.PlayerData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import util.ShapeUtil;

import java.util.*;

public class ActionManagerTest {
    @Test
    public void traning() {
        int sampleNum = 1000;
        int[][] ws = new int[sampleNum][8];
        for (int i = 0; i < ws.length; i++) {
            for (int j = 0; j < ws[0].length - 1; j++) {
                ws[i][j] = (int) (Math.random() * 150);
            }
            ws[i][7] = -999999999;
        }

        for (int i = 0; i < 1000; i++) {
            long start = System.currentTimeMillis();
            getFitnessNew(ws);
            int[][] olds = ws.clone();
            Arrays.sort(olds, Comparator.comparing(a -> -a[7]));
            long end = System.currentTimeMillis();
            if (ws[0][7] > 0) {
                System.out.println("gen " + i + ", cost:" + (end - start) + "ms, best score:" + ws[0][7] + ", w: " + Arrays.toString(ws[0]));
            }
            selection(ws);
            cross(ws, 0.6);
            mutation(ws, 0.05);
            for (int j = 0; j < (int) (sampleNum * 0.2); j++) {
                ws[i] = olds[i];
            }
        }

    }

    @Test
    public void test_bestW() {
//        143,67,91,29,99,75,120

    }

    private void mutation(int[][] ws, double rate) {
        for (int i = 0; i < ws.length; i++) {
            if (Math.random() < rate) {
                int mutationNum = new Random().nextInt(3);
                for (int j = 0; j<= mutationNum; j++) {
                    ws[i][(int) (Math.random() * ws[0].length)] = (int) (Math.random() * 150);
                }
            }
        }
    }

    private void selection(int[][] ws) {
        double total = 0.0d;
        double[] p = new double[ws.length];
        double[] q = new double[ws.length];

        for (int i = 0; i < ws.length; i++) {
            total += ws[i][7];
        }
        for (int i = 0; i < ws.length; i++) {
            p[i] = ws[i][7] / total;
            if (i == 0) {
                q[i] = p[i];
            } else {
                q[i] = q[i - 1] + p[i];
            }
        }
        for (int i = 0; i < ws.length; i++) {
            double r = Math.random();
            if (r <= q[0]) {
                ws[i] = ws[0];
            } else {
                for (int j = 1; j < ws.length; j++) {
                    if (r < q[j]) {
                        ws[i] = ws[j];
                        break; //确定区间后跳出循环
                    }
                }
            }
        }
    }

    private void cross(int[][] ws, double rate) {
        int[] temp1 = new int[ws[0].length];
        int[] temp2 = new int[ws[0].length];
        for (int i = 0; i < ws.length; i++) {
            if (Math.random() < rate) {
                int pos = (int) (Math.random() * 6) + 1;
                int other = (int) (Math.random() * ws.length);
                cross(ws[i], ws[other], temp1, temp2, pos);
                ws[i] = temp1;
                ws[other] = temp2;
            }
        }
    }

    private void cross(int[] w1, int[] w2, int[] tmp1, int[] tmp2, int pos) {
        for (int i = 0; i < pos; i++) {
            tmp1[i] = w1[i];
            tmp2[i] = w2[i];
        }
        for (int i = pos; i < w1.length; i++) {
            tmp1[i] = w2[i];
            tmp2[i] = w1[i];
        }
    }

    private void getFitness(int[][] ws) {
        for (int j = 0; j < ws.length; j++) {
            int score = 0;
            int num = 10;
            for (int i = 0; i < num; i++) {
                PlayerData mine = new PlayerData();
                mine.setScore(0);
                mine.setMap(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
                mine.setCurX(5);
                mine.setCurY(-2);

                while (ShapeUtil.getLandingHeight(mine.getMap()) < ShapeUtil.getHeight(mine.getMap()) - 3) {
                    int[][] randomCurMap = getRandomCurMap();
                    mine.setCurMap(randomCurMap);
                    mine.setMap(ActionManager.getBestMap(mine, ws[j]));
                    ActionManager.removeFull(mine);
                }
                score += mine.getScore();
            }
            ws[j][7] = score / num;
        }
    }

    private void getFitnessNew(int[][] ws) {
        List<TraningData> traningDatas = new ArrayList<>();
        for (int j = 0; j < ws.length; j++) {
            traningDatas.add(new TraningData(ws[j]));
        }
        while (traningDatas.stream().anyMatch(data -> !data.isOver())) {
            int[][] randomCurMap = getRandomCurMap();
            traningDatas.parallelStream().forEach(data -> data.train(randomCurMap));
        }
        for (int i = 0; i < traningDatas.size(); i++) {
            ws[i][7] = traningDatas.get(i).playerData.getScore();
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class TraningData {
        PlayerData playerData;
        boolean isOver;
        int[] w;

        public TraningData(int[] w) {
            PlayerData mine = new PlayerData();
            mine.setScore(0);
            mine.setMap(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});
            mine.setCurX(5);
            mine.setCurY(-2);
            this.playerData = mine;
            this.isOver = false;
            this.w = w;
        }

        public void train(int[][] curMap) {
            if (ShapeUtil.getLandingHeight(playerData.getMap()) < ShapeUtil.getHeight(playerData.getMap()) - 3) {
                playerData.setCurMap(curMap);
                playerData.setMap(ActionManager.getBestMap(playerData, w));
                ActionManager.removeFull(playerData);
            } else {
                this.isOver = true;
            }
        }
    }

    private int[][] getRandomCurMap() {
        Random random = new Random();
        int idx = random.nextInt(6);
        boolean[][] booleans = ConstData.Shape[idx];
        int[][] curMap = new int[booleans.length][booleans[0].length];
        for (int i = 0; i < booleans.length; i++) {
            for (int j = 0; j < booleans[0].length; j++) {
                curMap[i][j] = booleans[i][j] ? 1 : 0;
            }
        }
        int changeTime = random.nextInt(4);
        while (changeTime > 0) {
            curMap = ShapeUtil.rotate(curMap);
            changeTime--;
        }
        return curMap;
    }
}