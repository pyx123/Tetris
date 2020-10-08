package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class ShapeUtil {
    public static boolean isConflict(int[][] map, int curX, int curY, int[][] curMap) {
        if (badX(map, curX, curMap)) {
            return true;
        }

        for (int i = 0; i < getWidth(curMap); i++) {
            for (int j = 0; j < getHeight(curMap); j++) {
                if (getValue(curMap, i, getHeight(curMap) - 1 - j) == 1 && getValue(map, curX + i, curY - j) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean badX(int[][] map, int curX, int[][] curMap) {
        for (int i = 0; i < curMap[0].length; i++) {
            for (int j = 0; j < curMap.length; j++) {
                if (curMap[j][i] == 1) {
                    if (curX + i < 0 || curX + i >= getWidth(map)) {
                        return true;
                    } else {
                        break;
                    }
                }
            }

        }
        return false;
    }

    public static int getValue(int[][] map, int x, int y) {
        if (x >= getWidth(map) || x < 0 || y >= getHeight(map) || y < 0) {
            return 0;
        }
        return map[y][x];
    }

    public static int[][] rotate(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        int dst = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++, dst--) {
            for (int j = 0; j < matrix[0].length; j++) {
                temp[j][dst] = matrix[i][j];
            }
        }
        return temp;
    }

    public static int getWidth(int[][] curMap) {
        return curMap[0].length;
    }

    public static int getHeight(int[][] curMap) {
        return curMap.length;
    }

    public static void printMatrix(int[][] mat) {
        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean canLeft(int[][] map, int curX, int curY, int[][] curMap) {
        return !isConflict(map, curX - 1, curY, curMap);
    }

    public static boolean canRight(int[][] map, int curX, int curY, int[][] curMap) {
        return !isConflict(map, curX + 1, curY, curMap);
    }

    public static boolean canDown(int[][] map, int curX, int curY, int[][] curMap) {
        return !isConflict(map, curX, curY + 1, curMap) && curY + 1 < getHeight(map);
    }

    public static int[][] downStraight(int[][] map, int curX, int curY, int[][] curMap) {
        while (canDown(map, curX, curY, curMap)) {
            curY = curY + 1;
        }
        return getNewMap(map, curX, curY, curMap);
    }

    public static int[][] getNewMap(int[][] map, int curX, int curY, int[][] curMap) {
        int[][] newMap = cloneMap(map);
        for (int i = 0; i < getWidth(curMap); i++) {
            for (int j = 0; j < getHeight(curMap); j++) {
                if (curMap[getHeight(curMap) - 1 - j][i] == 1) {
                    newMap[curY - j][curX + i] = 1;
                }
            }
        }
        return newMap;
    }

    public static int[][] cloneMap(int[][] matrix) {
        int[][] clone = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                clone[i][j] = matrix[i][j];
            }
        }
        return clone;
    }

    public static int getLandingHeight(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    return matrix.length - i;
                }
            }
        }
        return 0;
    }

    public static int getErodedPieceCellsMetric(int[][] matrix) {
        int[] sum_n = new int[]{0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210};
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    count++;
                    break;
                }
            }
        }
        return sum_n[matrix.length - count];
    }

    public static int getBoardRowTransitions(int[][] matrix) {
        int count = 0;
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (ints[j] != ints[j + 1]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getBoardColTransitions(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length - 1; j++) {
                if (matrix[j][i] != matrix[j + 1][i]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int getBoardBuriedHoles(int[][] matrix) {
        int holes = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            int colHoles = -1;
            for (int j = 0; j < matrix.length; j++) {
                if (colHoles == -1 && matrix[j][i] == 1) {
                    colHoles = 0;
                }
                if (colHoles != -1 && matrix[j][i] == 0) {
                    colHoles++;
                }
            }
            if (colHoles != -1) {
                holes = holes + colHoles;
            }
        }
        return holes;
    }

    public static int getBoardWells(int[][] matrix) {
        int[] sum_n = new int[]{0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120, 136, 153, 171, 190, 210};
        int wells = 0;
        int sum = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] == 0) {
                    if ((i - 1 < 0 || matrix[j][i - 1] == 1)
                            && (i + 1 >= getWidth(matrix) || matrix[j][i + 1] == 1)) {
                        wells++;
                    } else {
                        sum = sum + sum_n[wells];
                        wells = 0;
                    }
                }
            }
        }
        return sum;
    }

    public static int score(int[][] matrix, int[] w) {
//        score = -45*lh + 34*epcm - 32*brt - 98*bct - 79* bbh -34*bw
        return -w[0] * getLandingHeight(matrix)
                + w[1] * getErodedPieceCellsMetric(matrix)
                - w[2] * getBoardRowTransitions(matrix)
                - w[3] * getBoardColTransitions(matrix)
                - w[4] * getBoardBuriedHoles(matrix)
                - w[5] * getBoardWells(matrix);
    }

    public static List<int[][]> getAllStation(int[][] map) {
        List<int[][]> stations = new ArrayList<>();
        stations.add(map);
        for (int i = 0; i < 3; i++) {
            stations.add(rotate(stations.get(i)));
        }
        return stations;
    }

}
