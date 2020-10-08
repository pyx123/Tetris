package data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import util.ShapeType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlockInfo<T> {
    private ShapeType shape;
    private T map;
}
