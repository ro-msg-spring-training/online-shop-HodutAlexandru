package ro.msg.learning.shop.payload.mapquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.models.MapQuestOptions;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class MapQuestRequest implements Serializable {

    private List<String> locations;
    private MapQuestOptions options;

}
