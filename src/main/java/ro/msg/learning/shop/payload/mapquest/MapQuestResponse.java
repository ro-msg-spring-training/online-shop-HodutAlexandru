package ro.msg.learning.shop.payload.mapquest;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MapQuestResponse implements Serializable {

    private List<Double> distance;

}
