package data;

import lombok.Getter;
import lombok.Setter;
import util.ShapeType;

import java.util.List;

@Getter
@Setter
public class PlayerData {
    private String user;
    private int score;
    private int bomb;
    private int nextBombScore;
    private int[][] map;
    private int curX;
    private int curY;
    private ShapeType curShape;
    private int[][] curMap;
    private ShapeType nextShape;
    private int[][] nextMap;
    private List<BlockInfo<int[][]>> moreBlocks;
}
