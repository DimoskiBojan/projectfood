package mk.ukim.finki.projectfood.model.idclasses;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
public class FoodComponentId implements Serializable {

    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "component_id")
    private Integer componentId;

    public FoodComponentId() {}

    public FoodComponentId(Integer foodId, Integer componentId) {
        this.foodId = foodId;
        this.componentId = componentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodComponentId that = (FoodComponentId) o;
        return Objects.equals(foodId, that.foodId) &&
                Objects.equals(componentId, that.componentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodId, componentId);
    }
}
